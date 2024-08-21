package com.example.myhazardgameapp.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.myhazardgameapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.reflect.typeOf

class BottomSheetFiltr: BottomSheetDialogFragment() {
    lateinit var adapter: ArrayAdapter<Game>

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val min = view.findViewById<EditText>(R.id.bottom_sheet_filter_min)
        val max = view.findViewById<EditText>(R.id.bottom_sheet_filter_max)
        val filtrButton = view.findViewById<TextView>(R.id.bottom_sheet_filtr_button)

        min.setText(FiltrStatus.playerNumberMin.toString())
        max.setText(FiltrStatus.playerNumberMax.toString())

        max.doOnTextChanged { _, _, _, _ ->
            if(max.text.toString() <= min.text.toString() && max.text.toString() != ""){
                Toast.makeText(context, "Proszę podać poprawne dane", Toast.LENGTH_SHORT).show()
            }
        }

        filtrButton.setOnClickListener {
            FiltrStatus.playerNumberMin =
                if (min.text.toString() != "") min.text.toString().toInt()
                else FiltrStatus.playerNumberMin
            FiltrStatus.playerNumberMax =
                if (max.text.toString() != "") max.text.toString().toInt()
                else FiltrStatus.playerNumberMax

            dismiss()
        }

    }

    fun setAdapter(gameListViewAdapter: GameListViewAdapter) {
        adapter = gameListViewAdapter
    }
}