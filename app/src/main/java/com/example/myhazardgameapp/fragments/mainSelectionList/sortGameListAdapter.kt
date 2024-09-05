package com.example.myhazardgameapp.fragments.mainSelectionList

fun sortGameListAdapter(
    chosenStatus: CharSequence,
    adapter: GameListViewAdapter
) {
    when (chosenStatus) {
        "Nazwa: A-Z" -> {
            adapter.sortGames { game1, game2 -> game1.compereAlfabetical(game1, game2) }
        }
        "Nazwa: Z-A" -> {
            adapter.sortGames { game1, game2 -> game1.compereAlfabetical(game2, game1) }
        }
        "Więcej graczy" -> {
            adapter.sortGames { game1, game2 -> game1.comperePlayers(game2, game1) }
        }
        "Mniej graczy" -> {
            adapter.sortGames { game1, game2 -> game1.comperePlayers(game1, game2) }
        }
    }
}