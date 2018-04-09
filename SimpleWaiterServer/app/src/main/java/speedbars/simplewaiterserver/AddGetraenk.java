package speedbars.simplewaiterserver;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import beans.Getraenk;
import enums.EinheitenEnum;

public class AddGetraenk extends AppCompatActivity {

    private List<EinheitenEnum> einheitenEnums;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_getraenk);

        Spinner spinner = (Spinner) findViewById(R.id.EinheitenSpinner);

        einheitenEnums = new LinkedList<>();

        List<String> einheiten = new LinkedList<>();
        for(EinheitenEnum e : EinheitenEnum.values())
        {
            einheiten.add(e.getBezeichnung());
            einheitenEnums.add(e);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, einheiten);
       spinner.setAdapter(adapter);

    }

    public void onAdd(View v)
    {
        //Adapter add
        Intent intent = new Intent(AddGetraenk.this, GetraenkeKonfig.class);
        Getraenk g = null;
        try {
            String bez = ((EditText) findViewById(R.id.GBez)).getText().toString();
            double menge = Double.parseDouble(((EditText) findViewById(R.id.GMenge)).getText().toString());
        double preis = Double.parseDouble(((EditText) findViewById(R.id.GPreis)).getText().toString());

        Spinner spinner = (Spinner) findViewById(R.id.EinheitenSpinner);
        int i = spinner.getSelectedItemPosition();
        EinheitenEnum e = einheitenEnums.get(i);

        if(!bez.equals("") && menge > 0 && preis > 0 && e != null)
            {
                g = new Getraenk(bez, menge, preis, e);
            }
       }
       catch(Exception ex)
       {
           setResult(-100);
           this.finish();
       }
       Intent intent1 = new Intent();
        intent1.putExtra("g", g);
      setResult(Activity.RESULT_OK, intent1);
        this.finish();
    }

    public void onCancle(View v)
    {
        setResult(Activity.RESULT_CANCELED);
        this.finish();
    }
}
