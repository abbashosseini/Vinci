package mklib.hosseini.com.vinci.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AtomicFile;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import mklib.hosseini.com.vinci.R;

public class Loader implements Request {

    private final Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
    private final ExecutorService executorService;
    private final Context mContext;
    public final AtomicInteger displayDefualtDawable = new AtomicInteger(R.drawable.fail);
    public static String ImageUrl;
    final MemoryCaching memoryCache = new MemoryCaching();
    final FileCaching fileCache;
    Bitmap BitmapCompress;


    public Loader(Context context) {

        this.mContext = context;
        this.fileCache = new FileCaching(context);
        this.executorService = Executors.newFixedThreadPool(10);

    }

    public Loader error(int drawable) {
        displayDefualtDawable.set(drawable);;
        return this;
    }

    public void load(String url, Request request) {

        //from web if image not found in cache folder
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Download(url, this, mContext, request));

        try {
            service.shutdown();
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            Log.e(e.getClass().getSimpleName(), e.getMessage());
        }

    }

    public Loader load(String imageUrl) {

        ImageUrl = imageUrl;
        return this;
    }

    public FileMaking file() {
        return new FileMaking(this);
    }

    public void view(ImageView imageView) {

        imageViews.put(imageView, ImageUrl);
        Bitmap bitmap = memoryCache.get(ImageUrl);

        if (bitmap != null)
            imageView.setImageBitmap(bitmap);

        else {

            queuePhoto(ImageUrl, imageView);
            imageView.setImageResource(displayDefualtDawable.get());
        }
    }

    private void queuePhoto(String url, ImageView imageView) {
        Item item = new Item(url, imageView);
        executorService.submit(new SeperateItems(item, this));
    }

    public Bitmap getBitmap(String url) {
        File f = fileCache.getFile(url);

        //from SD cache
        Bitmap b = decodeFile(f);
        if (b != null)
             return b;


        //from the net
        load(url, this);
        return BitmapCompress;
    }

    //decodes image and scales it to reduce memory consumption
    public Bitmap decodeFile(File f) {

        try {
            //decode image size
//            BitmapFactory.Options option = new BitmapFactory.Options();
//            option.inJustDecodeBounds = true;
//            //disable scaling
//            option.inScaled = false;
//            option.inDither = false;
//            option.inPreferredConfig = Bitmap.Config.ARGB_8888;
//            BitmapFactory.decodeStream(new FileInputStream(f),null,option);

//            //Find the correct scale value. It should be the power of 2.
//            final int REQUIRED_SIZE = 100;
//            int width_tmp = option.outWidth,
//                height_tmp = option.outHeight;
//
//            int scale = 1;
//            while(true){
//
//                if(width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
//                    break;
//
//                width_tmp /= 2;
//                height_tmp /= 2;
//                scale *= 2;
//            }

            //decode with inSampleSize
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, options);
        } catch (FileNotFoundException e) {
//            Log.e(e.getClass().getSimpleName(), e.getMessage(), e);
        }
        return null;
    }

    public boolean getFromCaching(Item item) {
        String tag = imageViews.get(item.imageView);
        return tag == null || !tag.equals(item.url);
    }

    public void clearCache() {
        memoryCache.clear();
        fileCache.clear();
    }

    public void clearFiles(){
        FileMaking cls = new FileMaking(this);
        cls.clear();
    }

    @Override
    public synchronized void onSuccess(final Bitmap bitmap) {
        BitmapCompress = bitmap;
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
