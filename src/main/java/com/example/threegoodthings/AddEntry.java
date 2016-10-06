package com.example.threegoodthings;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.threegoodthings.MemoContract.*;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ορφέας Τσαρτσιανίδης on 5/13/2016.
 */
public class AddEntry extends AppCompatActivity {
    private MemoDBHelper dbHelper;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        Intent intent = getIntent();
        id = intent.getLongExtra("key1", -99);
        if (id != -99)
        {
            String[] projection = new String[]{
                    MemoContract.MemoEntry._ID,
                    MemoContract.MemoEntry.COLUMN_NAME_DATE,
                    MemoContract.MemoEntry.COLUMN_NAME_TEXT1,
                    MemoContract.MemoEntry.COLUMN_NAME_TEXT2,
                    MemoContract.MemoEntry.COLUMN_NAME_TEXT3
            };

            dbHelper = new MemoDBHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(MemoContract.MemoEntry.TABLE_NAME, projection, "_ID = ?", new String[]{"" + id}, null, null, null, null);
            cursor.moveToFirst();

            String date = cursor.getString(cursor.getColumnIndex(MemoContract.MemoEntry.COLUMN_NAME_DATE));
            EditText etDate = (EditText) findViewById(R.id.date);
            etDate.setText(date);

            String text1 = cursor.getString(cursor.getColumnIndex(MemoContract.MemoEntry.COLUMN_NAME_TEXT1));
            EditText etText1 = (EditText) findViewById(R.id.text1);
            etText1.setText(text1);

            String text2 = cursor.getString(cursor.getColumnIndex(MemoContract.MemoEntry.COLUMN_NAME_TEXT2));
            EditText etText2 = (EditText) findViewById(R.id.text2);
            etText2.setText(text2);

            String text3 = cursor.getString(cursor.getColumnIndex(MemoContract.MemoEntry.COLUMN_NAME_TEXT3));
            EditText etText3 = (EditText) findViewById(R.id.text3);
            etText3.setText(text3);
        }
    }

    public void update(View view)
    {
        // Gets the data repository in write mode
        dbHelper = new MemoDBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        String date = ((EditText) findViewById(R.id.date)).getText().toString();
        values.put(MemoContract.MemoEntry.COLUMN_NAME_DATE, date);

        String text1 = ((EditText) findViewById(R.id.text1)).getText().toString();
        values.put(MemoContract.MemoEntry.COLUMN_NAME_TEXT1, text1);

        String text2 = ((EditText) findViewById(R.id.text2)).getText().toString();
        values.put(MemoContract.MemoEntry.COLUMN_NAME_TEXT2, text2);

        String text3 = ((EditText) findViewById(R.id.text3)).getText().toString();
        values.put(MemoContract.MemoEntry.COLUMN_NAME_TEXT2, text3);

        // Insert the new row, returning the primary key value of the new row
        if (id == -99)
        {
            long newRowId = db.insert(MemoContract.MemoEntry.TABLE_NAME, null, values);
        }
        else
        {
            db.update(MemoContract.MemoEntry.TABLE_NAME, values, "_ID = ?", new String[]{"" + id});
        }
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    public void delete(View view)
    {
        dbHelper = new MemoDBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(id == -99)
        {

        }
        else
        {
            db.delete(MemoContract.MemoEntry.TABLE_NAME, "_ID = ?", new String[]{"" + id});
        }

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}