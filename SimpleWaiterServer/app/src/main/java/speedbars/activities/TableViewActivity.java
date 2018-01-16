package speedbars.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.*;
import beans.*;
import java.util.*;

import bl.SimpleWaiterTableModel;
import server.SimpleWaiterServer;
import speedbars.simplewaiterserver.R;

public class TableViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);

        SimpleWaiterTableModel model = new SimpleWaiterTableModel();

        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));

        SimpleWaiterServer server = new SimpleWaiterServer(model , this);
        server.startServer();
        //drawTable(model, this, model);
    }

    public void drawTable(Bestellung bestellung, TableViewActivity tableview, SimpleWaiterTableModel model)
    {
        new Thread() {
            public void run() {
                try {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run()
                        {
                            TableLayout table = (TableLayout) tableview.findViewById(R.id.tableLayout);

                            String[] colors = {"#f2f2f2","#cccccc"};

                            TextView view;
                            TextView view2;
                            TextView view3;
                            TableRow row;

                            view = new TextView(tableview);
                            view.setText(bestellung.getBestellid());
                            view.setLayoutParams(tableview.findViewById(R.id.bestellid).getLayoutParams());
                            view.setMaxWidth(tableview.findViewById(R.id.bestellid).getWidth());
                            view.setBackgroundColor(Color.parseColor(colors[model.getRowCount()%2]));
                            view.setPadding(30,0,0,0);

                            view2 = new TextView(tableview);
                            view2.setText(model.formatDate(bestellung.getBestellzeit()));
                            view2.setLayoutParams(tableview.findViewById(R.id.bestellzeit).getLayoutParams());
                            view2.setMaxWidth(tableview.findViewById(R.id.bestellzeit).getWidth());
                            view2.setBackgroundColor(Color.parseColor(colors[model.getRowCount()%2]));
                            view2.setPadding(30,0,0,0);

                            view3 = new TextView(tableview);
                            view3.setText(String.format("%6.2f €", bestellung.getGesamtSumme()));
                            view3.setLayoutParams(tableview.findViewById(R.id.summe).getLayoutParams());
                            view3.setMaxWidth(tableview.findViewById(R.id.summe).getWidth());
                            view3.setBackgroundColor(Color.parseColor(colors[model.getRowCount()%2]));
                            view3.setPadding(30,0,0,0);

                            row = new TableRow(tableview);
                            row.addView(view);
                            row.addView(view2);
                            row.addView(view3);

                            table.addView(row, 1);
                        }
                    });
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /*public void help()
    {
        ableLayout table = (TableLayout) tableview.findViewById(R.id.tableLayout);

        String[] colors = {"#f2f2f2","#cccccc"};

        TextView view;
        TextView view2;
        TextView view3;
        TableRow row;

        for(int i = 0; i < model.getRowCount(); i++)
        {
            view = new TextView(tableview);
            view.setText(model.getValueAt(i,0)+"");
            view.setLayoutParams(tableview.findViewById(R.id.bestellid).getLayoutParams());
            view.setMaxWidth(tableview.findViewById(R.id.bestellid).getWidth());
            view.setBackgroundColor(Color.parseColor(colors[i%2]));
            view.setPadding(30,0,0,0);

            view2 = new TextView(tableview);
            view2.setText(model.getValueAt(i,1)+"");
            view2.setLayoutParams(tableview.findViewById(R.id.bestellzeit).getLayoutParams());
            view2.setMaxWidth(tableview.findViewById(R.id.bestellzeit).getWidth());
            view2.setBackgroundColor(Color.parseColor(colors[i%2]));
            view2.setPadding(30,0,0,0);

            view3 = new TextView(tableview);
            view3.setText(model.getValueAt(i,2)+"");
            view3.setLayoutParams(tableview.findViewById(R.id.summe).getLayoutParams());
            view3.setMaxWidth(tableview.findViewById(R.id.summe).getWidth());
            view3.setBackgroundColor(Color.parseColor(colors[i%2]));
            view3.setPadding(30,0,0,0);

            row = new TableRow(tableview);
            row.addView(view);
            row.addView(view2);
            row.addView(view3);

            table.addView(row, 1);
        }
    }*/
}
