package com.diegopardo.transformersbattle.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diegopardo.transformersbattle.R
import com.diegopardo.transformersbattle.application.TransformersBattleApplication

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TransformersBattleApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}