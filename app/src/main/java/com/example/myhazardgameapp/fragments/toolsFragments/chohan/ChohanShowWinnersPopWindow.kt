package com.example.myhazardgameapp.fragments.toolsFragments.chohan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.myhazardgameapp.R
import java.math.BigDecimal

class ChohanShowWinnersPopWindow(
    val choListView: ListView,
    val hanListView: ListView,
    val winningPlayersPoints: ArrayList<BigDecimal>,
    val isThisCho: Boolean,
): DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.cho_han_show_winners_pop_window, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val winnersList = view.findViewById<ListView>(R.id.cho_han_show_winners_pop_up_list_view)
        winnersList.adapter = ChohanShowWinnersPopUpListAdapter(
            context = requireActivity(),
            winningPlayersPoints = winningPlayersPoints,
            isThisCho = isThisCho)


        val resetButton = view.findViewById<TextView>(R.id.cho_han_show_winners_pop_up_button)

        resetButton.setOnClickListener {
            ChohanCalculatorPlayersLists.resetPlayers()

            ChohanCalculatorData.resetAllSums()

            choListView.adapter = ChohanCalculatorListAdapter(requireActivity(),
                ChohanCalculatorPlayersLists.choPlayers, true)
            hanListView.adapter = ChohanCalculatorListAdapter(requireActivity(),
                ChohanCalculatorPlayersLists.hanPlayers, false )

            dismiss()
        }
    }
}