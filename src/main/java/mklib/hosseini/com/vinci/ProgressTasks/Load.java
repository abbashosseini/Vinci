package mklib.hosseini.com.vinci.ProgressTasks;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import mklib.hosseini.com.vinci.Callbacks.ExecuteResult;
import mklib.hosseini.com.vinci.Callbacks.ResultProcess;


public class  Load extends AsyncTask<String, Void, byte[]> {

    private static ResultProcess delegate;
    private static ExecuteResult byteArray;

    public Load(ResultProcess asyncResponse) {
        delegate = asyncResponse;
    }

    @Override
    protected synchronized byte[] doInBackground(String... params) {

        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();

        try {  /*
                * get URL for downloading image from internet and convert it to Byte ARRAY
                * and put it in BLOB field ( database ).
                */
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            assert connection != null;
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();

            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            
            int len = 0;
            while ((len = input.read(buffer)) != -1) {
                byteArray.write(buffer, 0, len);
            }

            input.close();
            connection.disconnect();

            return byteArray.toByteArray();

        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected synchronized void onPostExecute(byte[] result) {
        // In onPostExecute we check if the listener is valid
        // And if it is we call the callback function on it.
        if(delegate != null)
                delegate.onFinish(result);
    }


    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}

    public static Load load(ExecuteResult onReady){
        byteArray = onReady;

        return new Load(new ResultProcess() {
            @Override
            public void onFinish(byte[] output) {

                byteArray.OnReady(output);

            }
        });
    }

}
