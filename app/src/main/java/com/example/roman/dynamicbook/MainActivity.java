package com.example.roman.dynamicbook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = this;
        db = new PagesDBController(this);
        allPages = db.getAllPages();

        add_bt = (Button) findViewById(R.id.add_page_bt);
        pages_lv = (ListView) findViewById(R.id.pages_lv);

        adapter = new PagesArrayAdapter(this, allPages);
        pages_lv.setAdapter(adapter);

        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater factory = LayoutInflater.from(c);
                final View alertDialogView = factory.inflate(R.layout.popup, null);

                AlertDialog.Builder adb = new AlertDialog.Builder(c);
                adb.setView(alertDialogView);
                adb.setTitle(R.string.add_page);
                adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        EditText et = (EditText)alertDialogView.findViewById(R.id.TextContent);
                        if (et.getText().toString() == "")
                            return;
                        Page page = new Page(-1, 1,et.getText().toString());
                        db.addPage(page);
                    } });
                adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    } });
                adb.show();
            }
        });

        pages_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterview, View view, int pos, long id)
            {
                final Page clicked = allPages.get(pos);
                Intent intent = new Intent(MainActivity.this, PageActivity.class);
                intent.putExtra("page", clicked);
                startActivity(intent);
            }
        });

    }

    PagesArrayAdapter adapter;
    ArrayList<Page> allPages;
    PagesDBController db;
    Context c;
    Button add_bt;
    ListView pages_lv;
}
