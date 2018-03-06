
package client;

import android.os.StrictMode;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import beans.Bestellung;
import beans.Command;
import beans.Getraenk;
import beans.Getraenkelist;
import enums.EinheitenEnum;

public class SimpleWaiterClient
{
    //Attribute
    private final static int PORTNR = 9999;
    private final static String HOST_NAME = "localhost";
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private static SimpleWaiterClient theInstance;

    private SimpleWaiterClient()
    {

    }

    public Getraenkelist connect()
    {
        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);

            socket = new Socket(HOST_NAME, PORTNR);

            reader = new ObjectInputStream(socket.getInputStream());
            writer = new ObjectOutputStream(socket.getOutputStream());

            Getraenkelist list =  (Getraenkelist) reader.readObject();
            Log.e("Client-----", list.toString());
            return list;
        }
        catch(Exception ex)
        {
            Log.e("Client-----", ex.toString());
            return null;
        }
    }


    static int i = 0;

    public void send(Bestellung order) throws IOException
    {
        writer.writeObject(Command.NEW_ORDER);
        writer.writeObject(order);
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

    public static SimpleWaiterClient getTheInstance()
    {
        if(theInstance == null)
        {
            theInstance = new SimpleWaiterClient();
        }

        return theInstance;
    }

}
