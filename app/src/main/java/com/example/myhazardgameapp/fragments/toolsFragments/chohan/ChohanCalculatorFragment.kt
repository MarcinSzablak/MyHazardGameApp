package com.example.myhazardgameapp.fragments.toolsFragments.chohan

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.other.FragmentStack

class ChohanCalculatorFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chohan, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentStack.currentGameSelectedStack = ChohanCalculatorFragment()
        //Prepare lists
        val choList = view.findViewById<ListView>(R.id.cho_list_view)
        val hanList = view.findViewById<ListView>(R.id.han_list_view)

        // for testing
//        for (i in 1..5){
//            ChohanCalculatorPlayersLists.choPlayers += (Player("Maciek", 2))
//            ChohanCalculatorPlayersLists.hanPlayers += (Player("Kamil", 1))
//        }

        //Adding adapters to both lists
        choList.adapter = ChohanCalculatorListAdapter(requireActivity(),
            ChohanCalculatorPlayersLists.choPlayers, true)
        hanList.adapter = ChohanCalculatorListAdapter(requireActivity(),
            ChohanCalculatorPlayersLists.hanPlayers, false)

        val choPointsView = view.findViewById<TextView>(R.id.cho_points)
        val hanPointsView = view.findViewById<TextView>(R.id.han_points)
        val pointsView = view.findViewById<TextView>(R.id.cho_han_points)

        ChohanCalculatorData.choPointsSum.observe(viewLifecycleOwner, Observer { count ->
            choPointsView.text = "${count}pt"
        })

        ChohanCalculatorData.hanPointsSum.observe(viewLifecycleOwner, Observer { count ->
            hanPointsView.text = "${count}pt"
        })

        ChohanCalculatorData.pointsSum.observe(viewLifecycleOwner, Observer { count ->
            pointsView.text = "${count}pt"
        })

        super.onViewCreated(view, savedInstanceState)
    }
}