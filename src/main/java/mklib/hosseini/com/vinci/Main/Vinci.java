package mklib.hosseini.com.vinci.Main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Vinci {

    private Context context;

    public Vinci(Context context){
        this.context = context;
    }



    public Drawable PhotoFromDB(byte[] imageByte){

        ByteArrayInputStream imageStream = new ByteArrayInputStream(imageByte);
        Bitmap image = BitmapFactory.decodeStream(imageStream);

        return new BitmapDrawable(context.getResources(), image);
    }


    public byte[] PhotoInDB(String Url){

        String message = "Try Again !";
        byte[] photo  = new byte[]{};
        try {
          photo = new SaveIt().execute(Url).get();
        } catch (InterruptedException | ExecutionException e) {
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
        }
        return photo;
    }


    private class SaveIt extends AsyncTask<String, Void, byte[]> {

        protected byte[] doInBackground(String... urls) {

            Bitmap bitmap;

            try {

                /*
                * get URL for downloading image from internet and convert it to Byte ARRAY
                * and put it in BLOB field ( database ).
                */

                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(input);

            } catch (IOException e) {
                Toast.makeText(context, e.getMessage(),Toast.LENGTH_LONG).show();

                return null;
            }


            //Tabdil b Array
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);

            return byteArray.toByteArray();

        }

        protected void onPostExecute(byte[] feed) {
            // TODO: check this.exception
            // TODO: do something with the feed

        }
    }
}