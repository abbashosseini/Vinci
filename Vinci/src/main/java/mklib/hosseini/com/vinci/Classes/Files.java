package mklib.hosseini.com.vinci.Classes;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by abbas on 4/21/16.
 */
public class Files {


    /* default constructor*/

    /**
     *
     * access folder your put image you like in it (Internal Storage / vinsi / Files)
     *
     * @see Storage
     **/
    public File[] grapStorePath() {
        return Storage.LocalPath().listFiles();
    }

    public List<File> list() {
        return Arrays.asList(grapStorePath());
    }




}
