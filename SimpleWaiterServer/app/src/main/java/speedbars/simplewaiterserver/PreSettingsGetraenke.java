package speedbars.simplewaiterserver;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;

import beans.Getraenk;
import beans.Getraenkelist;
import enums.EinheitenEnum;
import server.ApplicationVariables;

public class PreSettingsGetraenke extends AppCompatActivity {

    private ListenAdapter adapter;

    private ExpandableListView lview;

    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_settings_getraenke);

        //TODO Listen holen @Laura



        Getraenkelist list = new Getraenkelist("List 1");
        list.addGeatraenk(new Getraenk("Rum Cola",0.3,2.75, EinheitenEnum.LITER));
        list.addGeatraenk(new Getraenk("Vodka Cola",0.3,2.75, EinheitenEnum.LITER));
        list.addGeatraenk(new Getraenk("Vodka Orange",0.3,3, EinheitenEnum.LITER));
        list.addGeatraenk(new Getraenk("Malibu Orange",0.3,5, EinheitenEnum.LITER));
        list.addGeatraenk(new Getraenk("Wasser",0.3,5, EinheitenEnum.LITER));
        list.addGeatraenk(new Getraenk("Wein",0.3,5, EinheitenEnum.LITER));
        list.addGeatraenk(new Getraenk("Saft",0.3,5, EinheitenEnum.LITER));

        Getraenkelist list1 = new Getraenkelist("List 2");
        list1.addGeatraenk(new Getraenk("Rum Cola",0.3,2.75, EinheitenEnum.LITER));
        list1.addGeatraenk(new Getraenk("Vodka Cola",0.3,2.75, EinheitenEnum.LITER));
        list1.addGeatraenk(new Getraenk("Vodka Orange",0.3,3, EinheitenEnum.LITER));
        list1.addGeatraenk(new Getraenk("Malibu Orange",0.3,5, EinheitenEnum.LITER));
         List<Getraenkelist> lists = new LinkedList<>();

        lists.add(list);
        lists.add(list1);
        adapter = new ListenAdapter(this, lists);


        lview = findViewById(R.id.expandList);
        lview.setAdapter(adapter);

        List<String> listen = new LinkedList<>();

        for(Getraenkelist g: lists)
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
