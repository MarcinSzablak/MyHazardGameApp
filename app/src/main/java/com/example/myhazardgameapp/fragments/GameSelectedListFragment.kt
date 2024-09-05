package com.example.myhazardgameapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.fragments.gameSelectedList.SelectedGameChangeSupport
import com.example.myhazardgameapp.fragments.gameSelectedList.SelectedGameListAdapter
import com.example.myhazardgameapp.other.FragmentStack

class GameSelectedListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_selected_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentStack.currentGameSelectedStack = GameSelectedListFragment()

        val listView = view.findViewById<ListView>(R.id.game_selected_list_listview)

        listView.adapter = SelectedGameListAdapter(requireActivity(),
            SelectedGameChangeSupport.SelectedGame.toolsArray)

    }
}