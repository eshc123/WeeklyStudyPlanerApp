package com.eshc.weeklystudyplanerapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.DataBindingUtil
import com.eshc.weeklystudyplanerapp.R
import com.eshc.weeklystudyplanerapp.databinding.ActivityNewPlanBinding

class NewPlanActivity : AppCompatActivity() {

    private lateinit var titleEditText : AppCompatEditText
    private lateinit var startEditText : AppCompatEditText
    private lateinit var finishEditText : AppCompatEditText
    private var day = 0
    private lateinit var binding : ActivityNewPlanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_plan)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_new_plan)

        titleEditText = binding.etTitle
        startEditText = binding.etStart
        finishEditText = binding.etFinish

        val button = binding.btOk
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
                intent.putExtra(EXTRA_DAY,day)
                setResult(Activity.RESULT_OK,intent)
            }
            finish()
        }

        val spinner : Spinner = binding.spDays
        ArrayAdapter.createFromResource(
            this,
            R.array.days_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                day = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }
    companion object {
        const val EXTRA_TITLE = "TITLE"
        const val EXTRA_START = "START"
        const val EXTRA_FINISH = "FINISH"
        const val EXTRA_DAY = "DAY"
    }
}