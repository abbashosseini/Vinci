package mklib.hosseini.com.vinci.Classes;

import android.graphics.Bitmap;

/**
 * Created by abbas on 4/5/16.
 */
//Used to display bitmap in the UI thread
class bitmapHunter implements Runnable {

    final Bitmap bitmap;
    final Item item;
    final Loader loader;

    public bitmapHunter(Bitmap bitmap, Item item, Loader loader){
        this.bitmap = bitmap;
        this.item = item;
        this.loader = loader;
    }

    public void run() {

        if(loader.getFromCaching(item))
            return;

        if(bitmap != null)
            item.imageView.setImageBitmap(bitmap);

        else
            item.imageView.setImageResource(loader.displayDefualtDawable.get());
    }
}
