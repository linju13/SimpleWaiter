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
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));
        model.addOrder(new Bestellung(new Date(2017, 12, 2, 14, 13, 5) ,null, 134.56, 1));



    }
}
