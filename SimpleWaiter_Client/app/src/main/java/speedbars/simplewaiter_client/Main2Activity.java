package speedbars.simplewaiter_client;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
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

    public void onConnect(View v)
    {
        SimpleWaiterClient client = SimpleWaiterClient.getTheInstance();
        list = client.connect();
        Log.e("Client-----", list.getGetraenke().toString());
        Intent intent = new Intent(this, Kassa.class);
        intent.putExtra("list", list);
        startActivity(intent);
    }


    public void onFinish(View v)
    {
        finish();
    }
}
