package mklib.hosseini.com.vinci.Classes;

import android.app.Activity;
import android.graphics.Bitmap;

/**
 * Created by abbas on 4/5/16.
 */
class SeperateItems implements Runnable {
    private final Item item;
    private final Loader loader;

    SeperateItems(Item item, Loader loader){

        this.item = item;
        this.loader = loader;
    }

    @Override
    public void run() {

        if(loader.fromCaching(item))
            return;

        Bitmap bmp = loader.getBitmap(item.url);
        loader.memoryCache.put(item.url, bmp);

        if(loader.fromCaching(item))
            return;

        bitmapHunter bd = new bitmapHunter(bmp, item, loader);
        Activity a = (Activity) item.imageView.getContext();
        a.runOnUiThread(bd);
    }
}
