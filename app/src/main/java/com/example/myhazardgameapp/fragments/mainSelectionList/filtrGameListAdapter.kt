package com.example.myhazardgameapp.fragments.mainSelectionList

fun filtrGameListAdapter(
    min: Int,
    max: Int,
    type: String,
    adapter: GameListViewAdapter
){
    //Players range
    FiltrStatus.playerNumberMin = min
    FiltrStatus.playerNumberMax = max

    adapter.getFilterByPlayers().filter("$min" + "$max")

    //Type
    FiltrStatus.type = type

    adapter.getFilterByType().filter(FiltrStatus.type)
}