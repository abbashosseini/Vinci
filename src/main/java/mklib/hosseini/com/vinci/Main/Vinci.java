package mklib.hosseini.com.vinci.Main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Vinci {

    private static Context context;

    public Vinci(Context context){
        Vinci.context = context;
    }

    public synchronized Drawable PhotoFromDB(byte[] imageByte){

        ByteArrayInputStream imageStream = new ByteArrayInputStream(imageByte);
        Bitmap image = BitmapFactory.decodeStream(imageStream);

        return new BitmapDrawable(context.getResources(), image);
    }

    public synchronized byte[] PhotoInDB(String Url) throws Exception {


        ExecutorService service =  Executors.newSingleThreadExecutor();
        PhotoProcess.logger sumTask = new PhotoProcess.logger(Url);
        Future<byte[]> future = service.submit(sumTask);
        service.shutdown();
        service.awaitTermination(5, TimeUnit.SECONDS);

        return future.get();

    }

}