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

    public bitmapHunter(Bitmap bitmapParam, Item itemForTask, Loader loader){
        this.bitmap = bitmapParam;
        this.item = itemForTask;
        this.loader = loader;
    }

    public void run() {

        if(loader.imageViewReused(item))
            return;

        if(bitmap != null)
            item.imageView.setImageBitmap(bitmap);

        else
            item.imageView.setImageResource(loader.displayDefualtDawable);
    }
}
