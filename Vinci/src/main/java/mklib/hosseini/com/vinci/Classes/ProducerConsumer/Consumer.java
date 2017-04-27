package mklib.hosseini.com.vinci.Classes.ProducerConsumer;

import android.graphics.Bitmap;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import mklib.hosseini.com.vinci.Classes.Request;

/**
 * Created by abbas on 4/14/16.
 */
class Consumer implements Runnable {

    final BlockingQueue<Bitmap> queue = new LinkedBlockingQueue<>();
    final Request request;

    Consumer(Request request){
        this.request = request;
    }

    @Override
    public void run() {




    }

    public void add(){
        try {
            request.onSuccess(queue.take());
        } catch (InterruptedException e) {
            request.onFailure(e);
        }
    }
}