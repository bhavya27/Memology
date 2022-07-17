package com.example.memology.DB;

import android.content.Context;
import android.provider.ContactsContract;

public class DBHolder {
    public static DatabaseHandler dbHandler = null;
    Context context;



    public static void initDBHolder(Context context){
        if(dbHandler == null){
            dbHandler = new DatabaseHandler(context);
        }
    }
}
