package com.example.myhazardgameapp.fragments.toolsFragments.chohan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.myhazardgameapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ChoHanDeleteBottomSheet(
    val player: Player,
    val adapter: ChohanCalculatorListAdapter,
    val isCho: Boolean
) : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.cho_han_delete_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cancelButton = view.findViewById<TextView>(R.id.cho_han_delete_bottom_sheet_cancel_button)
        val deleteButton = view.findViewById<TextView>(R.id.cho_han_delete_bottom_sheet_delete_button)

        cancelButton.setOnClickListener {
            dismiss()
        }
        deleteButton.setOnClickListener {
            if (isCho){
                ChohanCalculatorPlayersLists.choPlayers.remove(player)
                ChohanCalculatorData.decrementChoPointsSum(player.points)
            }
            else{
                ChohanCalculatorPlayersLists.hanPlayers.remove(player)
                ChohanCalculatorData.decrementHanPointsSum(player.points)
            }

            adapter.notifyDataSetChanged()

            dismiss()
        }
    }
}