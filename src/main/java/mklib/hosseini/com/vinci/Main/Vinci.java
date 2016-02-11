package mklib.hosseini.com.vinci.Main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
<<<<<<< HEAD
=======
import android.graphics.Path;
>>>>>>> c85ffd69d6a270834c9c2dd750c38633a66a203d
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;

<<<<<<< HEAD
=======

>>>>>>> c85ffd69d6a270834c9c2dd750c38633a66a203d
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
<<<<<<< HEAD
import java.security.SecureRandom;
=======
>>>>>>> c85ffd69d6a270834c9c2dd750c38633a66a203d
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

<<<<<<< HEAD
import mklib.hosseini.com.vinci.ProgressTasks.PhotoProcess;

public class Vinci {

    private static Context context;
    private final String PATH = String.format("%s/%s",
            getClass().getSimpleName(),
            "Pictures");

=======
public class Vinci {

    private static Context context;
>>>>>>> c85ffd69d6a270834c9c2dd750c38633a66a203d

    public Vinci(Context context){
        Vinci.context = context;
    }


    public synchronized Drawable AndDrawable(byte[] imageByte){

        ByteArrayInputStream imageStream = new ByteArrayInputStream(imageByte);
        Bitmap image = BitmapFactory.decodeStream(imageStream);
        return new BitmapDrawable(context.getResources(), image);
    }

<<<<<<< HEAD

    public boolean intoStorage(Bitmap bitmapImage, String name, String paths) throws IOException {

        /* set your path for saving image file on Storage*/
        String MyPath = paths.isEmpty()? "" : paths ;
=======
    public boolean intoStorage(Bitmap bitmapImage, String name) throws IOException {
>>>>>>> c85ffd69d6a270834c9c2dd750c38633a66a203d

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
<<<<<<< HEAD
        * and full path as defualt is  :
        * /storage/emulated/0/Vinci/Pictures */
        String RootFolder = Environment.getExternalStorageDirectory() + File.separator
                + ( MyPath.isEmpty() ? PATH : MyPath );
=======
        * and full path is :
        * /storage/emulated/0/Vinci/Pictures */
        String RootFolder = Environment.getExternalStorageDirectory() + File.separator
                          +String.format("%s/%s",
                                getClass().getSimpleName(),
                                "Pictures");
>>>>>>> c85ffd69d6a270834c9c2dd750c38633a66a203d

        File path = new File(RootFolder);
        boolean DirectorIsCreated = path.mkdirs();

        File fileImage = new File(path, fileName);
        boolean FileIsCrreated = fileImage.createNewFile();

<<<<<<< HEAD
=======
        if (!DirectorIsCreated)
            throw new IllegalAccessError("director cant be created !");

        if (!FileIsCrreated)
            throw new IllegalAccessError("File cant be created !");
>>>>>>> c85ffd69d6a270834c9c2dd750c38633a66a203d

        //write the bytes in file
        FileOutputStream fo = new FileOutputStream(fileImage);
        fo.write(bytes.toByteArray());

        // remember close de FileOutput
        fo.close();

        return true;
<<<<<<< HEAD

    }

    private String FileName(String name){
=======
    }

    public String FileName(String name){
>>>>>>> c85ffd69d6a270834c9c2dd750c38633a66a203d
        Pattern pattern = Pattern.compile("([\\w]+.\\w{3})$");
        Matcher matcher = pattern.matcher(name);

        if (!matcher.find())
            throw new IllegalArgumentException("Name File not valid !");

<<<<<<< HEAD
        return "VINCI_" + new SecureRandom().nextInt(10_000_000) + "_"+ matcher.group().toUpperCase() ;
    }
=======
        return matcher.group();
    }

>>>>>>> c85ffd69d6a270834c9c2dd750c38633a66a203d
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
<<<<<<< HEAD
=======

>>>>>>> c85ffd69d6a270834c9c2dd750c38633a66a203d
}