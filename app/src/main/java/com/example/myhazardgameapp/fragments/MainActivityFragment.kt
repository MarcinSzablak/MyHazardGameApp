package com.example.myhazardgameapp.fragments

import BottomSheetDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.lists.GameListViewAdapter
import com.example.myhazardgameapp.lists.GamesList
import kotlinx.coroutines.android.awaitFrame

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
        val sortButton = view.findViewById<TextView>(R.id.main_fragment_sort)

        val gameListViewAdapter = GameListViewAdapter(requireActivity() , gameList)
        gameListView.adapter = gameListViewAdapter
        gameListViewAdapter.sort { item1, item2 -> item1.compereAlfabetical(item1, item2) }

        searchView.doOnTextChanged { text, _, _, _ ->
            gameListViewAdapter.filter.filter(text)
        }

        sortButton.setOnClickListener {
            val bottomSheet = BottomSheetDialog()
            bottomSheet.show(FragmentManager.findFragmentManager(view), "ModalBottomSheet")
            bottomSheet.setAdapter(gameListViewAdapter)
        }
    }
}