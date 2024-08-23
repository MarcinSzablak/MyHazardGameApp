package com.example.myhazardgameapp.fragments

import BottomSheetSort
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.lists.BottomSheetFiltr
import com.example.myhazardgameapp.lists.GameListViewAdapter
import com.example.myhazardgameapp.lists.GamesList


class MainActivityFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameList = GamesList.games

        val gameListView = view.findViewById<ListView>(R.id.main_fragment_game_select_list)
        val searchView = view.findViewById<EditText>(R.id.main_fragment_search_bar)
        val filterButton = view.findViewById<TextView>(R.id.main_fragment_filtr)
        val sortButton = view.findViewById<TextView>(R.id.main_fragment_sort)
        val settingsButton = view.findViewById<ImageButton>(R.id.main_fragment_settings)

        val gameListViewAdapter = GameListViewAdapter(requireActivity() , gameList)
        gameListView.adapter = gameListViewAdapter
        gameListViewAdapter.sort { item1, item2 -> item1.compereAlfabetical(item1, item2) }

        searchView.doOnTextChanged { text, _, before, _ ->
            gameListViewAdapter.getFilterByTitle(before).filter(text)
        }

        val buttonClick = AlphaAnimation(1f, 0.8f)

        filterButton.setOnClickListener {
            filterButton.startAnimation(buttonClick)
            val bottomSheetFiltr = BottomSheetFiltr()
            bottomSheetFiltr.show(FragmentManager.findFragmentManager(view), "ModalBottomSheet")
            bottomSheetFiltr.setAdapter(gameListViewAdapter)
        }

        sortButton.setOnClickListener {
            sortButton.startAnimation(buttonClick)
            val bottomSheetSort = BottomSheetSort()
            bottomSheetSort.show(FragmentManager.findFragmentManager(view), "ModalBottomSheet")
            bottomSheetSort.setAdapter(gameListViewAdapter)
        }

        settingsButton.setOnClickListener {
            val buttonClick = ScaleAnimation(
                1f, 0.7f,
                1f, 0.7f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            )
            buttonClick.duration = 100
            settingsButton.startAnimation(buttonClick)

            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_right
            )?.replace(R.id.main_fragment_container, SettingsFragment())
                ?.commit()
        }
    }
}