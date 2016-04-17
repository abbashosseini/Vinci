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
    private static final AtomicLong size = new AtomicLong(0); //current size
    private static final AtomicLong limit = new AtomicLong(1000000); //max memory in bytes

    public MemoryCaching(){
        //use 25% of available heap size
        setLimit(Runtime.getRuntime().maxMemory() / 4);
    }

    public void setLimit(final long new_limit){
        limit.set(new_limit);
        Log.i(TAG,String.format("MemoryCaching will use up to %s MB", limit.get()/1024./1024));
    }

    public Bitmap get(String id){
        try{
            if(!cache.containsKey(id))
                return null;
            //NullPointerException sometimes happen here
            // http://code.google.com/p/osmdroid/issues/detail?id=78
            return cache.get(id);
        }catch(NullPointerException ex){
            ex.printStackTrace();
            return null;
        }
    }

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
//        Log.i(TAG, String.format("cache size = %d, length = %d", size.get(), cache.size()));

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
