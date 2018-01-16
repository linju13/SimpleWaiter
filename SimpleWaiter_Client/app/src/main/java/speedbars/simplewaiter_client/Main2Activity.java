package speedbars.simplewaiter_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

import client.SimpleWaiterClient;
import android.content.*;
import android.widget.*;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anzeige);
    }

    public void onKassa(View v)
    {
        Intent intent = new Intent(this, Kassa.class);
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
        client.connect();
        try
        {
            client.send();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
