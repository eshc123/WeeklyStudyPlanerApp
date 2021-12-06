package com.eshc.weeklystudyplanerapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.eshc.weeklystudyplanerapp.R

class NewPlanActivity : AppCompatActivity() {

    private lateinit var titleEditText : AppCompatEditText
    private lateinit var startEditText : AppCompatEditText
    private lateinit var finishEditText : AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_plan)
        titleEditText = findViewById(R.id.et_title)
        startEditText = findViewById(R.id.et_start)
        finishEditText = findViewById(R.id.et_finish)

        val button = findViewById<AppCompatButton>(R.id.bt_ok)
        button.setOnClickListener {
            val intent = Intent()
            if(TextUtils.isEmpty(titleEditText.text) || TextUtils.isEmpty(startEditText.text) || TextUtils.isEmpty(finishEditText.text)){
                Toast.makeText(this,"Fill in the blank",Toast.LENGTH_SHORT).show()
            }
            else {
                val title = titleEditText.text.toString()
                val start = startEditText.text.toString()
                val finish = finishEditText.text.toString()
                intent.putExtra(EXTRA_TITLE,title)
                intent.putExtra(EXTRA_START,start)
                intent.putExtra(EXTRA_FINISH,finish)
                setResult(Activity.RESULT_OK,intent)
            }
            finish()
        }

    }
    companion object {
        const val EXTRA_TITLE = "TITLE"
        const val EXTRA_START = "START"
        const val EXTRA_FINISH = "FINISH"
    }
}