package com.diegopardo.transformersbattle.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentTransaction
import com.diegopardo.transformersbattle.R
import com.diegopardo.transformersbattle.application.TransformersBattleApplication
import com.diegopardo.transformersbattle.ui.fragment.BattleFragment
import com.diegopardo.transformersbattle.ui.fragment.CreateOrEditTransformerFragment
import com.diegopardo.transformersbattle.ui.fragment.TransformersFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TransformersBattleApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TransformersFragment.newInstance())
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_add -> {
//            supportFragmentManager.beginTransaction()
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .replace(R.id.container, CreateOrEditTransformerFragment.newInstance())
//                .addToBackStack(CreateOrEditTransformerFragment.tag())
//                .commit()
            CreateOrEditTransformerFragment.newInstance()
                .show(supportFragmentManager, CreateOrEditTransformerFragment.tag())
            true
        }

        R.id.action_battle -> {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BattleFragment.newInstance())
                .addToBackStack(BattleFragment.tag())
                .commit()
            true
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}