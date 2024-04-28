package com.example.android_homework_4

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.example.android_homework_4.databinding.ActivityMainBinding
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputLayout
import android.widget.Toast as Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val check = Check()

        val userNameTextInputLayout = findViewById<TextInputLayout>(R.id.userName)
        val userNameEditText = findViewById<EditText>(R.id.userForEnter)
        val userTelephoneEditText = findViewById<EditText>(R.id.userTelephone)
        val radioGroupSexUser = findViewById<RadioGroup>(R.id.userSexRadioGroup)
        val switchUserNotification = findViewById<Switch>(R.id.switchUserNotification)
        val checkAutorisation = findViewById<CheckBox>(R.id.checkBoxOne)
        val checkNovelty = findViewById<CheckBox>(R.id.checkBoxTwo)
        val progressBalls = findViewById<ProgressBar>(R.id.progressBalls)
        val textBalls = findViewById<TextView>(R.id.count)
        val btnSave = findViewById<Button>(R.id.buttonSave)
        btnSave.isEnabled = false
        checkNovelty.isEnabled = false
        checkAutorisation.isEnabled = false
        checkNovelty.isChecked = false
        checkAutorisation.isChecked = false

        var userOne = User(null, null, null, null, null, null, null)

        fun enabletbtnSave() {
            if (check.ckeckUser(userOne)) {
                btnSave.isEnabled = true
            }
            else {
                btnSave.isEnabled = false
            }
        }

        userNameEditText.doOnTextChanged { text, _, _, _ ->
            if (check.isValidUserName(text)) {
                userNameTextInputLayout.isErrorEnabled = false
                userOne.userName = text.toString()
                progressBalls.progress = check.balls()
                textBalls.text = check.progress(progressBalls.progress)
                enabletbtnSave()
            } else {
                userNameTextInputLayout.error = "Некорректное имя"
                userNameTextInputLayout.isErrorEnabled = true
                userOne.userName = null
                enabletbtnSave()
            }
        }

        userTelephoneEditText.doOnTextChanged { text, _, _, _ ->
            if (!check.isValidTelephoneNumber(text)) {
                userTelephoneEditText.error = "Некорректный номер"
                userOne.userTelephone = null
                enabletbtnSave()
            }
            if (check.isValidTelephoneNumber(text)) {
                userOne.userTelephone = text.toString()
                enabletbtnSave()
            }
        }

        radioGroupSexUser.setOnCheckedChangeListener { _, buttonId ->
            when (buttonId) {
                R.id.radioButtonOne -> check.isManSex = true
                R.id.radioButtonTwo -> check.isManSex = false
            }
            if (check.isManSex|| !check.isManSex) {
                userOne.isManSex = check.isManSex
                enabletbtnSave()
            }
            else {
                userOne.isManSex = null
            }
        }

        switchUserNotification.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                check.isNotification = true
                checkAutorisation.isEnabled = true
                checkNovelty.isEnabled = true
                userOne.userNotification = true
                enabletbtnSave()
            } else {
                check.isNotification = false
                checkNovelty.isEnabled = false
                checkAutorisation.isEnabled = false
                checkNovelty.isChecked = false
                checkAutorisation.isChecked = false
                userOne.userNotification = null
                enabletbtnSave()
            }
        }
        checkAutorisation.setOnCheckedChangeListener {_, isChecked ->
            if (checkAutorisation.isChecked) {
                userOne.userAutorisation = true
                enabletbtnSave()
            }
            else {
                userOne.userAutorisation = null
                enabletbtnSave()
            }
        }
        checkNovelty.setOnCheckedChangeListener {_, isChecked ->
            if (checkNovelty.isChecked) {
                userOne.userNovelty = true
                enabletbtnSave()
            }
            else {
                userOne.userNovelty = null
                enabletbtnSave()
            }
        }

        btnSave.setOnClickListener {
            Toast.makeText(this, "Пользователь ${userOne.userName} сохранен", Toast.LENGTH_SHORT).show()
        }

    }
}