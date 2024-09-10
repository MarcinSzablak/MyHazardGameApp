package com.example.myhazardgameapp.fragments.toolsFragments.chohan

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.other.AppToast
import com.example.myhazardgameapp.other.FragmentStack
import com.example.myhazardgameapp.other.buttonAnimation
import java.math.BigDecimal
import java.math.RoundingMode

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
        val choListView = view.findViewById<ListView>(R.id.cho_list_view)
        val hanListView = view.findViewById<ListView>(R.id.han_list_view)

        //Adding adapters to both lists
        choListView.adapter = ChohanCalculatorListAdapter(
            requireActivity(),
            ChohanCalculatorPlayersLists.choPlayers, true
        )
        hanListView.adapter = ChohanCalculatorListAdapter(
            requireActivity(),
            ChohanCalculatorPlayersLists.hanPlayers, false
        )

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

        val pointsString = requireContext().getString(R.string.points)

        ChohanCalculatorData.pointsSum.observe(viewLifecycleOwner, Observer { count ->
            pointsView.text = "$pointsString: ${count}pt"
        })

        //to calculate everything
        val choWinnerButton = view.findViewById<TextView>(R.id.cho_winner_button)
        val hanWinnerButton = view.findViewById<TextView>(R.id.han_winner_button)

        fun handleWinnerButtonClick(
            buttonView: View,
            pointsSum: Long?,
            choListView: ListView,
            hanListView: ListView,
            isCho: Boolean
        ) {
            buttonAnimation(buttonView)

            val sumPoints = pointsSum ?: 0
            val winningPlayersPoints: ArrayList<BigDecimal> = if (sumPoints != 0L) {
                calculatePointsForWinners(sumPoints)
            } else {
                AppToast.showToast(context, context?.getString(R.string.add_player))
                return
            }

            val showPopWindow = ChohanShowWinnersPopWindow(
                choListView = choListView,
                hanListView = hanListView,
                winningPlayersPoints = winningPlayersPoints,
                isThisCho = isCho
            )
            showPopWindow.show(FragmentManager.findFragmentManager(buttonView), "showPopUp")
        }

        choWinnerButton.setOnClickListener {
            handleWinnerButtonClick(
                buttonView = it,
                pointsSum = ChohanCalculatorData.choPointsSum.value,
                choListView = choListView,
                hanListView = hanListView,
                isCho = true
            )
        }

        hanWinnerButton.setOnClickListener {
            handleWinnerButtonClick(
                buttonView = it,
                pointsSum = ChohanCalculatorData.hanPointsSum.value,
                choListView = choListView,
                hanListView = hanListView,
                isCho = false
            )
        }
    }

    private fun calculatePointsForWinners(
        winnersStartingPoints: Long,
    ): ArrayList<BigDecimal>{
        val pointsSum = ChohanCalculatorData.pointsSum.value ?: 0
        val winningPlayersPoints: ArrayList<BigDecimal> = arrayListOf()

        ChohanCalculatorPlayersLists.choPlayers.forEach { player ->
            winningPlayersPoints.add(
                (player.points.toFloat() / winnersStartingPoints * pointsSum)
                    .toBigDecimal().setScale(0, RoundingMode.HALF_EVEN))
        }
        var checkSum: Long = 0

        winningPlayersPoints.forEach { points ->
            checkSum += points.toLong()
        }

        val index = winningPlayersPoints.indices.maxBy{ winningPlayersPoints[it] }

        if(checkSum > pointsSum) winningPlayersPoints[index] =
            winningPlayersPoints[index] - (checkSum.toBigDecimal() - pointsSum.toBigDecimal())
        else if(pointsSum > checkSum) winningPlayersPoints[index]=
            winningPlayersPoints[index] + (pointsSum.toBigDecimal() - checkSum.toBigDecimal())

        //deleting addPlayer
        winningPlayersPoints.removeLast()
        return winningPlayersPoints
    }
}