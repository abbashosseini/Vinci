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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import mklib.hosseini.com.vinci.R;

/**

* <p>
*   with this class you can access methods and api like Builder pattern style
* </p>
*
* @author      Abbas hosseini
* @version     1.0
* @since       2016-04-18
*
**/


public class Loader implements Request {


    /**
    * keep the imageViews object and ful uri for later
    **/
    private final Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());

    /**
    * ThreadPool and threadSafe one to execute Runnable's
    **/
    private final ExecutorService executorService;
    private final Context mContext;
    public final AtomicInteger displayDefualtDawable = new AtomicInteger(R.drawable.fail);


    /**
    * store http url in this var for later
    * @see #load(String)
    **/
    public static String ImageUrl;

    /**
     * store http url in this var for later
     * @see #load(String)
     **/
    final MemoryCaching memoryCache = new MemoryCaching();

    /**
     * access the file are caching and cache any images you want
     * @see FileCaching
     **/
    final FileCaching fileCache;

    // not safe for now
    Bitmap BitmapCompress;

    /**
     * @param context
     *              get context you need from super class
     *
     * @see mklib.hosseini.com.vinci.Vinci#base(Context)
     * */
    public Loader(Context context) {

        this.mContext = context;
        this.fileCache = new FileCaching(context);
        this.executorService = Executors.newFixedThreadPool(10);

    }

    /**
     * set drawable if any thing goes wrong and
     * app don't able to display the particular
     * its display this drawable
     * */
    public Loader error(int drawable) {
        displayDefualtDawable.set(drawable);;
        return this;
    }

    /**
     * if you want get bitmap from http u[rl and
     * do something Vinci not do it for youm you
     * can use this method
     **/
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

    /**
     * just pass http url and then you have to call view(ImageView)
     * @see #view(ImageView)
     **/
    public Loader load(String imageUrl) {

        ImageUrl = imageUrl;
        return this;
    }

    /**
     * access folder and file you are save in files folder and
     * you can do any thing with it create, delete, retrive file
     * as bitmap and more
     *
     * @see Storage
     **/
    public Storage file() {
        return new Storage(this);
    }

    /**
     * set the images to imageView your gonna pass to this method
     *
     * @param imageView
     *              get particulat ImageView object
     *              and cache it and then get bitmap
     *              from memory base on ImageView Object
     **/
    public void view(ImageView imageView) {

        imageViews.put(imageView, ImageUrl);
        Bitmap bitmap = memoryCache.get(ImageUrl);

        if (bitmap != null)
            imageView.setImageBitmap(bitmap);

        else {

            photoRows(ImageUrl, imageView);
            imageView.setImageResource(displayDefualtDawable.get());
        }
    }

    /**
     * create every item separete from others item to make sure
     * items are correct and safe and add concurrency to it to
     * make it faster
     *
     * @see #imageViews
     * @see Item
     **/
    private void photoRows(String url, ImageView imageView) {
        Item item = new Item(url, imageView);
        executorService.submit(new SeperateItems(item, this));
    }

    /**
     * get bitmap from cache folder
     * or can't find it then get it
     * from web Asynchorounsly
     *
     * @see #load(String, Request)
     * @see Request
     **/
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
            Log.e(e.getClass().getSimpleName(), e.getMessage(), e);
        }
        return null;
    }

    /**
     * search files/cache folder and memory cache
     **/
    public boolean fromCaching(Item item) {
        String tag = imageViews.get(item.imageView);
        return tag == null || !tag.equals(item.url);
    }

    /**
     * delete all caching included temp files(cache folder) and mempry cache
     **/
    public void clearCache() {
        memoryCache.clear();
        fileCache.clear();

    }

    /**
     * delete all files your save/store in ./Vinci/Files
     */
    public void deleteStoreFiles(){
        Storage cls = new Storage(this);
        cls.deleteAll();
    }

    /**
     *  in here you can get bitmap Asynchronously
     **/
    @Override
    public synchronized void onSuccess(final Bitmap bitmap) {
        BitmapCompress = bitmap;
    }

    /**
     *  in here you can get Exceptions Asynchronously
     **/
    @Override
    public void onFailure(Throwable e) {

    }
}
