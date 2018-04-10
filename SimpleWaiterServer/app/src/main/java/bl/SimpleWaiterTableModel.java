package bl;

import java.text.SimpleDateFormat;
import java.util.*;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.*;
import beans.Bestellung;
import beans.Getraenk;

/**
 * Created by jürgen on 14.11.2017.
 */

public class SimpleWaiterTableModel
{
    //Attribute
    private String[] columnNames = {"BestellId", "Zeitpunkt", "Betrag"};
    private ArrayList<Bestellung> orders;

    public SimpleWaiterTableModel()
    {
        orders = new ArrayList();
    }

    public int getRowCount()
    {
        return orders.size();
    }

    public int getColumnCount()
    {
        return columnNames.length;
    }

    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Bestellung order = orders.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM:ss    dd.MM.yy");

        switch(columnIndex)
        {
            case 0: return order.getBestellid();
            case 1: return sdf.format(order.getBestellzeit());
            default: return String.format("%6.2f €", order.getGesamtSumme());
        }
    }

    public void addOrder(Bestellung order)
    {
        orders.add(order);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sort()
    {
        //orders.sort((o1, o2) -> o2.getBestellzeit().compareTo(o1.getBestellzeit()));
    }

    public String formatDate(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM:ss    dd.MM.yy");
        return sdf.format(date);
    }

    public String toStringOrder(int row)
    {
        Bestellung order = orders.get(row);

        Map<Getraenk, Integer> orderMap = order.getGetraenke();
        String output = "";
        String quantity;

        for(Getraenk o: orderMap.keySet())
        {
            quantity = String.format("(%.2f%s)", o.getMenge(), o.getEinheit().getKurzzeichen());

            output += String.format("%20s x %2d = %6.2f €\n", o.getName()+quantity, orderMap.get(o),
                                                           o.getPreis()*orderMap.get(o));
        }

        output += String.format("%20s      = %6.2f €\n", "", order.getGesamtSumme());

        return output;
    }
}
