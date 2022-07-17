package com.example.memology.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;

import com.example.memology.R;
import com.example.memology.models.Memes;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MemesDB";
    private static String DBPath;
    private static final int DATABASE_VERSION = 3;
    private static final String MEMES_TABLE = "memes";
    private static final String KEY_FILEDATA = "FILE";
    private static final String KEY_ID = "ID";
    private static final String KEY_URI = "URI";
    private static final String KEY_TITLE = "TITLE";

    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
        DBPath = context.getDatabasePath(DATABASE_NAME).toString();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String createMemesTable = String.format("CREATE TABLE %1$s (%2$s INTEGER PRIMARY KEY AUTOINCREMENT,%3$S VARCHAR(1000), %4$s VARCHAR(1000), %5$s BLOB)", MEMES_TABLE,KEY_ID,KEY_TITLE,KEY_URI,KEY_FILEDATA);
            db.execSQL(createMemesTable);
        }
        catch (Exception e)
        {
            Log.e("DB",e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            try{
                db.execSQL(String.format("DROP TABLE IF EXISTS %s",MEMES_TABLE));
                onCreate(db);
            }catch (Exception e){
                Log.e("D",e.getMessage());
            }
        }
    }


    public void postMemes(Memes memes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,memes.getTitle());
        values.put(KEY_URI, memes.getUri().toString());
        try{
            db.insertOrThrow(MEMES_TABLE,null,values);
        }catch (Exception e){
            Log.e("DBError",e.getMessage());
        }
        db.close();
    }

    public List<Memes> getAllMemes(){
        List<Memes> memesList = new ArrayList<Memes>();
        try{
            String query= String.format("SELECT * FROM %s",MEMES_TABLE);
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(query,null);
            if(cursor.moveToFirst()){
                do{
                    Memes memes = new Memes();
                    memes.setId(cursor.getInt(0));
                    memes.setTitle(cursor.getString(1));
                    memes.setUri(Uri.parse(cursor.getString(2)));
                    memesList.add(memes);
                }while(cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){
            Log.e("DB",e.getMessage());
        }


        return memesList;
    }
}
