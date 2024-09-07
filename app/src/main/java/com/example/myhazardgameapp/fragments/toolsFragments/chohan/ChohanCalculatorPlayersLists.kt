package com.example.myhazardgameapp.fragments.toolsFragments.chohan

object ChohanCalculatorPlayersLists {
    var choPlayers: MutableList<PlayersListElement> = mutableListOf(
    )
    var hanPlayers: MutableList<PlayersListElement> = mutableListOf()

    fun resetPlayers(){
        choPlayers = mutableListOf()
        hanPlayers = mutableListOf()
        choPlayers += AddPlayer()
        hanPlayers += AddPlayer()
    }
}