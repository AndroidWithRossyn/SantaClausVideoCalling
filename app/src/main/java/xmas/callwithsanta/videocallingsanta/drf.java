package xmas.callwithsanta.videocallingsanta;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class drf extends SQLiteOpenHelper {
    public drf(Context context) {
        super(context, "DBPackageName", null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS MyCallerTable(id DATETIME DEFAULT CURRENT_TIMESTAMP PRIMARY KEY,name VARCHAR,phone_no VARCHAR,pic_path VARCHAR,video_path VARCHAR);");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ScheduleVideoCallerTable(call_time DATETIME DEFAULT CURRENT_TIMESTAMP PRIMARY KEY,caller_id VARCHAR);");
        sQLiteDatabase.execSQL("INSERT INTO ScheduleVideoCallerTable VALUES('23-01-2018','1');");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS CallTable(call_time DATETIME DEFAULT CURRENT_TIMESTAMP PRIMARY KEY,name VARCHAR,phone_no VARCHAR,interface_style VARCHAR,tone VARCHAR,voice_path VARCHAR,vibration_mode VARCHAR,image_path VARCHAR);");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS MessageTable(message_time DATETIME DEFAULT CURRENT_TIMESTAMP PRIMARY KEY,name VARCHAR,phone_no VARCHAR,message_text VARCHAR);");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS VideoCallTable(call_time DATETIME DEFAULT CURRENT_TIMESTAMP PRIMARY KEY,video_number VARCHAR);");
        sQLiteDatabase.execSQL("INSERT INTO VideoCallTable VALUES('23-01-2018','1');");
        h("table created");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS PackageName");
        onCreate(sQLiteDatabase);
    }

    public void a(String str, String str2, String str3, String str4, String str5) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS MyCallerTable(id DATETIME DEFAULT CURRENT_TIMESTAMP PRIMARY KEY,name VARCHAR,phone_no VARCHAR,pic_path VARCHAR,video_path VARCHAR);");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO MyCallerTable VALUES('");
        sb.append(str);
        String str6 = "','";
        sb.append(str6);
        sb.append(str2);
        sb.append(str6);
        sb.append(str3);
        sb.append(str6);
        sb.append(str4);
        sb.append(str6);
        sb.append(str5);
        sb.append("');");
        writableDatabase.execSQL(sb.toString());
        writableDatabase.close();
        h("New Caller ID added to List successfuly");
    }

    public void a(String str) {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS ScheduleVideoCallerTable(call_time DATETIME DEFAULT CURRENT_TIMESTAMP PRIMARY KEY,caller_id VARCHAR);");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ScheduleVideoCallerTable VALUES('");
        sb.append(format);
        sb.append("','");
        sb.append(str);
        sb.append("');");
        writableDatabase.execSQL(sb.toString());
        writableDatabase.close();
        h("Created successfuly");
    }

    public void a(String str, String str2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS VideoCallTable(call_time DATETIME DEFAULT CURRENT_TIMESTAMP PRIMARY KEY,video_number VARCHAR);");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO VideoCallTable VALUES('");
        sb.append(str);
        sb.append("','");
        sb.append(str2);
        sb.append("');");
        writableDatabase.execSQL(sb.toString());
        writableDatabase.close();
        h("Created successfuly");
    }

    public List<String> a() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT * FROM CallTable", null);
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(0, rawQuery.getString(0));
                arrayList.add(1, rawQuery.getString(1));
                arrayList.add(2, rawQuery.getString(2));
                arrayList.add(3, rawQuery.getString(3));
                arrayList.add(4, rawQuery.getString(4));
                arrayList.add(5, rawQuery.getString(5));
                arrayList.add(6, rawQuery.getString(6));
                arrayList.add(7, rawQuery.getString(7));
            } while (rawQuery.moveToNext());
            writableDatabase.close();
        } else {
            arrayList.add("No Call Schedule");
        }
        return arrayList;
    }

    public List<String> b(String str) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM CallTable WHERE call_time='");
        sb.append(str);
        sb.append("'");
        String sb2 = sb.toString();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery(sb2, null);
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(0, rawQuery.getString(0));
                arrayList.add(1, rawQuery.getString(1));
                arrayList.add(2, rawQuery.getString(2));
                arrayList.add(3, rawQuery.getString(3));
                arrayList.add(4, rawQuery.getString(4));
                arrayList.add(5, rawQuery.getString(5));
                arrayList.add(6, rawQuery.getString(6));
                arrayList.add(7, rawQuery.getString(7));
            } while (rawQuery.moveToNext());
            writableDatabase.close();
        } else {
            arrayList.add("No Call Schedule");
        }
        return arrayList;
    }

    public String b() {
        String str;
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT * FROM VideoCallTable", null);
        if (rawQuery.moveToFirst()) {
            do {
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(rawQuery.getString(1));
                str = sb.toString();
            } while (rawQuery.moveToNext());
        } else {
            str = "1";
        }
        writableDatabase.close();
        return str;
    }

    public void c() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("DELETE FROM MyCallerTable");
        writableDatabase.close();
        Log.d("Caller ID", "Removed from List successfuly");
    }

    public void c(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM MyCallerTable WHERE id='");
        sb.append(str);
        sb.append("'");
        writableDatabase.execSQL(sb.toString());
        writableDatabase.close();
        Log.d("Caller ID", "Removed from List successfuly");
    }

    public void d() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
