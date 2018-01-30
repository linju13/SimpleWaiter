package speedbars.simplewaiter_client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.util.LinkedList;

import beans.Getraenk;
import beans.Getraenkelist;
import speedbars.simplewaiter_client.R;

public class Kassa extends AppCompatActivity
{
    //Attribute
    private Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kassa);


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
        LinkedList<Getraenk> list = listObject.getGetraenke();

        for(int i = 0; i < list.size(); i++)
        {
            buttons[i/5][i%5].setText(list.get(i).getName());
            buttons[i/5][i%5].setEnabled(true);
        }
    }
}
