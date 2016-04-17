package mklib.hosseini.com.vinci.Classes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import mklib.hosseini.com.vinci.Classes.Exceptions.NetworkException;

/**
 * Created by abbas on 4/5/16.
 */
public class Download implements Runnable {

    private final String uri;
    private final Loader loader;
    private final Context context;
    public final Request request;

    public Download(
            String uri,
            Loader loader,
            Context context,
            Request request){

        this.uri = uri;
        this.loader = loader;
        this.context = context;
        this.request = request;
    }

//
//    public boolean isConnected() {
//
//        boolean status = false;
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo;
//
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP )
//            netInfo = connectivityManager.getNetworkInfo(connectivityManager.getAllNetworks()[0]);
//        else
//            netInfo = connectivityManager.getNetworkInfo(0);
//
//        if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
//            status= true;
//        }
//
//        else {
//
//            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP )
//                netInfo = connectivityManager.getNetworkInfo(connectivityManager.getAllNetworks()[1]);
//            else
//                netInfo = connectivityManager.getNetworkInfo(1);
//            if(netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED)
//                status= true;
//        }
//
//        return status;
//    }

    public boolean isConnected() {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    @Override
    public void run() {

//        if (!isConnected()) {
//            throw new NetworkException("Seem your not connected ! make sure you App and Device connected to same network ");
//        }

        final File file = loader.fileCache.getFile(uri);

        try {

            URL imageUrl = new URL(this.uri);
            HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is = conn.getInputStream();
            OutputStream os = new FileOutputStream(file);
            Utils.CopyStream(is, os);
            os.close();

            request.onSuccess(loader.decodeFile(file));

        } catch (Throwable ex){

            ex.printStackTrace();
            if(ex instanceof OutOfMemoryError)
                loader.memoryCache.clear();

            request.onFailure(ex);
        }
    }
}
