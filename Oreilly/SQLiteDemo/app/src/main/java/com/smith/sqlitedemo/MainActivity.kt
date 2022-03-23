package com.smith.sqlitedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.smith.sqlitedemo.data.DatabaseHandler
import com.smith.sqlitedemo.model.Contact

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = DatabaseHandler(this)

        var jeremy = Contact(
            null,
            "Jeremy",
            "909909023"
        )

        handler.addContact(jeremy)

        val contactList: List<Contact> = handler.getAllContacts()
        for (contact in contactList) {
            Log.d("MainActivity", "onCreate: ${contact.phoneNumber}")
        }
    }


}