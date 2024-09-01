package com.example.myhazardgameapp.fragments.toolsFragments.chohan

import android.annotation.SuppressLint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.myhazardgameapp.R


class ChohanCalculatorListAdapter(
    private val context: FragmentActivity,
    private val players: Array<Player>,
    private val isThisCho: Boolean //value to choose layout for list element
): ArrayAdapter<Player>(context, R.layout.fragment_chohan_list_view_red, players) {

    @SuppressLint("ViewHolder", "MissingInflatedId", "ClickableViewAccessibility")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val rowView: View
        val inflater = context.layoutInflater

        rowView = if (isThisCho){
            inflater.inflate(
                R.layout.fragment_chohan_list_view_red,
                null, true
            )
        } else{
            inflater.inflate(
                R.layout.fragment_chohan_list_view_blue,
                null, true
            )
        }

        val playerNameView = rowView.findViewById<TextView>(R.id.player_name)
        playerNameView.text = players[position].name

        val playerPointSView = rowView.findViewById<TextView>(R.id.player_points)
        playerPointSView.text = "${players[position].points}pt"

        //adding animation when text is bigger then textView
        rowView.setOnClickListener {
            playerNameView.isSelected = !playerNameView.isSelected
            playerPointSView.isSelected = !playerPointSView.isSelected
        }

        return rowView
    }
}