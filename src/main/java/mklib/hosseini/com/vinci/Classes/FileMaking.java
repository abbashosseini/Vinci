package mklib.hosseini.com.vinci.Classes;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.logging.Logger;

/**
 * with thi sclass you can created file and get info from paths or files
 */

public class FileMaking{

    private final File filesDir;
    private final boolean isCreated;
    private final Loader loader;
    private final Logger logger = Logger.getLogger(getClass().getSimpleName());
    private final File fileImage;

    public FileMaking(Loader loader){

        this.loader = loader;

        this.loader.load(Loader.ImageUrl, loader);

        filesDir = new File(android.os.Environment.getExternalStorageDirectory(), "Vinci/files");
        if (filesDir.mkdirs())
            logger.info(String.format("%s path is created ", filesDir.getAbsolutePath()));
        else
            logger.info("faild to create the path");


        String filename = String.format("%d_%d.jpg", Loader.ImageUrl.length(), Loader.ImageUrl.hashCode());
        fileImage = new File(filesDir, filename);

        OutputStream os = null;
        try {
            os = new FileOutputStream(fileImage);

        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Error writing bitmap", e);

        }

        //hold dawn to response if he created return true
        isCreated = loader.BitmapCompress.compress(Bitmap.CompressFormat.PNG, 100, os);


    }


    public File FileObject(){
        return fileImage;
    }

    public String LocalPath(){
        return fileImage.getAbsolutePath();
    }

    public boolean remove(){
        return fileImage.exists() && fileImage.delete();
    }

    public boolean isCreated(){
        return isCreated;
    }

    public Bitmap getBitmap(){
        return loader.BitmapCompress;
    }

    public URI Path(){
        return URI.create(filesDir.getAbsolutePath());
    }

    public URI getfullPath(){
        return URI.create(filesDir.getAbsoluteFile().getAbsolutePath());
    }

    public void clear(){

        File[] files = filesDir.listFiles();

        if(files == null)
            return;

        for(File f:files)
            if (f.delete())
                Log.i(getClass().getSimpleName(), String.format("file with %s name are deleted.", f.getName()));
    }

}
