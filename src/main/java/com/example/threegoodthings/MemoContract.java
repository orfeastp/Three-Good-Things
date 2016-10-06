package com.example.threegoodthings;

import android.provider.BaseColumns;

/**
 * Created by Ορφέας Τσαρτσιανίδης on 5/12/2016.
 */
public class MemoContract
{
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public MemoContract() {}

    /* Inner class that defines the table contents */
    public static abstract class MemoEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "memo";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TEXT1 = "text1";
        public static final String COLUMN_NAME_TEXT2 = "text2";
        public static final String COLUMN_NAME_TEXT3 = "text3";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MemoEntry.TABLE_NAME + " (" +
                    MemoEntry._ID + " INTEGER PRIMARY KEY," +
                    MemoEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
                    MemoEntry.COLUMN_NAME_TEXT1 + TEXT_TYPE +  COMMA_SEP +
                    MemoEntry.COLUMN_NAME_TEXT2 + TEXT_TYPE + COMMA_SEP +
                    MemoEntry.COLUMN_NAME_TEXT3 + TEXT_TYPE +" )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MemoEntry.TABLE_NAME;

}