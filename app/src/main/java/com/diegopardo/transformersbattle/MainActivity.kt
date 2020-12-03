package com.diegopardo.transformersbattle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diegopardo.transformersbattle.application.TransformersBattleApp
import com.diegopardo.transformersbattle.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TransformersBattleApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}