
package client;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.Bestellung;
import beans.Command;
import beans.Getraenk;
import enums.EinheitenEnum;

public class SimpleWaiterClient
{
    //Attribute
    private final static int PORTNR = 9999;
    private final static String HOST_NAME = "localhost";
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;

    public boolean connect()
    {
        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);

            socket = new Socket(HOST_NAME, PORTNR);
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return true;
        }

        return false;
    }
    static int i = 0;

    public void send() throws IOException {
        writer = new ObjectOutputStream(socket.getOutputStream());
        writer.writeObject(Command.NEW_ORDER);
        i += 10;
        Map<Getraenk, Integer> orderMap = new HashMap();
        orderMap.put(new Getraenk("Rum Cola", 0.25, 3.20, EinheitenEnum.LITER), 3);
        orderMap.put(new Getraenk("Klopfer", 0.15, 2, EinheitenEnum.LITER), 1);
        orderMap.put(new Getraenk("Vodka Orange", 0.25, 4, EinheitenEnum.LITER), 2);
        writer.writeObject(new Bestellung(new Date() ,orderMap, 20000+i, 1));
    }

    public void close()
    {
        try
        {
            writer.writeObject(Command.DISCONNECT);
            socket.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
    }

}
