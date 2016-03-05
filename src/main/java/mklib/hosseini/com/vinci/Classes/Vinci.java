package mklib.hosseini.com.vinci.Classes;

import android.content.Context;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Vinci {

    protected static volatile Vinci Base_singleton;
    protected static volatile Context ctxt;
//    final BlockingQueue<Vinci> queue = new LinkedBlockingQueue<>();

    Vinci(Context context) {
        Vinci.ctxt = context;
    }

    public static Vinci base(Context context) {

        if (Base_singleton == null) {
            synchronized (Vinci.class) {
                if (Base_singleton == null) {
                    Base_singleton = new Vinci(context);
                }
            }
        }
        return Base_singleton;
    }

    public Types type() {

        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Types> future = service.submit(new TypesBuilder(Vinci.ctxt));
        Types type = null;
        try {
            type = future.get(3, TimeUnit.SECONDS);
            service.shutdown();
        } catch (InterruptedException | ExecutionException | TimeoutException ignored) {
        }
        return type;
    }


    public Storage storage() {

        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Storage> future = service.submit(new StorageBuilder(Vinci.ctxt));
        Storage storage = null;
        try {
            storage = future.get(3, TimeUnit.SECONDS);
            service.shutdown();
        } catch (InterruptedException | ExecutionException | TimeoutException ignored) {
        }


        return storage;
    }

    class StorageBuilder extends Storage implements Callable<Storage> {


        StorageBuilder(Context context) {
            super(context);
        }

        @Override
        public Storage call() throws Exception {
            return new Storage(ctxt);
        }
    }

    class TypesBuilder extends Types implements Callable<Types> {

        TypesBuilder(Context context) {
            super(context);
        }

        @Override
        public Types call() throws Exception {
            return new Types(ctxt);
        }

    }
}