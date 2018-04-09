package speedbars.simplewaiterserver;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;



import beans.Getraenk;
import beans.Getraenkelist;
import enums.EinheitenEnum;
import server.ApplicationVariables;
import speedbars.activities.TableViewActivity;
import xml.XMLAccess;

public class Overview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        ApplicationVariables.path = this.getDataDir()  + File.separator;

        Getraenkelist getraenke = new Getraenkelist("List 1");
       getraenke.addGeatraenk(new Getraenk("Bacardi Cola", 0.3, 3.5, EinheitenEnum.LITER));
        getraenke.addGeatraenk(new Getraenk("Soda", 0.3, 0.5, EinheitenEnum.LITER));
        getraenke.addGeatraenk(new Getraenk("Soda", 0.5, 1, EinheitenEnum.LITER));
        getraenke.addGeatraenk(new Getraenk("Captain Cola", 0.3, 4, EinheitenEnum.LITER));
        getraenke.addGeatraenk(new Getraenk("Tequila", 2, 3.0, EinheitenEnum.CL));



        XMLAccess xml = XMLAccess.getInstance();

        try
        {
            xml.marshall(getraenke, ApplicationVariables.getPath() + "test.xml");
        }
        catch (Exception e)
        {
            Log.e("Marshall", e.toString());
        }

        try
        {
            for(Getraenk getraenk: xml.unmarshall(ApplicationVariables.getPath() + "test.xml").getGetraenke())
            {
                Log.e("Getraenk:", getraenk.toString());
            }
        }
        catch (Exception e)
        {
            Log.e("Unmarshall", e.getMessage());
        }
    }

    private static int PRESETTING = 150;
    public void onServer(View view)
    {
        Intent intent = new Intent(Overview.this, PreSettingsGetraenke.class);
        startActivityForResult(intent, PRESETTING);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PRESETTING)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                Toast.makeText(this, "Liste pre-setted", Toast.LENGTH_LONG);
                Intent intent = new Intent(Overview.this, TableViewActivity.class);
                startActivity(intent);
            }
            if(resultCode == Activity.RESULT_CANCELED)
            {
                Toast.makeText(this, "Es ist ein Problem beim Pre-Setting der Liste aufgetreten.", Toast.LENGTH_LONG);

            }
        }
    }

    public void onKonfig(View view)
    {
    Intent intent = new Intent(Overview.this, GetraenkeKonfig.class);
    startActivity(intent);
    }
}
