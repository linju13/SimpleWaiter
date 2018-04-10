package speedbars.simplewaiter_client;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import beans.Bestellung;
import beans.Getraenk;
import beans.Getraenkelist;
import client.SimpleWaiterClient;
import speedbars.simplewaiter_client.R;

public class Kassa extends AppCompatActivity
{
    //Attribute
    private Button[][] buttons;
    private static Bestellung order;
    private LinkedList<Getraenk> list;
    final Kassa kassa = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kassa);


        ((TextView)this.findViewById(R.id.betrag)).setText(String.format("Betrag: %3.2f €", 0.0));

        order = new Bestellung(new Date(), 15);


        buttons = new Button[4][5];
        buttons[0][0] = ((Button)this.findViewById(R.id.B0_0));
        buttons[0][0].setEnabled(false);
        buttons[0][1] = ((Button)this.findViewById(R.id.B0_1));
        buttons[0][1].setEnabled(false);
        buttons[0][2] = ((Button)this.findViewById(R.id.B0_2));
        buttons[0][2].setEnabled(false);
        buttons[0][3] = ((Button)this.findViewById(R.id.B0_3));
        buttons[0][3].setEnabled(false);
        buttons[0][4] = ((Button)this.findViewById(R.id.B0_4));
        buttons[0][4].setEnabled(false);
        buttons[1][0] = ((Button)this.findViewById(R.id.B1_0));
        buttons[1][0].setEnabled(false);
        buttons[1][1] = ((Button)this.findViewById(R.id.B1_1));
        buttons[1][1].setEnabled(false);
        buttons[1][2] = ((Button)this.findViewById(R.id.B1_2));
        buttons[1][2].setEnabled(false);
        buttons[1][3] = ((Button)this.findViewById(R.id.B1_3));
        buttons[1][3].setEnabled(false);
        buttons[1][4] = ((Button)this.findViewById(R.id.B1_4));
        buttons[1][4].setEnabled(false);
        buttons[2][0] = ((Button)this.findViewById(R.id.B2_0));
        buttons[2][0].setEnabled(false);
        buttons[2][1] = ((Button)this.findViewById(R.id.B2_1));
        buttons[2][1].setEnabled(false);
        buttons[2][2] = ((Button)this.findViewById(R.id.B2_2));
        buttons[2][2].setEnabled(false);
        buttons[2][3] = ((Button)this.findViewById(R.id.B2_3));
        buttons[2][3].setEnabled(false);
        buttons[2][4] = ((Button)this.findViewById(R.id.B2_4));
        buttons[2][4].setEnabled(false);
        buttons[3][0] = ((Button)this.findViewById(R.id.B3_0));
        buttons[3][0].setEnabled(false);
        buttons[3][1] = ((Button)this.findViewById(R.id.B3_1));
        buttons[3][1].setEnabled(false);
        buttons[3][2] = ((Button)this.findViewById(R.id.B3_2));
        buttons[3][2].setEnabled(false);
        buttons[3][3] = ((Button)this.findViewById(R.id.B3_3));
        buttons[3][3].setEnabled(false);
        buttons[3][4] = ((Button)this.findViewById(R.id.B3_4));
        buttons[3][4].setEnabled(false);

        Getraenkelist listObject = (Getraenkelist) getIntent().getSerializableExtra("list");
        Log.e("Client-----", listObject.toString());
        list = listObject.getGetraenke();

        for(int i = 0; i < list.size(); i++)
        {
            Log.e(".....", i + " ... " + list.get(i).getName());
            Log.e(".....", i /5+"..."+i%5);
            buttons[i/5][i%5].setText(list.get(i).getName());
            buttons[i/5][i%5].setEnabled(true);
            buttons[i/5][i%5].setTransitionName(i+"");


            buttons[i/5][i%5].setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Log.e("----------", list.get(Integer.parseInt(v.getTransitionName()))+"");
                    order.getraenkHinzufuegen(list.get(Integer.parseInt(v.getTransitionName())), 1);
                    ((TextView)kassa.findViewById(R.id.betrag)).setText(String.format("Betrag: %3.2f €", order.getGesamtSumme()));

                    int i = Integer.parseInt(v.getTransitionName());

                    buttons[i/5][i%5].setText(list.get(i).getName() +  " x " + order.getGetraenke().get(list.get(Integer.parseInt(v.getTransitionName()))));
                }
            });

            buttons[i/5][i%5].setOnLongClickListener(new View.OnLongClickListener()
            {
                public boolean onLongClick(View v)
                {
                    Log.e("----------", list.get(Integer.parseInt(v.getTransitionName()))+"");

                    if(order.getGetraenke().get(list.get(Integer.parseInt(v.getTransitionName()))) != 0)
                    {
                        order.getraenkStornieren(list.get(Integer.parseInt(v.getTransitionName())), 1);
                        ((TextView)kassa.findViewById(R.id.betrag)).setText(String.format("Betrag: %3.2f €", order.getGesamtSumme()));
                        int i = Integer.parseInt(v.getTransitionName());
                        buttons[i/5][i%5].setText(list.get(i).getName() +  " x " + order.getGetraenke().get(list.get(Integer.parseInt(v.getTransitionName()))));
                    }




                    return true;
                }
            });
        }
    }

    public void pay(View view) {
        try {
            order.setBestellzeit(new Date());

            SimpleWaiterClient client = SimpleWaiterClient.getTheInstance();
            client.send(order);
            order = new Bestellung(new Date(), 15);

            ((TextView) kassa.findViewById(R.id.betrag)).setText(String.format("Betrag: %3.2f €", 0.0));

            for (int i = 0; i < list.size(); i++) {
                buttons[i / 5][i % 5].setText(list.get(i).getName());
                buttons[i / 5][i % 5].setEnabled(true);
                buttons[i / 5][i % 5].setTransitionName(i + "");
            }
        } catch (Exception ex) {
            Log.e("senOrder----", ex.toString());
        }
    }

        public void cancel(View view)
        {
            try {
                order = new Bestellung(new Date(), 15);

                ((TextView) kassa.findViewById(R.id.betrag)).setText(String.format("Betrag: %3.2f €", 0.0));

                for (int i = 0; i < list.size(); i++) {
                    buttons[i / 5][i % 5].setText(list.get(i).getName());
                    buttons[i / 5][i % 5].setEnabled(true);
                    buttons[i / 5][i % 5].setTransitionName(i + "");
                }
            } catch (Exception ex) {
                Log.e("senOrder----", ex.toString());
            }
        }

    public void back(View view)
    {
        finish();
    }


}
