package mklib.hosseini.com.vinci.Classes;

import android.content.Context;
import android.util.Log;

import java.io.File;

public class FileCaching {

    private final File cacheDir;

    public FileCaching(Context context){

        //Find the dir to save cached images
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
                cacheDir = new File(android.os.Environment.getExternalStorageDirectory(),"Vinci/cache");

        else
            cacheDir = context.getCacheDir();

        if(!cacheDir.exists())
            if (cacheDir.mkdirs())
                Log.i(getClass().getSimpleName(), "directory created successfully");
    }


    public final File getFile(String url){

        return new File(cacheDir, String.format("%d_%d", url.length(), url.hashCode()));
    }

    protected boolean remove(String uri){

        File file = new File(cacheDir, String.format("%d_%d", uri.length(), uri.hashCode()));
        return file.exists() && file.delete();

    }

    public void clear(){

        File[] files = cacheDir.listFiles();

        if(files == null)
            return;

        for(File f:files)
            if (f.delete())
                Log.i(getClass().getSimpleName(), String.format("file with %s name are deleted.", f.getName()));
    }

}
