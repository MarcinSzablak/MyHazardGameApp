package com.example.myhazardgameapp.fragments.gameSelectedList

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.myhazardgameapp.R

class SelectedGameListAdapter (
    private val context: FragmentActivity,
    private val buttons: Array<String>
): ArrayAdapter<String>(context, R.layout.fragment_game_selected_list_content, buttons){
    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(
            R.layout.fragment_game_selected_list_content,
            null, true
        )

        val textView = rowView.findViewById<TextView>(R.id.test)

        textView.text = SelectedGameChangeSupport.SelectedGame.title

        return rowView
    }
}