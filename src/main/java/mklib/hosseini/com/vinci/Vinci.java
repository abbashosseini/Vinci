package mklib.hosseini.com.vinci;

import android.content.Context;

import mklib.hosseini.com.vinci.Classes.Loader;

/**
* <p>
*   this is main class of VinCi library and with this class you can access the
*   any aoi VinCi represent and its create it with singleton pattern permission
* </p>
*
* @author      Abbas hosseini
* @version     1.0
* @since       2016-04-18
*
**/

public class Vinci {

    protected static volatile Vinci Base_singleton;
    protected final Context ctxt;

    Vinci(Context context) {
        this.ctxt = context;
    }

    /**
    * create correct vinci object and make sure have one true instance
    *
    * @return Vinci object
    **/
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

    /**
    * <p>
    *   this method you can access it Builder pattern style
    *   and you can access all emthods and api
    * </p>
    *
    * @author      Abbas hosseini
    * @version     1.0
    * @since       2016-04-18
    * @return      Loader object
    *
    **/

    public Loader process(){
       return new Loader(this.ctxt);
    }


}
