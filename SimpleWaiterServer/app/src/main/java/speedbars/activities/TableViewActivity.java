package speedbars.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
        SimpleWaiterServer server = new SimpleWaiterServer(model , this);
        server.startServer();
        //drawTable(model, this, model);
    }

    public void drawTable(final Bestellung bestellung, final TableViewActivity tableview, final SimpleWaiterTableModel model)
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
                            view.setTextSize(30);

                            view2 = new TextView(tableview);
                            view2.setText(model.formatDate(bestellung.getBestellzeit()));
                            view2.setLayoutParams(tableview.findViewById(R.id.bestellzeit).getLayoutParams());
                            view2.setMaxWidth(tableview.findViewById(R.id.bestellzeit).getWidth());
                            view2.setBackgroundColor(Color.parseColor(colors[model.getRowCount()%2]));
                            view2.setPadding(30,0,0,0);
                            view2.setTextSize(30);

                            view3 = new TextView(tableview);
                            view3.setText(String.format("%6.2f €", bestellung.getGesamtSumme()));
                            view3.setLayoutParams(tableview.findViewById(R.id.summe).getLayoutParams());
                            view3.setMaxWidth(tableview.findViewById(R.id.summe).getWidth());
                            view3.setBackgroundColor(Color.parseColor(colors[model.getRowCount()%2]));
                            view3.setPadding(30,0,0,0);
                            view3.setTextSize(30);

                            row = new TableRow(tableview);
                            row.addView(view);
                            row.addView(view2);
                            row.addView(view3);
                            row.setTransitionName(model.getRowCount()-1+"");
                            table.addView(row, 1);

                            row.setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                    drawDetails(Integer.parseInt(v.getTransitionName()), model, tableview);
                                }
                            });
                        }
                    });
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void drawDetails(final int index, final SimpleWaiterTableModel model, final TableViewActivity tableview)
    {
        new Thread() {
            public void run() {
                try {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run()
                        {
                            AlertDialog alert = new AlertDialog.Builder(TableViewActivity.this)
                                    .setTitle(model.getValueAt(index,0)+"")
                                    .setMessage(model.toStringOrder(index))

                                    .setPositiveButton("ok", new DialogInterface.OnClickListener()
                                    {
                                        public void onClick(DialogInterface dialog, int whichButton)
                                        {


                                        }
                                    }).show();

                            TextView titleTxt = (TextView) alert.findViewById(android.R.id.title);

                            try
                            {
                                titleTxt.setTextSize(40.0f);
                                titleTxt.setTextColor(0xff9900);
                            }
                            catch(Exception ex)
                            {

                            }


                            TextView msgTxt = (TextView) alert.findViewById(android.R.id.message);
                            msgTxt.setTextSize(22.0f);

                            Typeface face = Typeface.createFromAsset(getAssets(),
                                    "fonts/Courier New.ttf");
                            msgTxt.setTypeface(face);

                        }
                    });
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


}
