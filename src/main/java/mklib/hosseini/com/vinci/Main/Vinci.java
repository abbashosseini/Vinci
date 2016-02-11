package mklib.hosseini.com.vinci.Main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;

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

import mklib.hosseini.com.vinci.ProgressTasks.PhotoProcess;

public class Vinci {

    private static Context context;
    private final String PATH = String.format("%s/%s",
            getClass().getSimpleName(),
            "Pictures");


    public Vinci(Context context){
        Vinci.context = context;
    }


    public synchronized Drawable AndDrawable(byte[] imageByte){

        ByteArrayInputStream imageStream = new ByteArrayInputStream(imageByte);
        Bitmap image = BitmapFactory.decodeStream(imageStream);
        return new BitmapDrawable(context.getResources(), image);
    }


    public boolean intoStorage(Bitmap bitmapImage, String name, String paths) throws IOException {

        /* set your path for saving image file on Storage*/
        String MyPath = paths.isEmpty()? "" : paths ;

        /* parse string and extract name with extension from String*/
        String fileName = FileName(name);

        /*
        * compress bitmap to real size (100) if you use less then 100,  image
        * converter to Lighter then  real size  */
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        /*
        * setup path in device for save image file to it and its defualt path is :
        * /storage/emulated/0
        * and full path as defualt is  :
        * /storage/emulated/0/Vinci/Pictures */
        String RootFolder = Environment.getExternalStorageDirectory() + File.separator
                + ( MyPath.isEmpty() ? PATH : MyPath );

        File path = new File(RootFolder);
        boolean DirectorIsCreated = path.mkdirs();

        File fileImage = new File(path, fileName);
        boolean FileIsCrreated = fileImage.createNewFile();


        //write the bytes in file
        FileOutputStream fo = new FileOutputStream(fileImage);
        fo.write(bytes.toByteArray());

        // remember close de FileOutput
        fo.close();

        return true;

    }

    private String FileName(String name){
        Pattern pattern = Pattern.compile("([\\w]+.\\w{3})$");
        Matcher matcher = pattern.matcher(name);

        if (!matcher.find())
            throw new IllegalArgumentException("Name File not valid !");

        return "VINCI_" + new SecureRandom().nextInt(10_000_000) + "_"+ matcher.group().toUpperCase() ;
    }

    public synchronized byte[] AndByte(String Url) throws InterruptedException, ExecutionException {

        ExecutorService service =  Executors.newSingleThreadExecutor();
        PhotoProcess.logger sumTask = new PhotoProcess.logger(Url);
        Future<byte[]> future = service.submit(sumTask);
        service.shutdown();
        service.awaitTermination(5, TimeUnit.SECONDS);

        return future.get();

    }
}