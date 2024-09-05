package com.example.myhazardgameapp.fragments.mainSelectionList

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.other.gamesFiles.GamesList
import com.example.myhazardgameapp.other.gamesFiles.GamesType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFiltr: BottomSheetDialogFragment() {
    private lateinit var adapter: GameListViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val v: View = inflater.inflate(
            R.layout.bottom_sheet_filtr,
            container, false
        )
        return v
    }

    fun setAdapter(gameListViewAdapter: GameListViewAdapter) {
        adapter = gameListViewAdapter
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val minView = view.findViewById<EditText>(R.id.bottom_sheet_filter_min)
        val maxView = view.findViewById<EditText>(R.id.bottom_sheet_filter_max)
        val filtrButton = view.findViewById<TextView>(R.id.bottom_sheet_filtr_button)
        val spinner = view.findViewById<Spinner>(R.id.bottom_sheet_filtr_spinner)

        minView.setText(FiltrStatus.playerNumberMin.toString())
        maxView.setText(FiltrStatus.playerNumberMax.toString())

        maxView.doOnTextChanged { _, _, _, _ ->
            if(maxView.text.toString() <= minView.text.toString() && maxView.text.toString() != ""){
                Toast.makeText(context, "Proszę podać poprawne dane", Toast.LENGTH_SHORT).show()
            }
        }

        val spinnerContent = GamesType.gamesTypes

        if(FiltrStatus.type == ""){
            FiltrStatus.type = spinnerContent[0]
        }

        val spinnerAdapter = ArrayAdapter(requireContext(),
            R.layout.bottom_sheet_spinner_item, spinnerContent)
        spinner.adapter = spinnerAdapter

        spinner.setSelection(GamesType.gamesTypes.indexOf(FiltrStatus.type))

        filtrButton.setOnClickListener {
            val adapter = this.adapter
            val min = minView.text.toString().toInt()
            val max = maxView.text.toString().toInt()
            val type = spinner.selectedView
                .findViewById<TextView>(R.id.spinnerTarget)
                .text.toString()

            filtrGameListAdapter(min, max, type, adapter)

            dismiss()
        }
    }
}