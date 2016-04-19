package mklib.hosseini.com.vinci.Classes;

import android.graphics.Bitmap;

/**
* <p>
*   Used to display bitmap in the UI threa 
* </p>
*
* @author      Abbas hosseini
* @version     1.0
* @since       2016-04-18
*
**/

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

        if(loader.fromCaching(item))
            return;

        if(bitmap != null)
            item.imageView.setImageBitmap(bitmap);

        else
            item.imageView.setImageResource(loader.displayDefualtDawable.get());
    }
}
