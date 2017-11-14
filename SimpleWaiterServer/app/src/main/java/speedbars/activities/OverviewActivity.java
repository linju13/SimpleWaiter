package speedbars.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.support.v7.app.ActionBar.*;
import android.graphics.*;
import android.os.Build.VERSION;

import speedbars.simplewaiterserver.R;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);


        TableLayout t1;

        TableLayout tl = (TableLayout) findViewById(R.id.main_table);

        TableRow tr_head = new TableRow(this);
        tr_head.setId(1);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));

        TextView label_date = new TextView(this);
        label_date.setId(20);
        label_date.setText("DATE");
        label_date.setTextColor(Color.WHITE);
        label_date.setPadding(5, 5, 5, 5);
        tr_head.addView(label_date);// add the column to the table row here

        TextView label_weight_kg = new TextView(this);
        label_weight_kg.setId(21);// define id that must be unique
        label_weight_kg.setText("Wt(Kg.)"); // set the text for the header
        label_weight_kg.setTextColor(Color.WHITE); // set the color
        label_weight_kg.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_weight_kg); // add the column to the table row here

        tl.addView(tr_head, new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));

        Integer count=0;
        while (cursor.moveToNext())
        {
            String date = formatdate(cursor.getString(2));// get the first variable
            Double weight_kg = roundTwoDecimals(cursor.getDouble(4));// get the second variable

            // Create the table row
            TableRow tr = new TableRow(this);
            if(count%2!=0) tr.setBackgroundColor(Color.GRAY);
            tr.setId(100+count);
            tr.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));

            //Create two columns to add as table data
            // Create a TextView to add date
            TextView labelDATE = new TextView(this);
            labelDATE.setId(200+count);
            labelDATE.setText(date);
            labelDATE.setPadding(2, 0, 5, 0);
            labelDATE.setTextColor(Color.WHITE);
            tr.addView(labelDATE);
            TextView labelWEIGHT = new TextView(this);
            labelWEIGHT.setId(200+count);
            labelWEIGHT.setText(weight_kg.toString());
            labelWEIGHT.setTextColor(Color.WHITE);
            tr.addView(labelWEIGHT);

            // finally add this to the table row
            tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
            count++;
        }


    }
}
