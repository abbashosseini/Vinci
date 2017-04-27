package mklib.hosseini.com.vinci.Classes;

import android.graphics.Bitmap;

/**
 * Created by abbas on 4/14/16.
 */
public interface Request {

    void onSuccess(Bitmap bitmap);

    void onFailure(Throwable e);

}
