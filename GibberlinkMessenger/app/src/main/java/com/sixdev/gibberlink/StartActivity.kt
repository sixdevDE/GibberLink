package com.sixdev.gibberlink

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        findViewById<Button>(R.id.btn_name).setOnClickListener {
            startActivity(Intent(this, NameSetupActivity::class.java))
        }
        findViewById<Button>(R.id.btn_contacts).setOnClickListener {
            startActivity(Intent(this, ContactListActivity::class.java))
        }
        findViewById<Button>(R.id.btn_gibberlink).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}

class MainActivity {

}
