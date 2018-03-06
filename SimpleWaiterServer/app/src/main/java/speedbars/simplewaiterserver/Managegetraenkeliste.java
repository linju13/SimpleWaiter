package speedbars.simplewaiterserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

import beans.Getraenk;
import beans.Getraenkelist;
import xml.XMLAccess;

public class Managegetraenkeliste extends AppCompatActivity {


    private XMLAccess xml;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managegetraenkeliste);


        ListView listView = (ListView) findViewById(R.id.listview);

        String path = "/Users/Laura/Desktop/GitHub/SimpleWaiter/SimpleWaiterServer/app/src/main/res/getraenke.xml";
        xml = XMLAccess.getInstance(path);
       Getraenkelist g = null;
        try {
            g = xml.unmarshall();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        List<String> list = new LinkedList<>();

        for(Getraenk ge : g.getGetraenke())
        {
            list.add(ge.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, list);


        // Assign adapter to ListView
        listView.setAdapter(adapter);


    }


    public void onSave(View view)
    {

    }



    public void onLoad(View view)
    {

    }
}
