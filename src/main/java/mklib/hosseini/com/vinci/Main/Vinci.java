package mklib.hosseini.com.vinci.Main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayInputStream;

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


        return new PhotoProcess.logger(Url).call();

    }

}