package com.example.threegoodthings;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.example.threegoodthings.MemoContract.*;

public class MainActivity extends AppCompatActivity
{

    private SimpleCursorAdapter adapter;
    SQLiteDatabase db;
    String[] projection;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.v("MainActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView) findViewById(R.id.listView);

        MemoDBHelper dbHelper = new MemoDBHelper(this);
        db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        projection = new String[]{MemoEntry._ID, MemoEntry.COLUMN_NAME_DATE};

        // How you want the results sorted in the resulting Cursor
        // String sortOrder = MemoEntry.COLUMN_NAME_TITLE + " DESC";

        Cursor cursor = db.query(
                MemoEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                        // The sort order
        );
        String[] fromColumns = {MemoContract.MemoEntry.COLUMN_NAME_DATE};
        int[] toViews = {R.id.item};
        adapter = new SimpleCursorAdapter(this, R.layout.list_item, cursor, fromColumns, toViews, 0);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, AddEntry.class);
                intent.putExtra("key1", id);
                startActivityForResult(intent, 1);
            }
        });

    }

    public void add(View view)
    {
        Intent intent = new Intent(this, AddEntry.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1)
        {
            if (resultCode == RESULT_OK)
            {
                Cursor cursor = db.query(
                        MemoEntry.TABLE_NAME,  // The table to query
                        projection,                               // The columns to return
                        null,                                // The columns for the WHERE clause
                        null,                            // The values for the WHERE clause
                        null,                                     // don't group the rows
                        null,                                     // don't filter by row groups
                        null                                        // The sort order
                );
                adapter.changeCursor(cursor);
            }
        }
    }
}
