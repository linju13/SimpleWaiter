package bl;

import java.text.SimpleDateFormat;
import java.util.*;
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
        orders.add(order);
    }
}
