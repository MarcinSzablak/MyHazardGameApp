package com.example.myhazardgameapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myhazardgameapp.fragments.MainActivityFragment
import com.example.myhazardgameapp.fragments.toolsFragments.chohan.AddPlayer
import com.example.myhazardgameapp.fragments.toolsFragments.chohan.ChohanCalculatorPlayersLists
import com.example.myhazardgameapp.other.FragmentStack

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null){
            FragmentStack.mainStack.push(MainActivityFragment())

            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, MainActivityFragment())
                .commit()
        }

        ChohanCalculatorPlayersLists.resetPlayers()
    }
}