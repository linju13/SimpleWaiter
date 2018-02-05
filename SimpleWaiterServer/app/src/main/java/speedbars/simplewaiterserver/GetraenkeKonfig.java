package speedbars.simplewaiterserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import xml.XMLAccess;

public class GetraenkeKonfig extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getraenke_konfig);
        //makexml
        XMLAccess access = XMLAccess.getInstance("/Users/Laura/Desktop/GitHub/SimpleWaiter/SimpleWaiterServer/app/src/main/res/getraenke.xml");
    }

    public void onSave(View view)
    {

    }
    public void onLoad(View view)
    {

    }
    public void onAdd(View view)
    {
        //TODO ADD Getraenk

    }
    public void onDel(View view)
    {

    }
    public void onFinish(View view)
    {
        //TODO Save
        Intent intent = new Intent(GetraenkeKonfig.this, Overview.class);
        startActivity(intent);
    }


}