//        writableDatabase.execSQL("DELETE FROM ScheduleVideoCallerTable");
        writableDatabase.close();
        Log.d("Deleting, ", "Scheudle Video Caller Data, deleted successfulys");
    }

    public void e() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("DELETE FROM VideoCallTable");
        writableDatabase.close();
        Log.d("deleteBook", "Deleted successfulys");
    }

    public String f() {
        String str;
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM ScheduleVideoCallerTable", null);
        if (rawQuery.moveToFirst()) {
            do {
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(rawQuery.getString(0));
                str = sb.toString();
            } while (rawQuery.moveToNext());
        } else {
            str = "1";
        }
        writableDatabase.close();
        return str;
    }

    public ArrayList<String> g() {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM MyCallerTable ORDER BY id DESC", null);
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(rawQuery.getString(0));
            } while (rawQuery.moveToNext());
            writableDatabase.close();
        }
        return arrayList;
    }

    public ArrayList<String> h() {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM MyCallerTable ORDER BY id DESC", null);
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(rawQuery.getString(1));
            } while (rawQuery.moveToNext());
            writableDatabase.close();
        }
        return arrayList;
    }

    public ArrayList<String> i() {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM MyCallerTable ORDER BY id DESC", null);
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(rawQuery.getString(2));
            } while (rawQuery.moveToNext());
            writableDatabase.close();
        }
        return arrayList;
    }

    public ArrayList<String> j() {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM MyCallerTable ORDER BY id DESC", null);
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(rawQuery.getString(3));
            } while (rawQuery.moveToNext());
            writableDatabase.close();
        }
        return arrayList;
    }

    public ArrayList<String> k() {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM MyCallerTable ORDER BY id DESC", null);
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(rawQuery.getString(4));
            } while (rawQuery.moveToNext());
            writableDatabase.close();
        }
        return arrayList;
    }

    public String d(String str) {
        String sb;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT  * FROM MyCallerTable WHERE id='");
        sb2.append(str);
        sb2.append("'");
        String sb3 = sb2.toString();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery(sb3, null);
        String str2 = "";
        if (!rawQuery.moveToFirst()) {
            return str2;
        }
        do {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str2);
            sb4.append(rawQuery.getString(1));
            sb = sb4.toString();
        } while (rawQuery.moveToNext());
        writableDatabase.close();
        return sb;
    }

    public String e(String str) {
        String sb;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT  * FROM MyCallerTable WHERE id='");
        sb2.append(str);
        sb2.append("'");
        String sb3 = sb2.toString();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery(sb3, null);
        String str2 = "";
        if (!rawQuery.moveToFirst()) {
            return str2;
        }
        do {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str2);
            sb4.append(rawQuery.getString(2));
            sb = sb4.toString();
        } while (rawQuery.moveToNext());
        writableDatabase.close();
        return sb;
    }

    public String f(String str) {
        String sb;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT  * FROM MyCallerTable WHERE id='");
        sb2.append(str);
        sb2.append("'");
        String sb3 = sb2.toString();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery(sb3, null);
        String str2 = "";
        if (!rawQuery.moveToFirst()) {
            return str2;
        }
        do {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str2);
            sb4.append(rawQuery.getString(3));
            sb = sb4.toString();
        } while (rawQuery.moveToNext());
        writableDatabase.close();
        return sb;
    }

    public String g(String str) {
        String sb;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT  * FROM MyCallerTable WHERE id='");
        sb2.append(str);
        sb2.append("'");
        String sb3 = sb2.toString();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery(sb3, null);
        String str2 = "";
        if (!rawQuery.moveToFirst()) {
            return str2;
        }
        do {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str2);
            sb4.append(rawQuery.getString(4));
            sb = sb4.toString();
        } while (rawQuery.moveToNext());
        writableDatabase.close();
        return sb;
    }

    public void h(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(str);
        Log.e("Message", sb.toString());
    }
}
