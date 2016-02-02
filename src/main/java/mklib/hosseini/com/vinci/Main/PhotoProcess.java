package mklib.hosseini.com.vinci.Main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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

    private static URL url;

    public static class logger implements Callable<byte[]> {

        final ImmutableList<byte[]> messages = new ImmutableList<>();

        public logger(String url){

            log(BackgroundProcess(url));
        }

        public void log(byte[] message) {
            messages.prepend(message);
        }

        @Override
        public byte[] call() throws Exception {
            return messages.top;
        }
    }

    public static synchronized byte[] BackgroundProcess(String url) {

        Bitmap bitmap;
        try {
                /*
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
    }
