package mklib.hosseini.com.vinci.Classes;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mklib.hosseini.com.vinci.Tasks.Load;

/**
 * @auther abbas
 * @since 3/5/16.
 */
public class Storage extends Vinci {

    protected final String PATH = String.format(
            "%s/%s",
            "Vinci",
            "Pictures");

    protected int Quality;
    protected File fileImage;

    /* its just space for Bitmap*/
    final ByteArrayOutputStream bytes = new ByteArrayOutputStream();

    Storage(Context context) {
        super(context);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public String into(String name, String paths, int quality){

        /*
        * this variable hold on to quality and of course size of file between 1 to 10
        * 10 is highest and will save the image file with orginal quality and size
        * or less the 10  image quality/size become lower then orginal one.
        * */
        Quality = quality >= 1
                ? quality * 10
                : 10 * 10;

        /* set your path for saving image file on Storage*/
        String MyPath = paths.isEmpty()? "" : paths ;

        /* parse string and extract name with extension from String*/
        String fileName = fileName(name);

        /**
         * setup path in device for save image file to it and its defualt path is :
         *
         *       /storage/emulated/0
         *
         * and full path as defualt is  :
         *
         *      /storage/emulated/0/Vinci/Pictures
         *
         * {@code MyPath.isEmpty() ? PATH : MyPath} if is empty use defualt PATH
         */

        String RootFolder = Environment.getExternalStorageDirectory() + File.separator
                + ( MyPath.isEmpty() ? PATH : MyPath );

        /*
        * Create folder for files on the internal storage*/
        File path = new File(RootFolder);
        boolean DirectorIsCreated = path.mkdirs();

        /*
        * Create files on the internal storage*/
        fileImage = new File(path, fileName);

        boolean FileIsCrreated;
        try {
            FileIsCrreated = fileImage.createNewFile();
        } catch (IOException e) {
            Log.e(e.getClass().getSimpleName(), e.getMessage(), e);
        }


        Load.ExecuteResult result = new Load.ExecuteResult() {

            /*
            * compress bitmap to real size (100) if you use less then 100,  image
            * converter to Lighter then  real size  */
            @Override
            public void OnReady(byte[] byteArray) {

                Types types = new Types(ctxt);

                Drawable drawable = types.andDrawable(byteArray);
                types.andBitmap(drawable).
                        compress(Bitmap.CompressFormat.JPEG,
                                Quality,
                                bytes);

                FileOutputStream fo;
                try {
                    fo = new FileOutputStream(fileImage);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (IOException e) {
                    Log.e(e.getClass().getSimpleName(), e.getLocalizedMessage(), e);
                }
                //write the bytes in file

            }
        };


        /*
        * get ready to proccess the URi and get Image as byte and make it file and put it
        * in internal storage and pass URI for database if your like store it in SQLite*/
        Load.from(result).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, name);

        return fileImage.getAbsolutePath();

    }

    public Drawable from(String PATH){
        File filePath = new File(PATH);
        return Drawable.createFromPath(filePath.toString());
    }

    private synchronized String fileName(String name){
        Pattern pattern = Pattern.compile("([\\w]+.\\w{3})$");
        Matcher matcher = pattern.matcher(name);

        if (!matcher.find())
            throw new IllegalArgumentException("Name File not valid !");

        return "VINCI_" + new SecureRandom().nextInt(10_000_000) + "_"+ matcher.group().toUpperCase() ;
    }

}
