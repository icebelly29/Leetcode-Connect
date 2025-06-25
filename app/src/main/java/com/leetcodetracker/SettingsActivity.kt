package com.leetcodetracker

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val input = findViewById<EditText>(R.id.input_username)
        val saveBtn = findViewById<Button>(R.id.button_save)

        // Load existing username if available
        val prefs = getSharedPreferences("leetcode_prefs", Context.MODE_PRIVATE)
        val savedUsername = prefs.getString("username", "")
        input.setText(savedUsername)

        saveBtn.setOnClickListener {
            val username = input.text.toString().trim()

            if (username.isEmpty()) {
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            prefs.edit().putString("username", username).apply()
            Toast.makeText(this, "Username saved!", Toast.LENGTH_SHORT).show()
        }
    }
}