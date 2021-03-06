package com.bsantalucia.beaconmqtt.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bsantalucia.beaconmqtt.db.beacon.BeaconContract;
import com.bsantalucia.beaconmqtt.db.beacon.BeaconInDistanceContract;
import com.bsantalucia.beaconmqtt.db.beacon.BeaconInRangeContract;
import com.bsantalucia.beaconmqtt.db.log.LogContract;
import com.bsantalucia.beaconmqtt.db.pid.PidContract;

public class DbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 8;
    private static final String DATABASE_NAME = "Beacon.db";

    private static final String SQL_CREATE_BEACON_TABLE =
            "CREATE TABLE " + BeaconContract.BeaconEntry.TABLE_NAME + " (" +
                    BeaconContract.BeaconEntry.COLUMN_NAME_UUID + " TEXT," +
                    BeaconContract.BeaconEntry.COLUMN_NAME_MAC + " TEXT," +
                    BeaconContract.BeaconEntry.COLUMN_NAME_MINOR + " TEXT," +
                    BeaconContract.BeaconEntry.COLUMN_NAME_MAJOR + " TEXT," +
                    BeaconContract.BeaconEntry.COLUMN_NAME_INFORMAL_NAME + " TEXT," +
                    "PRIMARY KEY (" +
                    BeaconContract.BeaconEntry.COLUMN_NAME_UUID + ", " +
                    BeaconContract.BeaconEntry.COLUMN_NAME_MAC + ", " +
                    BeaconContract.BeaconEntry.COLUMN_NAME_MINOR + ", " +
                    BeaconContract.BeaconEntry.COLUMN_NAME_MAJOR + ")" +
                    ");";

    private static final String SQL_CREATE_BEACON_IN_RANGE_TABLE =
            "CREATE TABLE " + BeaconInRangeContract.BeaconEntry.TABLE_NAME + " (" +
                    BeaconContract.BeaconEntry.COLUMN_NAME_UUID + " TEXT," +
                    BeaconContract.BeaconEntry.COLUMN_NAME_MAC + " TEXT," +
                    BeaconContract.BeaconEntry.COLUMN_NAME_MINOR + " TEXT," +
                    BeaconContract.BeaconEntry.COLUMN_NAME_MAJOR + " TEXT," +
                    "PRIMARY KEY (" +
                    BeaconContract.BeaconEntry.COLUMN_NAME_UUID + ", " +
                    BeaconContract.BeaconEntry.COLUMN_NAME_MAC + ", " +
                    BeaconContract.BeaconEntry.COLUMN_NAME_MINOR + ", " +
                    BeaconContract.BeaconEntry.COLUMN_NAME_MAJOR + ")" +
                    ");";

    private static final String SQL_CREATE_BEACON_IN_DISTANCE_TABLE =
            "CREATE TABLE " + BeaconInDistanceContract.BeaconEntry.TABLE_NAME + " (" +
                    BeaconInDistanceContract.BeaconEntry.COLUMN_NAME_UUID + " TEXT," +
                    BeaconInDistanceContract.BeaconEntry.COLUMN_NAME_MAC + " TEXT," +
                    BeaconInDistanceContract.BeaconEntry.COLUMN_NAME_MINOR + " TEXT," +
                    BeaconInDistanceContract.BeaconEntry.COLUMN_NAME_MAJOR + " TEXT," +
                    "PRIMARY KEY (" +
                    BeaconInDistanceContract.BeaconEntry.COLUMN_NAME_UUID + ", " +
                    BeaconInDistanceContract.BeaconEntry.COLUMN_NAME_MAC + ", " +
                    BeaconInDistanceContract.BeaconEntry.COLUMN_NAME_MINOR + ", " +
                    BeaconInDistanceContract.BeaconEntry.COLUMN_NAME_MAJOR + ")" +
                    ");";

    private static final String SQL_CREATE_LOG_TABLE =
            "CREATE TABLE " + LogContract.LogEntry.TABLE_NAME + " (" +
                    LogContract.LogEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    LogContract.LogEntry.COLUMN_NAME_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL," +
                    LogContract.LogEntry.COLUMN_NAME_LOG_LINE + " TEXT," +
                    LogContract.LogEntry.COLUMN_NAME_EXTRA_INFO + " TEXT)";

    private static final String SQL_CREATE_PID_TABLE =
            "CREATE TABLE " + PidContract.PidEntry.TABLE_NAME + " (" +
                    PidContract.PidEntry.COLUMN_NAME_PID + " TEXT," +
                    "PRIMARY KEY (" +
                    PidContract.PidEntry.COLUMN_NAME_PID + ")" +
                    ");";

    private static final String SQL_DELETE_BEACON_TABLE =
            "DROP TABLE IF EXISTS " + BeaconContract.BeaconEntry.TABLE_NAME;

    private static final String SQL_DELETE_BEACON_IN_DISTANCE_TABLE =
            "DROP TABLE IF EXISTS " + BeaconInDistanceContract.BeaconEntry.TABLE_NAME;

    private static final String SQL_DELETE_BEACON_IN_RANGE_TABLE =
            "DROP TABLE IF EXISTS " + BeaconInRangeContract.BeaconEntry.TABLE_NAME;

    private static final String SQL_DELETE_LOG_TABLE =
            "DROP TABLE IF EXISTS " + LogContract.LogEntry.TABLE_NAME;

    private static final String SQL_DELETE_PID_TABLE =
            "DROP TABLE IF EXISTS " + PidContract.PidEntry.TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_BEACON_TABLE);
        db.execSQL(SQL_CREATE_BEACON_IN_RANGE_TABLE);
        db.execSQL(SQL_CREATE_BEACON_IN_DISTANCE_TABLE);
        db.execSQL(SQL_CREATE_LOG_TABLE);
        db.execSQL(SQL_CREATE_PID_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_BEACON_TABLE);
        db.execSQL(SQL_DELETE_BEACON_IN_RANGE_TABLE);
        db.execSQL(SQL_DELETE_BEACON_IN_DISTANCE_TABLE);
        db.execSQL(SQL_DELETE_LOG_TABLE);
        db.execSQL(SQL_DELETE_PID_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
