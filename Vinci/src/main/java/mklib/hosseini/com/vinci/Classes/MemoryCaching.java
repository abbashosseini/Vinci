package mklib.hosseini.com.vinci.Classes;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

import android.graphics.Bitmap;
import android.util.Log;

public class MemoryCaching {

    private static final String TAG = MemoryCaching.class.getSimpleName();
    private static final Map<String, Bitmap> cache = Collections.synchronizedMap(new LinkedHashMap<String, Bitmap>(10,1.5f,true));

    /**
    *  current size
    **/
    private static final AtomicLong size = new AtomicLong(0);

    /**
    *  max memory in bytes
    **/
    private static final AtomicLong limit = new AtomicLong(1000000);

    /**
    * use 25% of heap
    **/
    public MemoryCaching(){
        setLimit(Runtime.getRuntime().maxMemory() / 4);
    }


    /**
     * <p>
     *     apply limition to caching task and let him know how many MegByte have
     *     and its make running the tsk and do caching more sefe
     * </p>
     *
     * @see         #limit
     * @since       2016-04-18
     * @param limit
     *              you can apply limition
     *              to caching and dot allow
     *              OOM Exception happen
     **/
    public void setLimit(final long limit){
        MemoryCaching.limit.set(limit);
        Log.i(TAG,String.format("MemoryCaching will use up to %s MB", MemoryCaching.limit.get()/1024./1024));
    }

    /**
     * <p>
     *     of course get Bitmap from cache file and its by defualt
     *     vinci caching file /storage/emulated/(int)/Vinci/cache
     *     and its get appropriate item from Map(cache)
     * </p>
     *
     * @see         FileCaching#
     * @see         #limit
     * @since       2016-04-18
     * @param       id
     *                  through this pass you get http full url
     *                  and since its particular you are safe to
     *                  use this method
     **/
    public Bitmap get(String id){
        try{
            if(!cache.containsKey(id))
                return null;
            //NullPointerException sometimes happen here
            //http://code.google.com/p/osmdroid/issues/detail?id=78
            return cache.get(id);
        }catch(NullPointerException ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * <p>
     *     create/save images in memory for later use
     *     and retrive images as object from Map(cache)
     * </p>
     *
     * @see         #cache
     * @see         #limit
     * @since       2016-04-18
     * @param       id
     *                  you can apply limition
     *                  to caching and dot allow
     *                  OOM Exception happen
     **/

    public void put(String id, Bitmap bitmap){
        try{
            if(cache.containsKey(id))
                size.set(size.get() - getSizeInBytes(cache.get(id)));
            cache.put(id, bitmap);
            size.set(size.get() +  getSizeInBytes(bitmap));
            checkSize();
        }catch(Throwable th){
            th.printStackTrace();
        }
    }

    private void checkSize() {
        Log.i(TAG, String.format("cache size = %d, length = %d", size.get(), cache.size()));

        if(size.get() > limit.get()){
            //least recently accessed item will be the first one iterated
            Iterator<Entry<String, Bitmap>> entryIterator = cache.entrySet().iterator();

            while(entryIterator.hasNext()){

                Entry<String, Bitmap> entry=entryIterator.next();
                size.set(size.get() - getSizeInBytes(entry.getValue()));
                entryIterator.remove();

                if(size.get() <= limit.get())
                    break;
            }

            Log.i(TAG, String.format("Clean cache. New size %s." , cache.size()));
        }
    }

    /*
    * deleteAll memory
    * */
    public void clear() {
        try{
            //NullPointerException sometimes happen here http://code.google.com/p/osmdroid/issues/detail?id=78
            cache.clear();
            size.set(0);
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }
    }

    long getSizeInBytes(Bitmap bitmap) {
        if(bitmap == null)
            return 0;
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}
