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

    public static String path;
}
