package com.example.myhazardgameapp.fragments.toolsFragments.chohan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.other.FragmentStack
import java.math.BigInteger

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentStack.currentGameSelectedStack = ChohanCalculatorFragment()


        val choList = view.findViewById<ListView>(R.id.cho_list_view)
        val hanList = view.findViewById<ListView>(R.id.han_list_view)

        // for testing
        for (i in 1..100){
            ChohanCalculatorPlayersLists.choPlayers += (Player("Maciek", 2))
            ChohanCalculatorPlayersLists.hanPlayers += (Player("Kamil", 1))
        }

        //Adding adapters to both lists
        choList.adapter = ChohanCalculatorListAdapter(requireActivity(),
            ChohanCalculatorPlayersLists.choPlayers, true)
        hanList.adapter = ChohanCalculatorListAdapter(requireActivity(),
            ChohanCalculatorPlayersLists.hanPlayers, false)

        super.onViewCreated(view, savedInstanceState)
    }
}