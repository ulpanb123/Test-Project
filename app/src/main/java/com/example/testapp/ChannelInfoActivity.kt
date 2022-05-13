package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text

class ChannelInfoActivity : AppCompatActivity() {
    private lateinit var channelAvatarImageView : ImageView
    private lateinit var loadImageButton : FloatingActionButton
    private lateinit var channelNameTextView : TextView
    private lateinit var subscriberTextView : TextView
    private lateinit var descriptionTextView : TextView
    private lateinit var channelLinkTextView : TextView
    private lateinit var subscribersCountTextView : TextView
    private lateinit var shareButton : ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel_info)

        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        channelAvatarImageView = findViewById(R.id.channel_avatar)
        loadImageButton = findViewById(R.id.loadImageButton)
        channelNameTextView = findViewById(R.id.channelNameTextView)
        subscriberTextView = findViewById(R.id.subscribersTextView)
        descriptionTextView = findViewById(R.id.descr_textView)
        channelLinkTextView = findViewById(R.id.channel_link)
        subscribersCountTextView = findViewById(R.id.subscribers_count)
        shareButton = findViewById(R.id.share_button)

        channelNameTextView.text = AppPreferences.channelName
        channelLinkTextView.text = AppPreferences.channelLink
        descriptionTextView.text = AppPreferences.channelDescription

        if(!AppPreferences.channelAvatar.isNullOrEmpty()) {
            channelAvatarImageView.setImageURI(AppPreferences.channelAvatar!!.toUri())
        }

        loadImageButton.setOnClickListener {
            ImagePicker.with(this)
                .cropSquare()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .saveDir(getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!)
                .start()
        }

        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, PublicLinkActivity::class.java))
        }

        shareButton.setOnClickListener {
            val textMessage = "Hey, join my channel by following the link: \n" + AppPreferences.channelLink
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, textMessage)
                type = "text/plain"
            }

            startActivity(sendIntent)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Image Uri will not be null for RESULT_OK
        val uri = data?.data!!

        channelAvatarImageView.setImageURI(uri)
        AppPreferences.channelAvatar = uri.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("HAHAHA", "works")
        menuInflater.inflate(R.menu.menu_info, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("HAHAHA", "works")

        if(item.itemId == R.id.edit_button) {
            val intent = Intent(this, EditChannelActivity::class.java)
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}