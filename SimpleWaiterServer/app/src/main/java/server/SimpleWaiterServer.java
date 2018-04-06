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
import beans.Getraenk;
import beans.Getraenkelist;
import bl.SimpleWaiterTableModel;
import enums.EinheitenEnum;
import speedbars.activities.TableViewActivity;
import speedbars.simplewaiterserver.R;
import xml.XMLAccess;

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

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Getraenkelist list = new Getraenkelist();
            list.addGeatraenk(new Getraenk("Rum Cola",0.3,2.75, EinheitenEnum.LITER));
            list.addGeatraenk(new Getraenk("Vodka Cola",0.3,2.75, EinheitenEnum.LITER));
            list.addGeatraenk(new Getraenk("Vodka Orange",0.3,3, EinheitenEnum.LITER));
            list.addGeatraenk(new Getraenk("Malibu Orange",0.3,5, EinheitenEnum.LITER));
            list.addGeatraenk(new Getraenk("Wasser",0.3,5, EinheitenEnum.LITER));
            list.addGeatraenk(new Getraenk("Wein",0.3,5, EinheitenEnum.LITER));
            list.addGeatraenk(new Getraenk("Saft",0.3,5, EinheitenEnum.LITER));

            writer.writeObject(list);

            Bestellung bestellung;

            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            while(!reader.readObject().toString().equals(Command.DISCONNECT))
            {
                bestellung = (Bestellung) reader.readObject();
                Log.e("-------", bestellung+"");
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
