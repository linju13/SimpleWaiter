package speedbars.simplewaiter_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import beans.Getraenkelist;
import client.SimpleWaiterClient;
import android.content.*;
import android.widget.*;

public class Main2Activity extends AppCompatActivity
{
    //Attribute
    private Getraenkelist list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anzeige);
    }

    public void onKassa(View v)
    {
        Intent intent = new Intent(this, Kassa.class);
        intent.putExtra("list", list);
        startActivity(intent);
    }

    public void onTasten(View v)
    {
        Intent intent = new Intent(this, Tasten.class);
        startActivity(intent);
    }

    public void onSend(View v)
    {
        SimpleWaiterClient client = new SimpleWaiterClient();
        list = client.connect();
        Log.e("Client-----", list.getGetraenke().toString());
    }
}
