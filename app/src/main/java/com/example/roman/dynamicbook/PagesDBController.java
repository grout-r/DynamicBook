package com.example.roman.dynamicbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Roman on 09/04/2017.
 */

public class PagesDBController {
    PagesDBController(Context context)
    {
        tdb = new MyDBOpenHelper(context, "dev.db", null, 2);
        sdb = tdb.getWritableDatabase();
    }

    boolean addPage(Page page)
    {
        ContentValues cv = new ContentValues();
        if (page.is_plain == 1)
        {
            cv.put("CONTENT", page.plain_content);
        }
        else
        {
            cv.put("CONTENT", page.url_content);
        }
        cv.put("IS_PLAIN", page.is_plain);
        sdb.insert("pages", null, cv);
        return true;
    }

    ArrayList<Page> getAllPages()
    {
        ArrayList<Page> retval = new ArrayList<>();
        String table_name = "pages";
        String[] columns = {"ID", "IS_PLAIN", "CONTENT"};
        Cursor c = sdb.query(table_name, columns, null, null, null, null, null);
        c.moveToFirst();
        for(int i = 0; i < c.getCount(); i++)
        {
            retval.add(new Page(c.getInt(0), c.getInt(1), c.getString(2)));
            c.moveToNext();
        }
        c.close();
        return retval;
    }


    private MyDBOpenHelper tdb;
    private SQLiteDatabase sdb;
}
