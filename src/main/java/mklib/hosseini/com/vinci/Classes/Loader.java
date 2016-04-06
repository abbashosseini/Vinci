package mklib.hosseini.com.vinci.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import mklib.hosseini.com.vinci.R;

public class Loader {

    private final Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
    final MemoryCaching memoryCache = new MemoryCaching();
    final int displayDefualtDawable = R.drawable.fail;
    final FileCaching fileCache;
    final ExecutorService executorService;
    final Context mContext;
    String ImageUrl;


    public Loader(Context context){

        mContext = context;
        fileCache = new FileCaching(context);
        executorService = Executors.newFixedThreadPool(10);
    }

    public Loader load(String imageUrl){

        this.ImageUrl = imageUrl;
        return this;
    }

    public void putIn(ImageView imageView) {

        imageViews.put(imageView, ImageUrl);
        Bitmap bitmap = memoryCache.get(ImageUrl);

        if(bitmap != null)
            imageView.setImageBitmap(bitmap);

        else {

            queuePhoto(ImageUrl, imageView);
            imageView.setImageResource(displayDefualtDawable);
        }
    }

    private void queuePhoto(String url, ImageView imageView) {
        Item item = new Item(url, imageView);
        executorService.submit(new SeperateItems(item, this));
    }

    public Bitmap getBitmap(String url) {
        Bitmap bitmapDownloaded = null;
        File f = fileCache.getFile(url);

        //from SD cache
        Bitmap b = decodeFile(f);
        if(b!=null)
            return b;

        //from web if image not cacjing before

        ExecutorService service = Executors.newCachedThreadPool();
        Future<Bitmap> bitmapFuture = service.submit(new Download(url, this));
        try {
            bitmapDownloaded = bitmapFuture.get();
            service.shutdown();
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException e) {
            Log.e(e.getClass().getSimpleName(), e.getMessage());
        }

        return bitmapDownloaded;

    }

    //decodes image and scales it to reduce memory consumption
    public Bitmap decodeFile(File f){

        try {
            //decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f),null,o);

            //Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE = 100;
            int width_tmp = o.outWidth, height_tmp=o.outHeight;
            int scale = 1;
            while(true){

                if(width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
                    break;

                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            //decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
            Log.e(e.getClass().getSimpleName(), e.getMessage(), e);
        }
        return null;
    }

    protected boolean imageViewReused(Item item){
        String tag = imageViews.get(item.imageView);
        return tag == null || !tag.equals(item.url);
    }

    public void clearCache() {
        memoryCache.clear();
        fileCache.clear();
    }

}
