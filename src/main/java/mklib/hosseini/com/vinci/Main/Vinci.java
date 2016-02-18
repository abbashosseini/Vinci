package mklib.hosseini.com.vinci.Main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mklib.hosseini.com.vinci.Callbacks.ExecuteResult;
import mklib.hosseini.com.vinci.ProgressTasks.Load;
import mklib.hosseini.com.vinci.ProgressTasks.PhotoProcess;

public class Vinci {



    private static Context context;
    private final String PATH = String.format("%s/%s",
            getClass().getSimpleName(),
            "Pictures");

    public Vinci(){}

    public Vinci(Context context){
        Vinci.context = context;
    }


    public synchronized Drawable andDrawable(byte[] imageByte){

        ByteArrayInputStream imageStream = new ByteArrayInputStream(imageByte);
        Bitmap image = BitmapFactory.decodeStream(imageStream);
        return new BitmapDrawable(context.getResources(), image);
    }


    public String intoStorage(String name, String paths, int quality){

        /*
        * this variable hold on to quality and of course size of file between 1 to 10
        * 10 is highest and will save the image file with orginal quality and size
        * or less the 10  image quality/size become lower then orginal one.
        * */
        final int sizeAndquality = quality >= 1
                                ? quality * 10
                                : 10 * 10;

        /* its just space for Bitmap*/
        final ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        /* set your path for saving image file on Storage*/
        String MyPath = paths.isEmpty()? "" : paths ;

        /* parse string and extract name with extension from String*/
        String fileName = FileName(name);

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
        final File fileImage = new File(path, fileName);

        boolean FileIsCrreated;
        try {
            FileIsCrreated = fileImage.createNewFile();
        } catch (IOException e) {
            Log.e(e.getClass().getSimpleName(), e.getMessage(), e);
        }

        /*
        * compress bitmap to real size (100) if you use less then 100,  image
        * converter to Lighter then  real size  */
        ExecuteResult byteArray = new ExecuteResult() {
            @Override
            public void OnReady(byte[] byteArray) {

                Drawable drawable = andDrawable(byteArray);

                andBitmap(drawable).
                        compress(Bitmap.CompressFormat.JPEG,
                                sizeAndquality,
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
        Load.load(byteArray).execute(name);

        return fileImage.getAbsolutePath();

    }

    private String FileName(String name){
        Pattern pattern = Pattern.compile("([\\w]+.\\w{3})$");
        Matcher matcher = pattern.matcher(name);

        if (!matcher.find())
            throw new IllegalArgumentException("Name File not valid !");

        return "VINCI_" + new SecureRandom().nextInt(10_000_000) + "_"+ matcher.group().toUpperCase() ;
    }

    public synchronized byte[] andByte(String Url) throws InterruptedException, ExecutionException {

        ExecutorService service =  Executors.newSingleThreadExecutor();
        PhotoProcess.logger sumTask = new PhotoProcess.logger(Url);
        Future<byte[]> future = service.submit(sumTask);
        service.shutdown();
        service.awaitTermination(5, TimeUnit.SECONDS);

        return future.get();

    }


    public synchronized static Bitmap andBitmap(Drawable drawable) {
        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
            // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }


}