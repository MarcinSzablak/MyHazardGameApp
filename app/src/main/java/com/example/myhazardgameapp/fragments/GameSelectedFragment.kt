package com.example.myhazardgameapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.fragments.gameSelectedList.SelectedGameChangeSupport
import com.google.android.material.appbar.MaterialToolbar

class GameSelectedFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_selected, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameSelectedMenu = view.findViewById<MaterialToolbar>(R.id.game_selected_menu)

        if (savedInstanceState == null){
            childFragmentManager.beginTransaction()
                .replace(R.id.game_selected_fragment_container, GameSelectedListFragment())
                .commit()
        }

        gameSelectedMenu.title = SelectedGameChangeSupport.SelectedGame.title

        gameSelectedMenu.setNavigationOnClickListener {
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_left
            )?.replace(R.id.main_fragment_container, MainActivityFragment())
                ?.commit()
        }

        gameSelectedMenu.setOnMenuItemClickListener { menuItem ->
            when( menuItem.itemId ){
                R.id.game_selected_menu_settings ->{
                    val fragmentTransaction = fragmentManager?.beginTransaction()
                    fragmentTransaction?.setCustomAnimations(
                        R.anim.slide_in_right,
                        R.anim.slide_out_right
                    )?.replace(R.id.main_fragment_container, SettingsFragment())
                        ?.commit()
                    true
                }else ->{
                    false
                }
            }

        }
    }
}