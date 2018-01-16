package bl;

import java.text.SimpleDateFormat;
import java.util.*;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.*;
import beans.Bestellung;

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
        orders.add(0, order);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sort()
    {
        orders.sort((o1, o2) -> o2.getBestellzeit().compareTo(o1.getBestellzeit()));
    }

    public String formatDate(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM:ss    dd.MM.yy");
        return sdf.format(date);
    }
}
