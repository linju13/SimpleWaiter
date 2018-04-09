package speedbars.simplewaiterserver;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

import beans.Getraenk;
import beans.Getraenkelist;
import server.ApplicationVariables;
import xml.XMLAccess;


public class GetraenkeKonfig extends Activity {

    private GetraenkeAdapter getraenkeAdapter;

    private ListView listView;

    private Getraenkelist gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getraenke_konfig);

         listView = (ListView) findViewById(R.id.getraenklistview);


        gl = new Getraenkelist("list 1");

        getraenkeAdapter = new GetraenkeAdapter(getApplicationContext(), gl.getGetraenke());


       listView.setAdapter(getraenkeAdapter);
    }

    public void onSave(View view)
    {
        String name = ((EditText) findViewById(R.id.listname)).getText().toString();

        if(name != null && !name.equals("")) {
            if(!name.endsWith(".xml")) {
                name += ".xml";
            }

            XMLAccess xml = XMLAccess.getInstance();
            Getraenkelist getraenkelist = new Getraenkelist(name);

            for (Getraenk g : getraenkeAdapter.getGetraenke())
            {
                getraenkelist.addGeatraenk(g);
            }

            try
            {
                //TODO @Laura wirklich gespeichert???
                ContextWrapper cw = new ContextWrapper(getApplicationContext());
                File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                File mypath = new File(directory, name);

            xml.marshall(getraenkelist, mypath.getAbsolutePath());
            }
            catch (Exception e)
            {
                Log.e("Marshall", e.toString());
            }
        }
        else
        {
            Toast.makeText(this, "Bitte einen Namen der Liste eingeben um zu speichern!", Toast.LENGTH_LONG);
        }

    }

    private static int GETLIST = 150;
    public void onLoad(View view)
    {
        Intent intent = new Intent(this, PreSettingsGetraenke.class);
        startActivityForResult(intent, GETLIST);
    }

    private static int ADDDRINK = 100;
    public void onAdd(View view)
    {
        Intent intent = new Intent(GetraenkeKonfig.this, AddGetraenk.class);
        startActivityForResult(intent, ADDDRINK);
    }

    public void onDel(View view)
    {
        getraenkeAdapter.deleteGetraenke();
        listView.setAdapter(getraenkeAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if(requestCode == ADDDRINK) {
        if (resultCode == Activity.RESULT_OK)
        {
            Getraenk getraenk = (Getraenk) data.getSerializableExtra("g");
         getraenkeAdapter.addGetraenk(getraenk);
         getraenkeAdapter.notifyDataSetChanged();
    }
    else
        {
            Toast.makeText(this, "Bitte Daten von neuen Getränken richtig eingeben!", Toast.LENGTH_LONG);
        }
    }
    if(requestCode == GETLIST)
    {
        if(resultCode == Activity.RESULT_OK)
        {
           gl = (Getraenkelist) data.getSerializableExtra("getraenkeliste");
           getraenkeAdapter = new GetraenkeAdapter(getApplicationContext(), gl.getGetraenke());
           listView.setAdapter(getraenkeAdapter);
           TextView tv = findViewById(R.id.listname);
           tv.setText(gl.getName());
        }
    }
    }
}
