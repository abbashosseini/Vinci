package mklib.hosseini.com.vinci.Classes;

import android.content.Context;

public class Vinci {

    protected static volatile Vinci Base_singleton;
    protected final Context ctxt;

    Vinci(Context context) {
        this.ctxt = context;
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

    public Loader process(){

       return new Loader(this.ctxt);
    }


}