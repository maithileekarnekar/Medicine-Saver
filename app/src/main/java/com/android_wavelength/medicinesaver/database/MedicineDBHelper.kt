package com.android_wavelength.medicinesaver.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MedicineDBHelper(
    context: Context?,
    name: String?,
    factory : SQLiteDatabase.CursorFactory?,
    version: Int,
) : SQLiteOpenHelper(context, name, factory, version) {

    //database mgmt logic
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("create table medicines(id integer primary key, name text, uses text, storage text, sideEffects text, imageId integer)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}