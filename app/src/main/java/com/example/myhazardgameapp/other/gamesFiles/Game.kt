package com.example.myhazardgameapp.other.gamesFiles

class Game(
    public val title: String,
    public val image: Int,
    public val type: String,
    public val playerCount: String,
    public val toolsArray: Array<Tool> = arrayOf<Tool>()
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