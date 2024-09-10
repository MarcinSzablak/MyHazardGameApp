package com.example.myhazardgameapp.other.gamesFiles

import android.content.Context
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.fragments.HowToPlay
import com.example.myhazardgameapp.fragments.Test
import com.example.myhazardgameapp.fragments.toolsFragments.chohan.ChohanCalculatorFragment
import com.example.myhazardgameapp.fragments.toolsFragments.chohan.ChohanHowToPlayFragment

object GamesList {
    fun getGames(context: Context): Array<Game> {
        return arrayOf(
            Game(
                title = context.getString(R.string.poker),
                image = R.raw.card,
                type = "card",
                playerCount = "2+",
                toolsArray =  arrayOf(
                    Tool(context.getString(R.string.how_to_play), HowToPlay()),
                    Tool("test", Test())
                )
            ),
            Game(
                title = context.getString(R.string.solitaire),
                image = R.raw.card,
                type = "card",
                playerCount = "1+",
                toolsArray = arrayOf(
                    Tool(context.getString(R.string.how_to_play), HowToPlay()),
                    Tool("test", Test())
                )
            ),
            Game(
                title = context.getString(R.string.mahjong),
                image = R.raw.reddragon,
                type = "mahjong",
                playerCount = "4",
                toolsArray = arrayOf(
                    Tool(context.getString(R.string.how_to_play), HowToPlay()),
                    Tool("test", Test())
                )
            ),
            Game(
                title = context.getString(R.string.cho_han),
                image = R.raw.dice,
                type = "dice",
                playerCount = "3+",
                toolsArray = arrayOf(
                    Tool(context.getString(R.string.how_to_play), ChohanHowToPlayFragment()),
                    Tool(context.getString(R.string.calculator), ChohanCalculatorFragment(), R.drawable.baseline_calculate_24)
                )
            )
        )
    }
}
