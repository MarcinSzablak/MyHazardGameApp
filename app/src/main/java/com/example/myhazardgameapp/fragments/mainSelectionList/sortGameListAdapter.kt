package com.example.myhazardgameapp.fragments.mainSelectionList

import android.content.Context
import com.example.myhazardgameapp.R

fun sortGameListAdapter(
    chosenStatus: CharSequence,
    adapter: GameListViewAdapter,
    context: Context
) {
    when (chosenStatus) {
        context.getString(R.string.name_a_z) -> {
            adapter.sortGames { game1, game2 -> game1.compereAlfabetical(game1, game2) }
        }
        context.getString(R.string.name_z_a) -> {
            adapter.sortGames { game1, game2 -> game1.compereAlfabetical(game2, game1) }
        }
        context.getString(R.string.more_players) -> {
            adapter.sortGames { game1, game2 -> game1.comperePlayers(game2, game1) }
        }
        context.getString(R.string.less_players) -> {
            adapter.sortGames { game1, game2 -> game1.comperePlayers(game1, game2) }
        }
    }
}