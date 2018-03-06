package speedbars.simplewaiterserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import speedbars.activities.TableViewActivity;
import xml.XMLAccess;

public class Overview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
    }

    public void onStart(View view)
    {
        Intent intent = new Intent(Overview.this, TableViewActivity.class);
        startActivity(intent);
    }

    public void onKonfig(View view)
    {
    Intent intent = new Intent(Overview.this, GetraenkeKonfig.class);
    startActivity(intent);
    }
}
