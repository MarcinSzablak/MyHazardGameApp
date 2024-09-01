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
        val min = view.findViewById<EditText>(R.id.bottom_sheet_filter_min)
        val max = view.findViewById<EditText>(R.id.bottom_sheet_filter_max)
        val filtrButton = view.findViewById<TextView>(R.id.bottom_sheet_filtr_button)
        val spinner = view.findViewById<Spinner>(R.id.bottom_sheet_filtr_spinner)

        min.setText(FiltrStatus.playerNumberMin.toString())
        max.setText(FiltrStatus.playerNumberMax.toString())

        max.doOnTextChanged { _, _, _, _ ->
            if(max.text.toString() <= min.text.toString() && max.text.toString() != ""){
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

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        filtrButton.setOnClickListener {
            //Players range
            FiltrStatus.playerNumberMin =
                if (min.text.toString() != "") min.text.toString().toInt()
                else FiltrStatus.playerNumberMin
            FiltrStatus.playerNumberMax =
                if (max.text.toString() != "") max.text.toString().toInt()
                else FiltrStatus.playerNumberMax

            adapter.getFilterByPlayers().filter(
                "${min.text.toString().toInt()}" +
                    "${max.text.toString().toInt()}")

            //Type
            FiltrStatus.type = spinner.selectedView
                .findViewById<TextView>(R.id.spinnerTarget).text.toString()

            adapter.getFilterByType().filter(FiltrStatus.type)

            dismiss()
        }
    }
}