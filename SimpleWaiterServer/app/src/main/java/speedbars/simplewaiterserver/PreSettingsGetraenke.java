package speedbars.simplewaiterserver;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import beans.Getraenk;
import beans.Getraenkelist;
import enums.EinheitenEnum;
import server.ApplicationVariables;
import xml.XMLAccess;

public class PreSettingsGetraenke extends AppCompatActivity {

    private ListenAdapter adapter;

    private ExpandableListView lview;

    private ArrayAdapter<String> arrayAdapter;

    private List<Getraenkelist> getraenkelists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_settings_getraenke);

        //TODO Listen holen @Laura

        File deinRootDir = new File( Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS) +"/SimpleWaiterServer");
        ArrayList<String> files = new ArrayList<String>
                ();
        deinRootDir.listFiles(new FileFilter(){
            public boolean accept(File f){
                if(f.getName().endsWith(".xml")) {
                    files.add(f.getAbsolutePath());
                }
                return false;
            }} );

        if(files.size() > 0) {
            for (String filename : files)
            {

                XMLAccess x = XMLAccess.getInstance();
            Getraenkelist g = null;
                try {
                    g =  x.unmarshall(filename);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                getraenkelists.add(g);
            }
        }

        adapter = new ListenAdapter(this, getraenkelists);


        lview = findViewById(R.id.expandList);
        lview.setAdapter(adapter);

        List<String> listen = new LinkedList<>();

        for(Getraenkelist g: getraenkelists)
        {
            listen.add(g.getName());
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listen);
        Spinner spinner = findViewById(R.id.listen);
        spinner.setAdapter(arrayAdapter);
    }

    public void onAuswahl(View view)
    {
        Spinner spinner = findViewById(R.id.listen);
        int i = spinner.getSelectedItemPosition();
        if( i > -1) {
            ApplicationVariables.setG((Getraenkelist) adapter.getGroup(i));
            Intent intent = new Intent();
            intent.putExtra("getraenkeliste",(Getraenkelist) adapter.getGroup(i));
            setResult(Activity.RESULT_OK, intent);
        }
        else
        {

            setResult(Activity.RESULT_CANCELED);
        }


        this.finish();
    }
}
