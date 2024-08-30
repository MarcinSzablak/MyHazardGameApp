package com.example.myhazardgameapp.other

import androidx.fragment.app.Fragment
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.fragments.HowToPlay
import com.example.myhazardgameapp.fragments.Test

object GamesList {
    public val games = arrayOf<Game>(
        Game("Poker", R.raw.card,"card","2+"),
        Game("Pasjans", R.raw.card,"card","1+"),
        Game("Mahjong", R.raw.reddragon,"mahjong","4"),
        Game("Cho-han", R.raw.dice,"dice","3+"),
        )
    public val gamesTypes = arrayOf<String>("all", "card", "dice", "mahjong")
}

class Game(
    public val title: String,
    public val image: Int,
    public val type: String,
    public val playerCount: String,
    public val toolsArray: Array<Tool> = arrayOf<Tool>(
        HowToPlay("How to play", HowToPlay(), "aaaaaa"),
        GameTool("test", Test())
    )
)
{
    fun compereAlfabetical(item1: Game, item2: Game): Int {
        val title1 = item1.title
        val title2 = item2.title

        return compareValues(title1, title2)
    }
    fun comperePlayers(item1: Game, item2: Game): Int {
        val playerCount1 = item1.playerCount
        val playerCount2 = item2.playerCount

        return compareValues(playerCount1, playerCount2)
    }
}

abstract class Tool(
    public val title: String,
    public val fragment: Fragment,
)

class GameTool(
    title: String,
    fragment: Fragment,
): Tool(title, fragment)


class HowToPlay(
    title: String,
    fragment: Fragment,
    public val content: String
): Tool(title, fragment)