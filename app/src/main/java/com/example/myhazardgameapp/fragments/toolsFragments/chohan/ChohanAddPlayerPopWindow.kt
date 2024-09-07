package com.example.myhazardgameapp.fragments.toolsFragments.chohan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.other.buttonAnimation


class ChohanAddPlayerPopWindow(
    private val isCho: Boolean,
    private val adapter: ChohanCalculatorListAdapter
) : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.cho_han_add_player_pop_window, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val choHanPopUpButton = view.findViewById<TextView>(R.id.cho_han_pop_up_add_player_button)
        val choHanPopUpName = view.findViewById<TextView>(R.id.cho_han_pop_up_add_player_name)
        val choHanPopUpPoints = view.findViewById<TextView>(R.id.cho_han_pop_up_add_player_points)

        var name = ""
        var points = 0

        choHanPopUpName.doOnTextChanged { text, _, _, _ ->
            name = text.toString()
        }
        choHanPopUpPoints.doOnTextChanged { text, _, _, _ ->
            points = text.toString().toIntOrNull() ?: 0
        }

        choHanPopUpButton.setOnClickListener {

            buttonAnimation(it)

            if (name == "" || points <= 0){
                Toast.makeText(context, "incorrect data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (isCho){
                ChohanCalculatorPlayersLists.choPlayers.removeLast()
                ChohanCalculatorPlayersLists.choPlayers += Player( name, points )
                ChohanCalculatorPlayersLists.choPlayers += AddPlayer()
                adapter.notifyDataSetChanged()

                ChohanCalculatorData.incrementChoPointsSum(points)

                dismiss()
            }
            else{
                ChohanCalculatorPlayersLists.hanPlayers.removeLast()
                ChohanCalculatorPlayersLists.hanPlayers += Player( name, points )
                ChohanCalculatorPlayersLists.hanPlayers += AddPlayer()
                adapter.notifyDataSetChanged()

                ChohanCalculatorData.incrementHanPointsSum(points)

                dismiss()
            }
        }

    }

}