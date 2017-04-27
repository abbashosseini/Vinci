package mklib.hosseini.com.vinci.Classes.Exceptions;

/**
 * Created by abbas on 4/14/16.
 */
public class NetworkException extends IllegalAccessError {

    public NetworkException() {}

    public NetworkException(String detailMessage) {
        super(detailMessage);
    }

}
