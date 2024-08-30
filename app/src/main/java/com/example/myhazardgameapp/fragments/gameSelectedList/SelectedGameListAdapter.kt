package com.example.myhazardgameapp.fragments.gameSelectedList

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.fragments.GameSelectedFragment
import com.example.myhazardgameapp.fragments.GameSelectedListFragment
import com.example.myhazardgameapp.other.FragmentStack
import com.example.myhazardgameapp.other.Tool

class SelectedGameListAdapter (
    private val context: FragmentActivity,
    private val tools: Array<Tool>
): ArrayAdapter<Tool>(context, R.layout.fragment_game_selected_list_content, tools){
    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(
            R.layout.fragment_game_selected_list_content,
            null, true
        )

        val textView = rowView.findViewById<TextView>(R.id.test)

        textView.text = tools[position].title

        rowView.setOnClickListener {
            val buttonClick = ScaleAnimation(
                1f, 0.9f,
                1f, 0.9f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            )
            buttonClick.duration = 200
            rowView.startAnimation(buttonClick)

            FragmentStack.gameSelectedStack.push(tools[position].fragment)

            val fragmentTransaction = context.supportFragmentManager.beginTransaction()
            fragmentTransaction
                .setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_out_right
                )
                .replace(R.id.game_selected_fragment_container , tools[position].fragment)
                .commit()
        }

        return rowView
    }
}