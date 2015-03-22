package mcs.gymapp.db;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import mcs.gymapp.db.entita.AllenamentoH;
import mcs.gymapp.db.entita.AllenamentoI;
import mcs.gymapp.db.entita.Corso;
import mcs.gymapp.db.entita.Esercizio;
import mcs.gymapp.db.entita.GruppoH;
import mcs.gymapp.db.entita.GruppoI;
import mcs.gymapp.db.entita.Serie;

public class DataBaseAdapter {
	static final String DATABASE_NAME = "gym.db";
	public static final int DATABASE_VERSION = 7 ;

	static final String[] DATABASE_CREATE = {
            "CREATE TABLE CORSO ("                              +
                    "IDC    INTEGER PRIMARY KEY  NOT NULL ,"    +   //chiave
                    "NOME   TEXT NOT NULL  , "                  +
                    "IDI    INTEGER NOT NULL ,"                 +
                    "NOTE   TEXT );",

            "CREATE TABLE ESERCIZIO ("                          +
                    "IDE    INTEGER PRIMARY KEY  NOT NULL ,"    +   //chiave
                    "NOME   TEXT NOT NULL  , "                  +
                    "NOTE   TEXT );",

            "CREATE TABLE GRUPPO_I ("                           +
                    "IDG        INTEGER NOT NULL ,"             +   //chiave
                    "POSN       INTEGER NOT NULL ,"             +   //chiave
                    "IDE        INTEGER NOT NULL ,"             +
                    "N_SERIE    INTEGER , "                     +
                    "N_RIPET    INTEGER , "                     +
                    "RIPOSO     INTEGER , "                     +
                    "SSERIE     INTEGER , "                     +
                    "PRIMARY KEY (IDG,POSN));",

            "CREATE TABLE GRUPPO_H ("                           +
                    "IDGS     INTEGER NOT NULL , "              +   //chiave
                    "POSN     INTEGER NOT NULL , "              +   //chiave
                    "IDG      INTEGER NOT NULL , "              +
                    "NOME     TEXT NOT NULL ,"                  +
                    "NOTE     TEXT , "                          +
                    "PRIMARY KEY (IDG,POSN));",

            "CREATE TABLE ALLENAMENTO_I ("                      +
                    "IDA      INTEGER NOT NULL , "              +   //chiave scheda
                    "POSN     INTEGER NOT NULL , "              +   //chiave a b c
                    "IDGS     INTEGER , "                       +  //nel caso in cui è corso chiave corso
                    "TIPO     INTEGER NOT NULL , "              +  //1 fitnes - 2 corso
                    "NOME     TEXT ,"                           +
                    "NOTE     TEXT ,"                           +
                    "PRIMARY KEY (IDA, POSN));",

            "CREATE TABLE ALLENAMENTO_H  ("                     +
                    "IDA      INTEGER PRIMARY KEY  NOT NULL ,"  +   //chiave
                    "IDI      INTEGER NOT NULL ,"               +
                    "DATAI    INTEGER NOT NULL ,"               +
                    "DATAF    INTEGER NOT NULL ,"               +
                    "NOTE );",

            "INSERT INTO ALLENAMENTO_H (IDA,IDI,DATAI,DATAF,NOTE) VALUES ('1','1','2015-01-01','2015-01-31','forza');",
            "INSERT INTO ALLENAMENTO_H (IDA,IDI,DATAI,DATAF,NOTE) VALUES ('2','1','2015-02-01','2015-02-28','massa');",

            "INSERT INTO ALLENAMENTO_I (IDA,POSN,IDGS,TIPO,NOME,NOTE) VALUES ('2','1','1','1','a','');",
            "INSERT INTO ALLENAMENTO_I (IDA,POSN,IDGS,TIPO,NOME,NOTE) VALUES ('2','2','2','1','b','');",
            "INSERT INTO ALLENAMENTO_I (IDA,POSN,IDGS,TIPO,NOME,NOTE) VALUES ('2','3','1','2','c','');",

            "INSERT INTO GRUPPO_H (IDGS,POSN,IDG,NOME,NOTE) VALUES ('1','1','1','petto','');",
            "INSERT INTO GRUPPO_H (IDGS,POSN,IDG,NOME,NOTE) VALUES ('1','2','2','deltoidi','');",
            "INSERT INTO GRUPPO_H (IDGS,POSN,IDG,NOME,NOTE) VALUES ('2','1','3','dorsali','');",

            "INSERT INTO GRUPPO_I (IDG,POSN,IDE,N_SERIE,N_RIPET, RIPOSO, SSERIE) VALUES ('1','1','1','3','10','05','');",
            "INSERT INTO GRUPPO_I (IDG,POSN,IDE,N_SERIE,N_RIPET, RIPOSO, SSERIE) VALUES ('1','2','2','3','10','10','');",
            "INSERT INTO GRUPPO_I (IDG,POSN,IDE,N_SERIE,N_RIPET, RIPOSO, SSERIE) VALUES ('2','1','3','3','10','15','');",
            "INSERT INTO GRUPPO_I (IDG,POSN,IDE,N_SERIE,N_RIPET, RIPOSO, SSERIE) VALUES ('3','1','4','3','10','20','');",
            "INSERT INTO GRUPPO_I (IDG,POSN,IDE,N_SERIE,N_RIPET, RIPOSO, SSERIE) VALUES ('3','2','5','3','10','25','');",

            "INSERT INTO ESERCIZIO (IDE,NOME,NOTE) VALUES ('1','panca piana','');",
            "INSERT INTO ESERCIZIO (IDE,NOME,NOTE) VALUES ('2','croci orizzontali','');",
            "INSERT INTO ESERCIZIO (IDE,NOME,NOTE) VALUES ('3','alzate laterali','');",
            "INSERT INTO ESERCIZIO (IDE,NOME,NOTE) VALUES ('4','lat. avanti','');",
            "INSERT INTO ESERCIZIO (IDE,NOME,NOTE) VALUES ('5','pulley','');",

            "INSERT INTO CORSO (IDC,NOME,IDI,NOTE) VALUES ('1','zumpa','ciccio','');",


    };
    public static final String NOT_EXIST = null;

