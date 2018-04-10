package speedbars.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import beans.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

import bl.SimpleWaiterTableModel;
import enums.EinheitenEnum;
import server.SimpleWaiterServer;
import speedbars.simplewaiterserver.R;

public class TableViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);

        SimpleWaiterTableModel model = new SimpleWaiterTableModel();
        SimpleWaiterServer server = new SimpleWaiterServer(model , this);
        server.startServer();
        //drawTable(model, this, model);

      /*  Bestellung b = new Bestellung(GregorianCalendar.getInstance().getTime(), 001);
        b.setBestellid("12345");
        b.setGesamtSumme(20);
        b.getraenkHinzufuegen(new Getraenk("Soda", 2, 2, EinheitenEnum.LITER), 5);
     model.addOrder(b);
    drawTable(b, this, model);*/
    }

    public void drawTable(final Bestellung bestellung, final TableViewActivity tableview, final SimpleWaiterTableModel model)
    {
        new Thread() {
            public void run() {
                try {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run()
                        {
                            TableLayout table = (TableLayout) tableview.findViewById(R.id.tableLayout);

                            String[] colors = {"#f2f2f2","#cccccc"};

                            TextView view;
                            TextView view2;
                            TextView view3;
                            TableRow row;

                            view = new TextView(tableview);
                            view.setText(bestellung.getBestellid());
                            view.setLayoutParams(tableview.findViewById(R.id.bestellid).getLayoutParams());
                            view.setMaxWidth(tableview.findViewById(R.id.bestellid).getWidth());
                            view.setBackgroundColor(Color.parseColor(colors[model.getRowCount()%2]));
                            view.setPadding(30,0,0,0);
                            view.setTextSize(30);

                            view2 = new TextView(tableview);
                            view2.setText(model.formatDate(bestellung.getBestellzeit()));
                            view2.setLayoutParams(tableview.findViewById(R.id.bestellzeit).getLayoutParams());
                            view2.setMaxWidth(tableview.findViewById(R.id.bestellzeit).getWidth());
                            view2.setBackgroundColor(Color.parseColor(colors[model.getRowCount()%2]));
                            view2.setPadding(30,0,0,0);
                            view2.setTextSize(30);

                            view3 = new TextView(tableview);
                            view3.setText(String.format("%6.2f €", bestellung.getGesamtSumme()));
                            view3.setLayoutParams(tableview.findViewById(R.id.summe).getLayoutParams());
                            view3.setMaxWidth(tableview.findViewById(R.id.summe).getWidth());
                            view3.setBackgroundColor(Color.parseColor(colors[model.getRowCount()%2]));
                            view3.setPadding(30,0,0,0);
                            view3.setTextSize(30);

                            row = new TableRow(tableview);
                            row.addView(view);
                            row.addView(view2);
                            row.addView(view3);
                            row.setTransitionName(model.getRowCount()-1+"");
                            table.addView(row, 1);

                            row.setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                    drawDetails(Integer.parseInt(v.getTransitionName()), model, tableview);
                                }
                            });
                        }
                    });
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void drawDetails(final int index, final SimpleWaiterTableModel model, final TableViewActivity tableview)
    {
        new Thread() {
            public void run() {
                try {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run()
                        {
                            AlertDialog alert = new AlertDialog.Builder(TableViewActivity.this)
                                    .setTitle(model.getValueAt(index,0)+"")
                                    .setMessage(model.toStringOrder(index))

                                    .setPositiveButton("ok", new DialogInterface.OnClickListener()
                                    {
                                        public void onClick(DialogInterface dialog, int whichButton)
                                        {


                                        }
                                    })
                                    .setNegativeButton("PDF Erstellen", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            try {
                                                createPdfWrapper(model.getBestellung(index));
                                            } catch (FileNotFoundException e) {
                                                e.printStackTrace();
                                            } catch (DocumentException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    })
                                    .show();

                            TextView titleTxt = (TextView) alert.findViewById(android.R.id.title);

                            try
                            {
                                titleTxt.setTextSize(40.0f);
                                titleTxt.setTextColor(0xff9900);
                            }
                            catch(Exception ex)
                            {

                            }


                            TextView msgTxt = (TextView) alert.findViewById(android.R.id.message);
                            msgTxt.setTextSize(22.0f);

                            Typeface face = Typeface.createFromAsset(getAssets(),
                                    "fonts/Courier New.ttf");
                            msgTxt.setTypeface(face);

                        }
                    });
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private File pdfFile;

    private Bestellung b;

    private void createPdfWrapper(Bestellung b) throws FileNotFoundException,DocumentException {
        this.b = b;
        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                         requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);

                                }
                            });
                    return;
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);

        }else {
            createPdf(b);
        }
    }

    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    try {
                        createPdfWrapper(b);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Permission Denied
                    Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private static final String TAG = "TableViewActivity";

    private void createPdf(Bestellung b) throws FileNotFoundException, DocumentException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Created a new directory for PDF");
        }

        pdfFile = new File(docsFolder.getAbsolutePath(),"HelloWorld.pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();

        document.add(new Paragraph("Bestellung Nr."  + b.getBestellid()));
        document.addAuthor("SimpleWaiter APP");
        document.addTitle("Bestellung Nr." + b.getBestellid());
        String context = b.getBestellzeit() + "\n\n";



        document.add(new Paragraph(context));

        document.close();
        previewPdf();

    }

    private void previewPdf() {

        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdfFile);
            intent.setDataAndType(uri, "application/pdf");

            startActivity(intent);
        }else{
            Toast.makeText(this,"Download a PDF Viewer to see the generated PDF",Toast.LENGTH_SHORT).show();
        }
    }

}
