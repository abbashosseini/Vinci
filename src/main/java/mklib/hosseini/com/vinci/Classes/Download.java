package mklib.hosseini.com.vinci.Classes;

import android.graphics.Bitmap;

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

    final String uri;
    final Loader loader;

    public Download(String uri, Loader loader){
        this.uri = uri;
        this.loader = loader;
    }

//
//    public Bitmap Load(){
//
//        File f = loader.FileCaching.getFile(uri);
//
//        try {
//            Bitmap bitmap = null;
//            URL imageUrl = new URL(this.uri);
//            HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
//            conn.setConnectTimeout(30000);
//            conn.setReadTimeout(30000);
//            conn.setInstanceFollowRedirects(true);
//            InputStream is=conn.getInputStream();
//            OutputStream os = new FileOutputStream(f);
//            Utils.CopyStream(is, os);
//            os.close();
//            bitmap = loader.decodeFile(f);
//            return bitmap;
//        } catch (Throwable ex){
//            ex.printStackTrace();
//            if(ex instanceof OutOfMemoryError)
//                loader.memoryCache.clear();
//            return null;
//        }
//    }

    @Override
    public Bitmap call() throws Exception {

        File f = loader.fileCache.getFile(uri);

        try {
            Bitmap bitmap = null;
            URL imageUrl = new URL(this.uri);
            HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is=conn.getInputStream();
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

/*
    class producer implements Runnable{


        final consuming consuming;
        producer(consuming consumer){
            this.consuming = consumer;
        }

        @Override
        public void run() {

            File f = loader.FileCaching.getFile(uri);

            //from web
            try {
                Bitmap bitmap = null;
                URL imageUrl = new URL(uri);
                HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
                conn.setConnectTimeout(30000);
                conn.setReadTimeout(30000);
                conn.setInstanceFollowRedirects(true);
                InputStream is = conn.getInputStream();
                OutputStream os = new FileOutputStream(f);
                Utils.CopyStream(is, os);
                os.close();
                bitmap = loader.decodeFile(f);
                consuming.add(bitmap);
            } catch (Throwable ex){
                ex.printStackTrace();
                if(ex instanceof OutOfMemoryError)
                    loader.memoryCache.clear();
            }

        }
    }

    class consuming implements Callable<Bitmap> {

        final BlockingQueue<Bitmap> queue = new LinkedBlockingQueue<>();

        public void add(Bitmap bitmap){
            try {
                queue.put(bitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Bitmap call() throws Exception {
            return queue.take();
        }
    }
    */
}
