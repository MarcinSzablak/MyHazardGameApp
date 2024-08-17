package com.example.myhazardgameapp.lists

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.myhazardgameapp.R

public class GameListViewAdapter(
    private val context: Activity,
    private val games: Array<Game>):
    ArrayAdapter<Game>(context, R.layout.fragment_main_activity_list_view, games)
{
    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(
            R.layout.fragment_main_activity_list_view,
            null, true)

        val titleView = rowView.findViewById<TextView>(R.id.game_list_view_title)
        val imageView = rowView.findViewById<ImageView>(R.id.game_list_view_image)
        val playersView = rowView.findViewById<TextView>(R.id.game_list_view_players)

        titleView.text = games[position].title
        imageView.setImageResource(games[position].image)
        playersView.text = games[position].playerCount


        return rowView
    }
}