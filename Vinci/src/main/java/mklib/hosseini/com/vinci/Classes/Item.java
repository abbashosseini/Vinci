package mklib.hosseini.com.vinci.Classes;

import android.widget.ImageView;

/**
 * Created by abbas on 4/5/16.
 */
class Item {

    public final String url;
    public final ImageView imageView;

    public Item(String url, ImageView image){
        this.url = url;
        this.imageView = image;
    }
}