package speedbars.simplewaiter_client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.*;

import java.io.IOException;

import client.SimpleWaiterClient;
import speedbars.simplewaiter_client.R;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void onAnzeige(View v)
    {
        Intent intent = new Intent(this, Anzeige.class);
        startActivity(intent);
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

    public void onTest(View v)
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
