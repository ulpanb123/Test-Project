package com.example.testapp

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object AppPreferences {
    private var sharedPreferences: SharedPreferences? = null

    // TODO step 1: call `AppPreferences.setup(applicationContext)` in your MainActivity's `onCreate` method
    fun setup(context: Context) {
        // TODO step 2: set your app name here
        sharedPreferences = context.getSharedPreferences("TestApp.sharedprefs", Context.MODE_PRIVATE)
    }

    // TODO step 4: replace these example attributes with your stored values
    var channelName: String?
        get() = Key.NAME.getString()
        set(value) = Key.NAME.setString(value)

    var channelDescription: String?
        get() = Key.DESCRIPTION.getString()
        set(value) = Key.DESCRIPTION.setString(value)

    var channelAvatar: String?
        get() = Key.AVATAR.getString()
        set(value) = Key.AVATAR.setString(value)

    var channelLink : String?
        get() = Key.LINK.getString()
        set(value) = Key.LINK.setString(value)

    private enum class Key {
        NAME, DESCRIPTION, AVATAR, LINK; // TODO step 3: replace these cases with your stored values keys

        fun getString(): String? = if (sharedPreferences!!.contains(name)) sharedPreferences!!.getString(name, "") else null

        fun setString(value: String?) = value?.let { sharedPreferences!!.edit { putString(name, value) } } ?: remove()

        fun remove() = sharedPreferences!!.edit { remove(name) }
    }
}