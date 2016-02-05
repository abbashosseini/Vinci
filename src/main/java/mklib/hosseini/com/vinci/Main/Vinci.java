package mklib.hosseini.com.vinci.Main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vinci {

    private static Context context;

    public Vinci(Context context){
        Vinci.context = context;
    }


    public synchronized Drawable AndDrawable(byte[] imageByte){

        ByteArrayInputStream imageStream = new ByteArrayInputStream(imageByte);
        Bitmap image = BitmapFactory.decodeStream(imageStream);
        return new BitmapDrawable(context.getResources(), image);
    }

    public boolean intoStorage(Bitmap bitmapImage, String name) throws IOException {

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
        * and full path is :
        * /storage/emulated/0/Vinci/Pictures */
        String RootFolder = Environment.getExternalStorageDirectory() + File.separator
                          +String.format("%s/%s",
                                getClass().getSimpleName(),
                                "Pictures");

        File path = new File(RootFolder);
        boolean DirectorIsCreated = path.mkdirs();

        File fileImage = new File(path, fileName);
        boolean FileIsCrreated = fileImage.createNewFile();

        if (!DirectorIsCreated)
            throw new IllegalAccessError("director cant be created !");

        if (!FileIsCrreated)
            throw new IllegalAccessError("File cant be created !");

        //write the bytes in file
        FileOutputStream fo = new FileOutputStream(fileImage);
        fo.write(bytes.toByteArray());

        // remember close de FileOutput
        fo.close();

        return true;
    }

    public String FileName(String name){
        Pattern pattern = Pattern.compile("([\\w]+.\\w{3})$");
        Matcher matcher = pattern.matcher(name);

        if (!matcher.find())
            throw new IllegalArgumentException("Name File not valid !");

        return matcher.group();
    }

    public synchronized Bitmap AndBitmap(String Url) throws InterruptedException, ExecutionException {

        ByteArrayInputStream imageStream = new ByteArrayInputStream(AndByte(Url));
        return BitmapFactory.decodeStream(imageStream);

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