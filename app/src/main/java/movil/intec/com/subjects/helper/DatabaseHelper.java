package movil.intec.com.subjects.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import movil.intec.com.subjects.model.Horario;
import movil.intec.com.subjects.model.Subject;

/**
 * Created by wesley on 3/21/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper mInstance = null;

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "subjectsManager";

    // Table Names
    private static final String TABLE_SUBJECTS = "subjects";
    private static final String TABLE_HORARIO = "Horario";

    // Common column names
    private static final String COL_ID = "id";
    private static final String COL_CREATED_AT = "created_at";

    // Subjects Table - column nmaes
    private static final String COL_NAME = "name";
    private static final String COL_PROFESOR = "profesor";


    // TAGS Table - column names
    private static final String COL_START = "start";
    private static final String COL_END = "end";
    private static final String COL_DAY = "day";
    private static final String COL_SUBJECTS_ID = "subjects_id";



    // Table Create Statements
    // Subjects table create statement
    private static final String CREATE_TABLE_S = "CREATE TABLE "
            + TABLE_SUBJECTS + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME
            + " TEXT NOT NULL," + COL_PROFESOR + " TEXT NOT NULL," + COL_CREATED_AT
            + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    // Horario table create statement
    private static final String CREATE_TABLE_H = "CREATE TABLE " + TABLE_HORARIO
            + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_START + " TEXT NOT NULL,"
            + COL_END + " TEXT NOT NULL," + COL_DAY + " TEXT NOT NULL," + COL_SUBJECTS_ID + " INTEGER,"
            + COL_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP" + ")";


    private final String triggerName = "fk_check_materia_id";

    private Context mCtx;


    public static DatabaseHelper getInstance(Context ctx) {
        /**
         * use the application context as suggested by CommonsWare.
         * this will ensure that you dont accidentally leak an Activitys
         * context (see this article for more information:
         * http://developer.android.com/resources/articles/avoiding-memory-leaks.html)
         */
        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    /**
     * constructor should be private to prevent direct instantiation.
     * make call to static factory method "getInstance()" instead.
     */
    private DatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.mCtx = ctx;
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_S);
        db.execSQL(CREATE_TABLE_H);
        createTrigger(db,this.triggerName,this.TABLE_SUBJECTS,this.TABLE_HORARIO,this.COL_ID,this.COL_ID);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HORARIO);
        // create new tables
        onCreate(db);
    }


    public void createTrigger(SQLiteDatabase db,String triggerName,String tableName1, String tableName2,String columnName1,String columnName2){
        db.execSQL("CREATE TRIGGER " +triggerName+
                " BEFORE INSERT "+
                " ON "+tableName1+

                " FOR EACH ROW BEGIN"+
                " SELECT CASE WHEN ((SELECT "+columnName1+" FROM "+tableName2+
                " WHERE "+columnName1+"=new."+columnName2+" ) IS NULL)"+
                " THEN RAISE (ABORT,'Foreign Key Violation') END;"+
                "  END;");
    }

    public Cursor getAll(String table){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c=db. rawQuery("SELECT id, * FROM "+table+";", null);
        return c;
    }

    // Adding new
    public long createSubject(Subject s, int[] h_ids) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, s.getName());
        values.put(COL_PROFESOR, s.getProfesor());

        // insert row
        long s_id = db.insert(TABLE_SUBJECTS, null, values);

       addSubToHorario((int) s_id, h_ids);

        return s_id;
    }

    public long createHorario(Horario h) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_START, h.getStart().toString());
        values.put(COL_END, h.getEnd().toString());
        values.put(COL_START, h.getDay().toString());

        // insert row
        long h_id = db.insert(TABLE_HORARIO, null, values);

        return h_id;
    }

    public void addSubToHorario(int s_id,int[] h_ids) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_SUBJECTS_ID,s_id);

        db.update(TABLE_HORARIO,values,COL_ID + " =?", new String[] { String.valueOf(h_ids) });
    }

    public ArrayList<Subject> getAllSubjects() {
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        String selectQuery = "SELECT * FROM " + TABLE_SUBJECTS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Subject s = this.getSubject(c.getInt(c.getColumnIndex(COL_ID)));
                subjects.add(s);
            } while (c.moveToNext());
        }

        return subjects;
    }

    public int getSubjectsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SUBJECTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public Subject getSubject(int s_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_SUBJECTS + " WHERE "
                + COL_ID + " = " + s_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Subject s = new Subject();
        s.setId(c.getInt((c.getColumnIndex(COL_ID))));
        s.setName((c.getString(c.getColumnIndex(COL_NAME))));
        s.setProfesor((c.getString(c.getColumnIndex(COL_NAME))));
        s.setCreatedAt(c.getString(c.getColumnIndex(COL_CREATED_AT)));
        s.setHorarios(this.getSubjectsHorarios((int) s.getId()));
        return s;
    }

    private ArrayList<Horario> getSubjectsHorarios(int s_id){
        ArrayList<Horario> horarios = new ArrayList<Horario>();
        String selectQuery = "SELECT * FROM " + TABLE_HORARIO + "INNER JOIN "+TABLE_SUBJECTS
                + "ON "+ COL_SUBJECTS_ID +" = "+ s_id;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Horario h = this.getHorario(c.getInt(c.getColumnIndex(COL_ID)));
                horarios.add(h);
            } while (c.moveToNext());
        }
        return horarios;
    }

    public Horario getHorario(int h_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_SUBJECTS + " WHERE "
                + COL_ID + " = " + h_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Horario h = new Horario();
        h.setId(c.getInt((c.getColumnIndex(COL_ID))));
        h.setStart((c.getString(c.getColumnIndex(COL_START))));
        h.setEnd((c.getString(c.getColumnIndex(COL_END))));
        h.setDay(c.getString(c.getColumnIndex(COL_DAY)));
        h.setSubject(this.getSubject(c.getInt((c.getColumnIndex(COL_CREATED_AT)))));
        return h;
    }

    public ArrayList<Subject> getAllSubjectbyDay(String day) {
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        String selectQuery = "SELECT * FROM " + TABLE_HORARIO + "WHERE day="+day;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Subject s = this.getSubject(c.getInt(c.getColumnIndex(COL_SUBJECTS_ID)));
                subjects.add(s);
            } while (c.moveToNext());
        }

        return subjects;
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }


}