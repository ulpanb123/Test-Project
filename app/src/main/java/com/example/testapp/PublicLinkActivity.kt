package com.example.testapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged



class PublicLinkActivity : AppCompatActivity() {
    private lateinit var channelLinkEditText : EditText
    private lateinit var checkButton : ImageView
    private lateinit var channelLink : String

    private lateinit var backButton : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_link)

        channelLinkEditText = findViewById(R.id.channelLinkEditText)
        checkButton = findViewById(R.id.checkButton)
        backButton = findViewById(R.id.backButton)

        channelLinkEditText.doOnTextChanged { text, start, before, count ->
            channelLink = channelLinkEditText.text.toString().trim()

            if(!channelLink.isEmpty()) {
                checkButton.setImageResource(R.drawable.check_activated)
                checkButton.isClickable = true
            }
        }

        checkButton.setOnClickListener {
            AppPreferences.channelLink = channelLink

            val intent = Intent(this, ChannelInfoActivity::class.java)
            startActivity(intent)

        }

        backButton.setOnClickListener {
            val intent = Intent(this, CreateChannelActivity::class.java)
            startActivity(intent)
        }

    }

}