	public SQLiteDatabase db;
	private final Context context;
	private DataBaseHelper dbHelper;

	public DataBaseAdapter(Context _context) {
		context = _context;
	}

	public DataBaseAdapter open() throws SQLException {
		dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
		db = dbHelper.getWritableDatabase();
		return this;
	}
	public void close() {
		db.close();
	}

	public SQLiteDatabase getDatabaseInstance() {
		return db;
	}
    /*
	public void insertOtion(String opt, String val) {
		ContentValues newValues = new ContentValues();
		newValues.put("OPTION", opt);
		newValues.put("VALUE", val);
		db.insert("CONFIG", null, newValues);
	}
	public void deleteOtion(String opt) {
		String where = "OPTION =? ";
		db.delete("CONFIG", where , new String[] { opt }  );
	}
	public void updateOtion(String opt, String val) {
		ContentValues updatedValues = new ContentValues();
		updatedValues.put("OPTION", opt);
		updatedValues.put("VALUE", val);
		String where = "OPTION =? ";
		int i = 0;
		if (getSingleOtion(opt) == this.NOT_EXIST){ 
			this.insertOtion(opt, val);
		}else{
			i = db.update("CONFIG", updatedValues, where, new String[] { opt });
		}
		i= i*1;
	}
	public void disconnetti() {
		db.delete("CONFIG", null, null);
		db.delete("ORE", null, null);
		db.delete("BUSTA_PAGA", null, null);
	}
*/


