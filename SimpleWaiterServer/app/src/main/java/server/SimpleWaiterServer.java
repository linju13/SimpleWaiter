package server;

import android.graphics.Color;
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
                drawTable();
            }
        }
        catch(Exception ex)
        {

        }

        return null;
    }

    public void drawTable()
    {
        TableLayout table = (TableLayout) activity.findViewById(R.id.tableLayout);

        String[] colors = {"#f2f2f2","#cccccc"};

        TextView view;
        TextView view2;
        TextView view3;
        TableRow row;

        for(int i = 0; i < model.getRowCount(); i++)
        {
            view = new TextView(activity);
            view.setText(model.getValueAt(i,0)+"");
            view.setLayoutParams(activity.findViewById(R.id.bestellid).getLayoutParams());
            view.setMaxWidth(activity.findViewById(R.id.bestellid).getWidth());
            view.setBackgroundColor(Color.parseColor(colors[i%2]));
            view.setPadding(30,0,0,0);

            view2 = new TextView(activity);
            view2.setText(model.getValueAt(i,1)+"");
            view2.setLayoutParams(activity.findViewById(R.id.bestellzeit).getLayoutParams());
            view2.setMaxWidth(activity.findViewById(R.id.bestellzeit).getWidth());
            view2.setBackgroundColor(Color.parseColor(colors[i%2]));
            view2.setPadding(30,0,0,0);

            view3 = new TextView(activity);
            view3.setText(model.getValueAt(i,2)+"");
            view3.setLayoutParams(activity.findViewById(R.id.summe).getLayoutParams());
            view3.setMaxWidth(activity.findViewById(R.id.summe).getWidth());
            view3.setBackgroundColor(Color.parseColor(colors[i%2]));
            view3.setPadding(30,0,0,0);

            row = new TableRow(activity);
            row.addView(view);
            row.addView(view2);
            row.addView(view3);

            table.addView(row);
        }
    }

}
