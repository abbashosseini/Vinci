package mklib.hosseini.com.vinci.Classes;

import java.io.File;
import java.net.URI;
import java.net.URLEncoder;

import android.content.Context;
import android.util.Log;

public class FileCaching {

    private File cacheDir;

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

    public String getPath(){
        return cacheDir.getAbsolutePath();
    }
    public File getFile(String url){

        String filename = String.format("%d_%d", url.length(), url.hashCode());
        return new File(cacheDir, filename);
    }

    public void clear(){

        File[] files = cacheDir.listFiles();

        if(files == null)
            return;

        for(File f:files) f.delete();
    }

}
