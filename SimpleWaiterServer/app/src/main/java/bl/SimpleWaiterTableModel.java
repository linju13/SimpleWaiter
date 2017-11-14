package bl;

import java.util.*;
import android.widget.*;

/**
 * Created by jürgen on 14.11.2017.
 */

public class SimpleWaiterTableModel
{
    //Attribute
    private String[] columnNames = {"BestellId", "Zeitpunkt", "Betrag"};
    private ArrayList<Order> orders;

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
        return null;
    }

    public void addOrder(Order order)
    {
        orders.add(order);
    }
}
