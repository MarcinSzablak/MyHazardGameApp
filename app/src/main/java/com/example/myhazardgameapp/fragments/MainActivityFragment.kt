package com.example.myhazardgameapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.FragmentActivity
import com.example.myhazardgameapp.R
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

        val gameListViewAdapter = GameListViewAdapter(requireActivity() , gameList)
        gameListView.adapter = gameListViewAdapter
    }
}