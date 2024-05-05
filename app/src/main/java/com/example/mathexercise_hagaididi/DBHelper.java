package com.example.mathexercise_hagaididi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
public class DBHelper extends SQLiteOpenHelper {


        private static final String DATABASENAME = "missions.db";
        private static final String TABLE_RECORD = "tblmissions";
        private static final int DATABASEVERSION = 1;
        // ?
        private static final String COLUMN_ID = "_id";
        private static final String COLUMN_PERCENTAGE = "percentage";
        private static final String COLUMN_NAME = "name";
        private static final String COLUMN_PRIORITY = "priority";


        private static final String[] allColumns = {COLUMN_ID, COLUMN_NAME, COLUMN_PRIORITY,COLUMN_PERCENTAGE};

        private static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " +
                TABLE_RECORD + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT UNIQUE," +
                COLUMN_PERCENTAGE + " INT," +
                COLUMN_PRIORITY + " TEXT );";


        private SQLiteDatabase database; // access to table

        public DBHelper(@Nullable Context context) {
            super(context, DATABASENAME, null, DATABASEVERSION);
        }


        // creating the database
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase)
        {
            sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        }

        // in case of version upgrade -> new schema
        // database version
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORD);
            onCreate(sqLiteDatabase);
        }


        // get the user back with the id
        // also possible to return only the id
        public long insert(mission mission,Context context){
            database = getWritableDatabase(); // get access to write the database
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, mission.getName());
            values.put(COLUMN_PERCENTAGE, mission.getPercentage());
            values.put(COLUMN_PRIORITY, mission.getPriority());

            // stored as Binary Large OBject ->  BLOB


            long id = database.insert(TABLE_RECORD, null, values);
            mission.setId(id);
            database.close();
            return id;
        }

        // remove a specific user from the table
        public void deleteUser(mission mission)
        {

        }


        public void deleteById(long id )
        {
            database = getWritableDatabase(); // get access to write e data
            database.delete(TABLE_RECORD, COLUMN_ID + " = " + id, null);
            database.close(); // close the database
        }

        // update a specific user
        public void update(mission mission)
        {
            database = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, mission.getId());
            values.put(COLUMN_NAME, mission.getName());
            values.put(COLUMN_PERCENTAGE, mission.getPercentage());
            values.put(COLUMN_PRIORITY, mission.getPriority());
            database.update(TABLE_RECORD, values, COLUMN_ID + "=" + mission.getId(), null);
            database.close();

        }

        // return all rows in table
        public ArrayList<mission> selectAll(){
            database = getReadableDatabase(); // get access to read the database
            ArrayList<mission> users = new ArrayList<>();
            Cursor cursor = database.query(TABLE_RECORD, allColumns, null, null, null, null, null); // cursor points at a certain row
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                    int percentage = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PERCENTAGE));
                    String priority = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRIORITY));


                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
                    mission mission= new mission(name,percentage,priority,id);
                    users.add(mission);
                }
            }
            cursor.close();
            database.close();
            return users;
        }


        //I prefer using this one...
        //
        public ArrayList<mission> genericSelectByUserName(String Name)
        {
            String[] vals = { Name };
            // if using the rawQuery
            // String query = "SELECT * FROM " + TABLE_RECORD + " WHERE " + COLUMN_NAME + " = ?";
            String column = COLUMN_NAME;
            return select(column,vals);
        }


        //INPUT: notice two options rawQuery should look like
        // rawQuery("SELECT id, name FROM people WHERE name = ? AND id = ?", new String[] {"David", "2"});
        //OUTPUT: arraylist - number of elements accordingly
        public ArrayList<mission> select(String column,String[] values)
        {
            database = getReadableDatabase(); // get access to read the database
            ArrayList<mission> missions = new ArrayList<>();
            // Two options,
            // since query cannot be created in compile time there is no difference
            //Cursor cursor = database.rawQuery(query, values);
            Cursor cursor= database.query(TABLE_RECORD, allColumns, COLUMN_NAME +" = ? ", values, null, null, null); // cursor points at a certain row
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                    int percentage = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PERCENTAGE));
                    String  priority= cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRIORITY));
                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
                    mission mission= new mission(name,percentage,priority,id);
                    missions.add(mission);
                }// end while
            } // end if
            database.close();
            return missions;
        }

}
