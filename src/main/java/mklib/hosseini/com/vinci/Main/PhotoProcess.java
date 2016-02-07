package mklib.hosseini.com.vinci.Main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import mklib.hosseini.com.vinci.list.ImmutableList;

/**
 * Created by abbas on 1/7/16.
 */
public class PhotoProcess {

    private static URL url = null;

    public static class logger implements Callable<byte[]> {

        final ImmutableList<byte[]> messages = new ImmutableList<>();

        public logger(final String url){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    log(load(url));
                }
            }).start();

        }

        public void log(byte[] message) {
            messages.prepend(message);
        }

        @Override
        public byte[] call() throws Exception {
            return messages.top;
        }
    }

    public static synchronized byte[] load(final String url) {

        Bitmap bitmap;
        try {  /*
                * get URL for downloading image from internet and convert it to Byte ARRAY
                * and put it in BLOB field ( database ).
                */
            PhotoProcess.url = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) PhotoProcess.url
                        .openConnection();
            assert connection != null;
            connection.setDoInput(true);
                connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);

            //Convert BYTEARRAY
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);

            return byteArray.toByteArray();

            } catch (IOException e) {
                return null;
            }

        }

    public synchronized static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

}
