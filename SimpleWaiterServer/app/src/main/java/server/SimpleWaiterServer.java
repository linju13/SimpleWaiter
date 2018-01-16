package server;

import android.graphics.Color;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Random;
import java.io.*;

import beans.Bestellung;
import beans.Command;
import bl.SimpleWaiterTableModel;
import speedbars.activities.TableViewActivity;
import speedbars.simplewaiterserver.R;

/**
 * Created by jürgen on 14.11.2017.
 */
public class SimpleWaiterServer extends SimpleServer
{
    //Attribute
    private Set<Integer> id;
    private Random rand;
    private SimpleWaiterTableModel model;
    private TableViewActivity activity;

    public SimpleWaiterServer(SimpleWaiterTableModel model, TableViewActivity activity)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        id = new HashSet<Integer>();
        this.model = model;
        this.activity = activity;
    }

    @Override
    protected void log(String text){}

    @Override
    protected String action(Socket socket, Scanner scanner)
    {
        try
        {
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());

            Bestellung bestellung;

            while(!reader.readObject().toString().equals(Command.DISCONNECT))
            {
                bestellung = (Bestellung) reader.readObject();

                model.addOrder(bestellung);
                activity.drawTable(bestellung, activity, model);
            }
        }
        catch(Exception ex)
        {
            Log.e("server", ex.toString());
        }

        return null;
    }
}
