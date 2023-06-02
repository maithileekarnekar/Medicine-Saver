package com.android_wavelength.medicinesaver.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.android_wavelength.medicinesaver.models.Medicine

object DBUtil {

    fun updateMedicine (
        context: Context,
        medicine: Medicine,
    ){
        val db = getDataBase(context)

        val values = ContentValues()
        values.put("name",medicine.name)
        values.put("uses",medicine.uses)
        values.put("storage",medicine.storage)
        values.put("sideEffects", medicine.sideEffects)
        values.put("imageId", medicine.imageId)


        db!!.update(
            "medicines",
            values,
            "id = ?",
            arrayOf("${medicine.id}")
        )


            db.close()
    }
    fun addMedicine(
        context: Context,
        medicine: Medicine,
        id: Int,
    ) {
        val db = getDataBase(context)
        val values= ContentValues()
        values.put("id",id)
        values.put("name",medicine.name)
        values.put("uses",medicine.uses)
        values.put("storage",medicine.storage)
        values.put("sideEffects", medicine.sideEffects)
        values.put("imageId", medicine.imageId)


        db!!.insert("medicines",null,values)
        db!!.close()

    }
    fun getMedicines(
        context: Context
    ) : ArrayList<Medicine> {
        val db = getDataBase(context)
        val cursor = db!!.query(
            "medicines",
            null,
            null,
            null,
            null,
            null,
            "id"
        )
        val medicine = ArrayList<Medicine>()

        while (cursor.moveToNext()) {
            medicine.add(
                Medicine(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5)
                )
            )
        }
        db.close()
        return medicine
    }
    fun deleteMedicine(
        context: Context,
        id: Int
    ){
        val db = getDataBase(context)

        db!!.delete(
            "medicines",
            "id = ?",
            arrayOf("$id")
        )
        db.close()
    }
    private fun getDataBase(context: Context): SQLiteDatabase? {

        return MedicineDBHelper(
            context,
            "db_medicines",
            null,
            1
        ).writableDatabase

    }
}