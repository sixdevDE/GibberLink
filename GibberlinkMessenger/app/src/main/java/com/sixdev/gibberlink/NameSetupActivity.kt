package com.sixdev.gibberlink

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit

class NameSetupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_setup)

        val nameField = findViewById<EditText>(R.id.nameInput)
        val saveButton = findViewById<Button>(R.id.btnSave)

        val prefs = getSharedPreferences("gibberlink", Context.MODE_PRIVATE)
        nameField.setText(prefs.getString("username", ""))

        saveButton.setOnClickListener {
            val name = nameField.text.toString().trim()
            prefs.edit { putString("username", name) }
            finish()
        }
    }
}
