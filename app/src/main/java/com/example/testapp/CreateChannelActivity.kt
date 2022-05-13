package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import com.github.dhaval2404.imagepicker.ImagePicker

class CreateChannelActivity : AppCompatActivity() {
    private lateinit var channelNameEditText : EditText
    private lateinit var  descriptionEditText : EditText
    private lateinit var channelAvatarImageView : ImageView
    private lateinit var checkButton : ImageView

    private lateinit var userInputName : String
    private lateinit var userInputDescription : String
    private var userAvatarUri : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        AppPreferences.setup(applicationContext)

        channelNameEditText = findViewById(R.id.channelNameEditText)
        descriptionEditText = findViewById(R.id.channelDescrEditText)
        channelAvatarImageView = findViewById(R.id.avatarButton)
        checkButton = findViewById(R.id.checkButton)

        channelNameEditText.addTextChangedListener(userInputTextWatcher)
        descriptionEditText.addTextChangedListener(userInputTextWatcher)

        channelAvatarImageView.setOnClickListener {
            ImagePicker.with(this)
                .cropSquare()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .saveDir(getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!)
                .start()
        }

        checkButton.setOnClickListener {
            AppPreferences.channelName = userInputName
            AppPreferences.channelDescription = userInputDescription
            AppPreferences.channelAvatar = userAvatarUri

            val intent = Intent(this, PublicLinkActivity::class.java)
            startActivity(intent)
        }
    }

    private val userInputTextWatcher : TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }


        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            userInputName  = channelNameEditText.text.toString().trim()
            userInputDescription = descriptionEditText.text.toString().trim()

            if(!userInputDescription.isEmpty() && !userInputName.isEmpty()) {
                checkButton.setImageResource(R.drawable.check_activated)
                checkButton.isClickable = true
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Image Uri will not be null for RESULT_OK
        val uri = data?.data!!

        // Use Uri object instead of File to avoid storage permissions
        userAvatarUri = uri.toString()
        channelAvatarImageView.setImageURI(uri)
    }


}