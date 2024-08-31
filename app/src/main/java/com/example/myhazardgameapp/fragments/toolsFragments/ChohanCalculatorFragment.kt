package com.example.myhazardgameapp.fragments.toolsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.other.FragmentStack

class ChohanCalculatorFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chohan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentStack.currentGameSelectedStack = ChohanCalculatorFragment()

        super.onViewCreated(view, savedInstanceState)
    }
}