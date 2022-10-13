package com.testing.hammersys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.testing.hammersys.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        supportActionBar?.elevation = 0F
        supportActionBar?.title = "TEST"
    }
}
