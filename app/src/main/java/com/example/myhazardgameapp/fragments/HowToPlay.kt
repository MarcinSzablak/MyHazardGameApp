package com.example.myhazardgameapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.other.FragmentStack
import com.example.myhazardgameapp.other.HowToPlay

class HowToPlay : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_how_to_play, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentStack.currentGameSelectedStack = HowToPlay()

        super.onViewCreated(view, savedInstanceState)
    }
}