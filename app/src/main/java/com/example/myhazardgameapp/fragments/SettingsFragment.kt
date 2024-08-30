package com.example.myhazardgameapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.other.FragmentStack
import com.google.android.material.appbar.MaterialToolbar

class SettingsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentStack.mainStack.push(SettingsFragment())

        val settingsMenu = view.findViewById<MaterialToolbar>(R.id.settings_menu)

        settingsMenu.setNavigationOnClickListener {
            FragmentStack.mainStack.pop()
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_left
            )?.replace(R.id.main_fragment_container, FragmentStack.mainStack.peek())
                ?.commit()
        }
    }
}