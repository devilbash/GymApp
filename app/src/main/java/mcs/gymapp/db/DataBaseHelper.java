package mcs.gymapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
	
    public DataBaseHelper(Context context, String name,CursorFactory factory, int version) {
    	super(context, name, factory, version);
    }
    // Called when no database exists in disk and the helper class needs
    // to create a new one.
    @Override 
    public void onCreate(SQLiteDatabase _db) {
    	//_db.execSQL(DataBaseAdapter.DATABASE_CREATE);
    	for(String sql : DataBaseAdapter.DATABASE_CREATE){
    		_db.execSQL(sql);
    	}
    	
    	
    	
    }
    // Called when there is a database version mismatch meaning that the version
    // of the database on disk needs to be upgraded to the current version.
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
               
            // Upgrade the existing database to conform to the new version. Multiple
            // previous versions can be handled by comparing _oldVersion and _newVersion
            // values.
            // The simplest case is to drop the old table and create a new one.
        _db.execSQL("DROP TABLE IF EXISTS " + "ESERCIZIO");
        _db.execSQL("DROP TABLE IF EXISTS " + "SERIE");
        _db.execSQL("DROP TABLE IF EXISTS " + "GRUPPO_I");
        _db.execSQL("DROP TABLE IF EXISTS " + "GRUPPO_H");
        _db.execSQL("DROP TABLE IF EXISTS " + "ALLENAMENTO_I ");
        _db.execSQL("DROP TABLE IF EXISTS " + "ALLENAMENTO_H");
            
            
         // Create a new one.
         onCreate(_db);
    }
    

}
