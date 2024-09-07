package com.example.myhazardgameapp.fragments

import BottomSheetSort
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.fragments.mainSelectionList.BottomSheetFiltr
import com.example.myhazardgameapp.fragments.mainSelectionList.FiltrStatus
import com.example.myhazardgameapp.fragments.mainSelectionList.GameListViewAdapter
import com.example.myhazardgameapp.fragments.mainSelectionList.SortStatus
import com.example.myhazardgameapp.fragments.mainSelectionList.filtrGameListAdapter
import com.example.myhazardgameapp.fragments.mainSelectionList.sortGameListAdapter
import com.example.myhazardgameapp.other.FragmentStack
import com.example.myhazardgameapp.other.buttonAnimation
import com.example.myhazardgameapp.other.gamesFiles.GamesList


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

        FragmentStack.gameSelectedStack.push(GameSelectedListFragment())

        val gameList = GamesList.games

        val gameListView = view.findViewById<ListView>(R.id.main_fragment_game_select_list)
        val searchView = view.findViewById<EditText>(R.id.main_fragment_search_bar)
        val filterButton = view.findViewById<TextView>(R.id.main_fragment_filtr)
        val sortButton = view.findViewById<TextView>(R.id.main_fragment_sort)
        val settingsButton = view.findViewById<ImageButton>(R.id.main_fragment_settings)

        val gameListViewAdapter = GameListViewAdapter(requireActivity() , gameList)
        gameListView.adapter = gameListViewAdapter

        filtrGameListAdapter(
            FiltrStatus.playerNumberMin,
            FiltrStatus.playerNumberMax,
            FiltrStatus.type,
            gameListViewAdapter
        )

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
            buttonAnimation(it)

            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_right
            )?.replace(R.id.main_fragment_container, SettingsFragment())
                ?.commit()
        }
    }
}