    public AllenamentoH getLastAllenamento() {
        List<AllenamentoH> l = new ArrayList<AllenamentoH>();
        AllenamentoH a;

        Cursor cursor = db.query("ALLENAMENTO_H", null, null,
                null , null, null, null);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        long data;
        if (cursor.moveToFirst()) {
            do {
                a= new AllenamentoH();
                a.setIDA(cursor.getInt(cursor.getColumnIndexOrThrow("IDA")));
                a.setIDI(cursor.getInt(cursor.getColumnIndexOrThrow("IDI")));

                data = cursor.getLong(cursor.getColumnIndexOrThrow("DATAI"));
                a.setDataI(new Date(data));
                data = cursor.getLong(cursor.getColumnIndexOrThrow("DATAF"));
                a.setDataF(new Date(data));
                a.setNote(cursor.getString(cursor.getColumnIndexOrThrow("NOTE")));
                l.add(a);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        Collections.sort(l);
        return l.get(1);
    }

    public List<AllenamentoI> getAllenamentoI(int ida) {
        List<AllenamentoI> l = new ArrayList<AllenamentoI>();
        AllenamentoI a;
        Cursor cursor = db.query("Allenamento_I", null, " IDA =? ",
                new String[] {  (new Integer (ida)).toString() } ,
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                a= new AllenamentoI();
                a.setIDA(cursor.getInt(cursor.getColumnIndexOrThrow("IDA")));
                a.setPosn(cursor.getInt(cursor.getColumnIndexOrThrow("POSN")));
                a.setIDGS(cursor.getInt(cursor.getColumnIndexOrThrow("IDGS")));
                a.setTipo(cursor.getInt(cursor.getColumnIndexOrThrow("TIPO")));
                a.setNome(cursor.getString(cursor.getColumnIndexOrThrow("NOME")));
                a.setNote(cursor.getString(cursor.getColumnIndexOrThrow("NOTE")));
                l.add(a);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return l;
    }

    public ArrayList<GruppoH> getGruppoH(int idgs ) {
        ArrayList<GruppoH> l = new ArrayList<GruppoH>();
        GruppoH a;
        Cursor cursor = db.query("Gruppo_H", null, " IDGS =? ",
                new String[] {  (new Integer (idgs)).toString() } , null, null, null);

        if (cursor.moveToFirst()) {
            do {
                a= new GruppoH();
                a.setIDGS(cursor.getInt(cursor.getColumnIndexOrThrow("IDGS")));
                a.setPosn(cursor.getInt(cursor.getColumnIndexOrThrow("POSN")));
                a.setIDG(cursor.getInt(cursor.getColumnIndexOrThrow("IDG")));
                a.setNome(cursor.getString(cursor.getColumnIndexOrThrow("NOME")));
                a.setNote(cursor.getString(cursor.getColumnIndexOrThrow("NOTE")));
                l.add(a);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return l;
    }

    public ArrayList<GruppoI> getGruppoI(int idg) {
        ArrayList<GruppoI> l = new ArrayList<GruppoI>();

        GruppoI a;
        Cursor cursor = db.query("Gruppo_I", null, " IDG =? ",
                new String[] {  (new Integer (idg)).toString() } , null, null, null);

        if (cursor.moveToFirst()) {
            do {
                a= new GruppoI();
                a.setIDG(cursor.getInt(cursor.getColumnIndexOrThrow("IDG")));
                a.setPosn(cursor.getInt(cursor.getColumnIndexOrThrow("POSN")));
                a.setIDE(cursor.getInt(cursor.getColumnIndexOrThrow("IDE")));
                a.setNSer(cursor.getInt(cursor.getColumnIndexOrThrow("N_SERIE")));
                a.setNRip(cursor.getInt(cursor.getColumnIndexOrThrow("N_RIPET")));
                a.setRiposo(cursor.getInt(cursor.getColumnIndexOrThrow("RIPOSO")));
                a.setSSer(cursor.getInt(cursor.getColumnIndexOrThrow("SSERIE")));
                l.add(a);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return l;
    }


    public Esercizio getEser(int ide) {
        Esercizio a = null;
        Cursor cursor = db.query("Esercizio", null, " IDE =? ",
                new String[] {  (new Integer (ide)).toString() } ,
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                a= new Esercizio();
                a.setIDE(cursor.getInt(cursor.getColumnIndexOrThrow("IDE")));
                a.setNome(cursor.getString(cursor.getColumnIndexOrThrow("NOME")));
                a.setNote(cursor.getString(cursor.getColumnIndexOrThrow("NOTE")));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return a;
    }

    public Corso getCorso(int idc) {
        Corso a = null;
        Cursor cursor = db.query("Corso", null, " IDC =? ",
                new String[] {  (new Integer (idc)).toString() } ,
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                a= new Corso();
                a.setIDE(cursor.getInt(cursor.getColumnIndexOrThrow("IDC")));
                a.setNome(cursor.getString(cursor.getColumnIndexOrThrow("NOME")));
                a.setIDI(cursor.getInt(cursor.getColumnIndexOrThrow("IDI")));
                a.setNote(cursor.getString(cursor.getColumnIndexOrThrow("NOTE")));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return a;
    }


}
