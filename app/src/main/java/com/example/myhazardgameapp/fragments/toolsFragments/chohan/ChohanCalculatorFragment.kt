package com.example.myhazardgameapp.fragments.toolsFragments.chohan

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.other.FragmentStack
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.roundToInt

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
        //to proper moving between fragment
        FragmentStack.currentGameSelectedStack = ChohanCalculatorFragment()

        //Prepare lists
        val choList = view.findViewById<ListView>(R.id.cho_list_view)
        val hanList = view.findViewById<ListView>(R.id.han_list_view)

        //Adding adapters to both lists
        choList.adapter = ChohanCalculatorListAdapter(requireActivity(),
            ChohanCalculatorPlayersLists.choPlayers, true, )
        hanList.adapter = ChohanCalculatorListAdapter(requireActivity(),
            ChohanCalculatorPlayersLists.hanPlayers, false)

        //counting points
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
            pointsView.text = "Points: ${count}pt"
        })

        //to calculate every thing
        val choWinnerButton = view.findViewById<TextView>(R.id.cho_winner_button)
        val hanWinnerButton = view.findViewById<TextView>(R.id.han_winner_button)

        choWinnerButton.setOnClickListener {
            val pointsSum = ChohanCalculatorData.pointsSum.value ?: 0
            val choPointsSum = ChohanCalculatorData.choPointsSum.value ?: 0
            var winningPlayersPoints: ArrayList<BigDecimal> = arrayListOf()
            if (choPointsSum != 0){
                ChohanCalculatorPlayersLists.choPlayers.forEach { player ->
                    winningPlayersPoints.add(
                        (player.points.toFloat() / choPointsSum * pointsSum)
                            .toBigDecimal().setScale(0, RoundingMode.HALF_EVEN) )
                }
                var checkSum: Long = 0

                winningPlayersPoints.forEach { points ->
                    checkSum += points.toLong()
                    Log.v("test1", "$points")
                }

            }
            return@setOnClickListener
        }


        super.onViewCreated(view, savedInstanceState)
    }
}