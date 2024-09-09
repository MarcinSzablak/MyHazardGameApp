package com.example.myhazardgameapp.other.gamesFiles

import androidx.fragment.app.Fragment
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.fragments.HowToPlay
import com.example.myhazardgameapp.fragments.Test
import com.example.myhazardgameapp.fragments.toolsFragments.chohan.ChohanCalculatorFragment
import com.example.myhazardgameapp.fragments.toolsFragments.chohan.ChohanHowToPlayFragment

object GamesList {
    public val games = arrayOf<Game>(
        Game("Poker", R.raw.card,"card","2+",
            arrayOf<Tool>(
                Tool("How to play", HowToPlay()),
                Tool("test", Test())
                )
            ),
        Game("Solitaire", R.raw.card,"card","1+",
            arrayOf<Tool>(
                Tool("How to play", HowToPlay()),
                Tool("test", Test())
                )
            ),
        Game("Mahjong", R.raw.reddragon,"mahjong","4",
            arrayOf<Tool>(
                Tool("How to play", HowToPlay()),
                Tool("test", Test())
                )
            ),
        Game("Cho-han", R.raw.dice,"dice","3+",
            arrayOf<Tool>(
                Tool("How to play", ChohanHowToPlayFragment()),
                Tool("Calculator", ChohanCalculatorFragment(), R.drawable.baseline_calculate_24)
                )
            ),
        )
}



