package com.example.myhazardgameapp.fragments.toolsFragments.chohan

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.myhazardgameapp.MainActivity
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.fragments.mainSelectionList.GameListViewAdapter

class ChohanCalculatorListAdapter(
    private val context: FragmentActivity,
    private val players: MutableList<PlayersListElement>,
    private val isThisCho: Boolean, //value to choose layout for list element
): ArrayAdapter<PlayersListElement>(context, R.layout.cho_han_list_view_red, players) {

    @SuppressLint("ViewHolder", "MissingInflatedId", "ClickableViewAccessibility")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val rowView: View
        val inflater = context.layoutInflater

        val playerListElement = players[position]

        if(playerListElement is Player){
            val player: Player = playerListElement
            if (isThisCho){
                rowView = inflater.inflate(
                    R.layout.cho_han_list_view_red,
                    null, true
                )
                rowView.setOnLongClickListener {
                    val bottomSheetDelete = ChoHanDeleteBottomSheet(player, this, true)
                    bottomSheetDelete.show(context.supportFragmentManager, "ModalBottomSheet")
                    this.notifyDataSetChanged()
                    return@setOnLongClickListener true
                }
            } else{
                rowView = inflater.inflate(
                    R.layout.cho_han_list_view_blue,
                    null, true
                )
                rowView.setOnLongClickListener {
                    val bottomSheetDelete = ChoHanDeleteBottomSheet(player, this, false)
                    bottomSheetDelete.show(context.supportFragmentManager, "ModalBottomSheet")
                    this.notifyDataSetChanged()
                    return@setOnLongClickListener true
                }
            }

            val playerNameView = rowView.findViewById<TextView>(R.id.player_name)
            playerNameView.text = player.name

            val playerPointSView = rowView.findViewById<TextView>(R.id.player_points)
            playerPointSView.text = "${player.points}pt"

            //adding animation when text is bigger then textView
            rowView.setOnClickListener {
                playerNameView.isSelected = !playerNameView.isSelected
                playerPointSView.isSelected = !playerPointSView.isSelected
            }

            return rowView
        }
        else
        {
            if(isThisCho){
                rowView = inflater.inflate(
                    R.layout.cho_han_add_player_red,
                    null, true
                )

                rowView.setOnClickListener {
                    val showPopWindow = ChoHanPopWindow(true, this)
                    showPopWindow.show(context.supportFragmentManager, "showPopUp")
                }

            } else{
                rowView = inflater.inflate(
                    R.layout.cho_han_add_player_blue,
                    null, true
                )

                rowView.setOnClickListener {
                    val showPopWindow = ChoHanPopWindow(false, this)
                    showPopWindow.show(context.supportFragmentManager, "showPopUp")
                }
            }

            return rowView
        }
    }
}