package com.example.myhazardgameapp.lists

import com.example.myhazardgameapp.R

object GamesList {
    public val games = arrayOf<Game>(
        Game("Poker", R.drawable.card,"1+"),
        Game("Poker", R.drawable.card,"1+"),)
}

class Game(
    public val title: String,
    public val image: Int,
    public val playerCount: String)
{
}