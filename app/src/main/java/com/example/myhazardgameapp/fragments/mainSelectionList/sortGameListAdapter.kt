package com.example.myhazardgameapp.fragments.mainSelectionList

fun sortGameListAdapter(
    chosenStatus: CharSequence,
    adapter: GameListViewAdapter
) {
    when (chosenStatus) {
        "Name: A-Z" -> {
            adapter.sortGames { game1, game2 -> game1.compereAlfabetical(game1, game2) }
        }
        "Name: Z-A" -> {
            adapter.sortGames { game1, game2 -> game1.compereAlfabetical(game2, game1) }
        }
        "More players" -> {
            adapter.sortGames { game1, game2 -> game1.comperePlayers(game2, game1) }
        }
        "Less players" -> {
            adapter.sortGames { game1, game2 -> game1.comperePlayers(game1, game2) }
        }
    }
}