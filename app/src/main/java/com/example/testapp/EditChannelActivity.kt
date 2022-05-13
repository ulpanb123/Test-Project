package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView

class EditChannelActivity : AppCompatActivity() {
    private lateinit var channelNameEditText: EditText
    private lateinit var descriptionEditText : EditText
    private lateinit var usernameEditText : EditText

    private lateinit var checkButton : ImageView

    private var userInputName : String? = AppPreferences.channelName
    private var userInputDescription : String? = AppPreferences.channelDescription
    private var userInputLink : String? = AppPreferences.channelLink

    private lateinit var backButton : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_channel)

        channelNameEditText = findViewById(R.id.channelNameEditText1)
        descriptionEditText = findViewById(R.id.channelDescrEditText1)
        usernameEditText = findViewById(R.id.channelLinkEditText1)
        checkButton = findViewById(R.id.checkButton)
        backButton = findViewById(R.id.backButton)

        checkButton.setImageResource(R.drawable.check_activated)
        checkButton.isClickable = true

        channelNameEditText.setText(AppPreferences.channelName)
        descriptionEditText.setText(AppPreferences.channelDescription)

        val username = AppPreferences.channelLink?.substring(10)

        usernameEditText.setText(username)

        channelNameEditText.addTextChangedListener(userInputTextWatcher)
        descriptionEditText.addTextChangedListener(userInputTextWatcher)
        usernameEditText.addTextChangedListener(userInputTextWatcher)

        checkButton.setOnClickListener {
            AppPreferences.channelName = userInputName
            AppPreferences.channelDescription = userInputDescription
            AppPreferences.channelLink = userInputLink

            val intent = Intent(this, ChannelInfoActivity::class.java)
            startActivity(intent)
        }

        backButton.setOnClickListener {
            startActivity(Intent(this, ChannelInfoActivity::class.java))
        }

    }

    private val userInputTextWatcher : TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }


        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            userInputName  = channelNameEditText.text.toString().trim()
            userInputDescription = descriptionEditText.text.toString().trim()

            userInputLink = "typi.team/" + usernameEditText.text.toString().trim()
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }
}