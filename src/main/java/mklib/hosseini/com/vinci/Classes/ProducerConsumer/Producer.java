package mklib.hosseini.com.vinci.Classes.ProducerConsumer;

/**
 * Created by abbas on 4/14/16.
 */
class Producer implements Runnable {

    final Consumer consumer;

    Producer(Consumer consuming) {
        this.consumer = consuming;
    }

    @Override
    public void run() {


    }
}
