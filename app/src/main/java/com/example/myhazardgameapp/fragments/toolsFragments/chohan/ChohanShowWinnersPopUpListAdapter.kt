package com.example.myhazardgameapp.fragments.toolsFragments.chohan

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.other.buttonAnimation
import java.math.BigDecimal

class ChohanShowWinnersPopUpListAdapter(
    private val context: FragmentActivity,
    private val winningPlayersPoints: ArrayList<BigDecimal>,
    private val isThisCho: Boolean,
): ArrayAdapter<BigDecimal>(context, R.layout.cho_han_list_view_red, winningPlayersPoints) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val rowView: View
        val inflater = context.layoutInflater

        if(isThisCho){
            rowView = inflater.inflate(
                R.layout.cho_han_list_view_red,
                null, true)
        }
        else{
            rowView = inflater.inflate(
                R.layout.cho_han_list_view_blue,
                null, true)
        }

        val player = ChohanCalculatorPlayersLists.choPlayers[position]

        val playerNameTextView = rowView.findViewById<TextView>(R.id.player_name)
        playerNameTextView.text =
            if(isThisCho) player.name
            else player.name

        val winningPlayerPoints = winningPlayersPoints[position]

        val playerPointsTextView = rowView.findViewById<TextView>(R.id.player_points)
        playerPointsTextView.text = winningPlayerPoints.toPlainString()

        rowView.setOnClickListener {
            buttonAnimation(it)
            playerNameTextView.isSelected = !playerNameTextView.isSelected
            playerPointsTextView.isSelected = !playerPointsTextView.isSelected
        }

        return rowView
    }
}