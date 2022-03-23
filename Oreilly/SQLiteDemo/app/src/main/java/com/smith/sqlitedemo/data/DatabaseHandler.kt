package com.smith.sqlitedemo.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.smith.sqlitedemo.R
import com.smith.sqlitedemo.model.Contact
import com.smith.sqlitedemo.util.Util.Companion.DATABASE_NAME
import com.smith.sqlitedemo.util.Util.Companion.DATABASE_VERSION
import com.smith.sqlitedemo.util.Util.Companion.KEY_ID
import com.smith.sqlitedemo.util.Util.Companion.KEY_NAME
import com.smith.sqlitedemo.util.Util.Companion.KEY_PHONE_NUMBER
import com.smith.sqlitedemo.util.Util.Companion.TABLE_NAME

class DatabaseHandler(
    private val context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACT_TABLE =
            "CREATE TABLE $TABLE_NAME ( $KEY_ID INTEGER PRIMARY KEY, $KEY_NAME TEXT, $KEY_PHONE_NUMBER TEXT)"
        db?.execSQL(CREATE_CONTACT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newversion: Int) {
        val DROP_TABLE = context.getString(R.string.db_drop)
        db?.execSQL(DROP_TABLE, arrayOf(DATABASE_NAME))

        //Create a table again
        onCreate(db)
    }

    fun addContact(contact: Contact){
        val db: SQLiteDatabase = this.writableDatabase

        val values =  ContentValues()
        values.put(KEY_NAME, contact.name)
        values.put(KEY_PHONE_NUMBER, contact.phoneNumber)

        //Insert to row
        db.insert(TABLE_NAME, null, values)

        //close db connection
        db.close()
    }

    // Get a contact

    fun getContact(id: Int): Contact {
        val db: SQLiteDatabase = this.readableDatabase

        val cursor: Cursor = db.query(
            TABLE_NAME,
            arrayOf(KEY_ID, KEY_NAME, KEY_PHONE_NUMBER),
            "$KEY_ID =?", arrayOf(id.toString()),
            null,
            null,
            null
        )

        cursor.moveToFirst()
        return Contact(
            cursor.getString(0).toInt(),
            cursor.getString(1),
            cursor.getString(2)
        )
    }

    fun getAllContacts(): List<Contact> {
        val contactList = mutableListOf<Contact>()

        val db: SQLiteDatabase = this.readableDatabase

        val selectAll = "SELECT * FROM $TABLE_NAME"
        val cursor: Cursor = db.rawQuery(selectAll, null)

        if(cursor.moveToFirst()) {
            do {
                val contact = Contact(
                    cursor.getString(0).toInt(),
                    cursor.getString(1),
                    cursor.getString(2),
                )

                // add contact object to list
                contactList.add(contact)
            } while (cursor.moveToNext())
        }

        return contactList
    }

    //update contact
    fun updateContact(contact: Contact): Int {
        val db: SQLiteDatabase = this.writableDatabase

        val values = ContentValues()
        values.put(KEY_NAME, contact.name)
        values.put(KEY_PHONE_NUMBER, contact.phoneNumber)

        //update table values, where id = 43
        return db.update(TABLE_NAME, values, "$KEY_ID=?", arrayOf(contact.id?.toString()))

    }

    //Delete single contact
    fun deleteContact(contact: Contact) {
        val db:SQLiteDatabase = this.writableDatabase
        db.delete(TABLE_NAME, "$KEY_ID=?", arrayOf(contact.id.toString()))
        db.close()

    }

    //Get Contacts count method
    fun getCount(): Int {
        val countQuery = "SELECT * FROM $TABLE_NAME"
        val db: SQLiteDatabase = this.readableDatabase
        val cursor: Cursor = db.rawQuery(countQuery, null)

        return cursor.count
    }


}