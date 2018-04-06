package server;

import java.io.File;

import beans.Getraenkelist;

/**
 * Created by Laura on 06.02.18.
 */

public class ApplicationVariables {

    private static Getraenkelist g;

    public static Getraenkelist getG() {
        return g;
    }

    public static void setG(Getraenkelist g) {
        ApplicationVariables.g = g;
    }

    public static String getPath() {
        return path;
    }

    private static final String path = System.getProperty("user.dir") + File.separator + "main" + File.separator + "res" + File.separator + "xml" + File.separator;
}
