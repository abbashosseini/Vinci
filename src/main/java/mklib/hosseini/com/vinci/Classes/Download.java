package mklib.hosseini.com.vinci.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Created by abbas on 4/5/16.
 */
public class Download implements Callable<Bitmap> {

    private final String uri;
    private final Loader loader;
    private final Context context;

    public Download(String uri, Loader loader, Context context){
        this.uri = uri;
        this.loader = loader;
        this.context = context;
    }

    public boolean isConnected() {

        boolean status = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo;

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP )
            netInfo= connectivityManager.getNetworkInfo(connectivityManager.getAllNetworks()[0]);
        else
            netInfo = connectivityManager.getNetworkInfo(0);

        if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
            status= true;
        }

        else {

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP )
                netInfo = connectivityManager.getNetworkInfo(connectivityManager.getAllNetworks()[1]);
            else
                netInfo = connectivityManager.getNetworkInfo(1);
            if(netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED)
                status= true;
        }

        return status;
    }
    @Override
    public Bitmap call() throws Exception {

//        if (!isConnected())
//            throw new IllegalAccessError("Seem you are not connected !!");

        File f = loader.fileCache.getFile(uri);

        try {
            Bitmap bitmap = null;
            URL imageUrl = new URL(this.uri);
            HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is = conn.getInputStream();
            OutputStream os = new FileOutputStream(f);
            Utils.CopyStream(is, os);
            os.close();
            bitmap = loader.decodeFile(f);
            return bitmap;
        } catch (Throwable ex){
            ex.printStackTrace();
            if(ex instanceof OutOfMemoryError)
                loader.memoryCache.clear();
            return null;
        }
    }
}
