package com.zm.org.alc4phase1challengeandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_a.*

class ActivityA : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)

        supportActionBar?.title = getString(R.string.alc_4_phase_1)


        navToActivityB_Button.setOnClickListener {

            startActivity(Intent(this@ActivityA, ActivityB::class.java))
        }
        navToActivityC_Button.setOnClickListener {

            startActivity(Intent(this@ActivityA, ActivityC::class.java))
        }
    }
}
