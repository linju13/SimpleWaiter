package speedbars.simplewaiterserver;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

import beans.Getraenk;
import beans.Getraenkelist;
import enums.EinheitenEnum;
import server.ApplicationVariables;
import xml.XMLAccess;

public class GetraenkeKonfig extends Activity {

    private GetraenkeAdapter getraenkeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getraenke_konfig);

       Getraenk g = (Getraenk) getIntent().getSerializableExtra("getraenk");


        ListView listView = (ListView) findViewById(R.id.getraenklistview);

        LinkedList<Getraenk> getraenke = new LinkedList<>();

        //public Getraenk(String name, double menge, double preis, EinheitenEnum einheit) {
       /* getraenke.add(new Getraenk("Bacardi Cola", 0.3, 3.5, EinheitenEnum.LITER));
        getraenke.add(new Getraenk("Soda", 0.3, 0.5, EinheitenEnum.LITER));
        getraenke.add(new Getraenk("Soda", 0.5, 1, EinheitenEnum.LITER));
        getraenke.add(new Getraenk("Captain Cola", 0.3, 4, EinheitenEnum.LITER));
        getraenke.add(new Getraenk("Tequila", 2, 2.5, EinheitenEnum.CL));
*/

        getraenkeAdapter = new GetraenkeAdapter(getApplicationContext(), getraenke);


        if(g != null) {
            getraenkeAdapter.addGetraenk(g);
        }
        listView.setAdapter(getraenkeAdapter);
    }

    public void onSave(View view)
    {
        String name = ((EditText) findViewById(R.id.listname)).getText().toString();

        if(name != null && !name.equals("")) {
            name += ".xml";


            XMLAccess xml = XMLAccess.getInstance(ApplicationVariables.getPath() + name);
            Getraenkelist getraenkelist = new Getraenkelist();

            for (Getraenk g : getraenkeAdapter.getGetraenke()) {
                getraenkelist.addGeatraenk(g);
            }

            try {
                xml.marshall(getraenkelist);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(this, "Bitte einen Namen der Liste eingeben um zu speichern!", Toast.LENGTH_LONG);
        }

    }


    public void onLoad(View view)
    {
        
    }


    public void onAdd(View view)
    {
        Intent intent = new Intent(GetraenkeKonfig.this, AddGetraenk.class);
        startActivity(intent);
    }


    public void onDel(View view)
    {

    }



}
