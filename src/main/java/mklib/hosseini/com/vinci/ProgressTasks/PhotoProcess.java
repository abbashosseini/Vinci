package mklib.hosseini.com.vinci.ProgressTasks;

import java.util.concurrent.Callable;

import mklib.hosseini.com.vinci.Callbacks.ResultProcess;
import mklib.hosseini.com.vinci.list.ImmutableList;

/**
 * Created by abbas on 1/7/16.
 */
public class PhotoProcess {

    public static class logger implements Callable<byte[]> {

        final ImmutableList<byte[]> messages = new ImmutableList<>();

        public logger(final String url){

            Load load = new Load(new ResultProcess() {
                @Override
                public void onFinish(byte[] output) {
                    log(output);
                }
            });

            load.execute(url);



        }

        public void log(byte[] message) {
            messages.prepend(message);
        }

        @Override
        public byte[] call() throws Exception {
            return messages.top;
        }
    }


}
