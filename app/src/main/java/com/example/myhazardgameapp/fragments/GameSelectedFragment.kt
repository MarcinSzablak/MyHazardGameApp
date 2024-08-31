package com.example.myhazardgameapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.fragments.gameSelectedList.SelectedGameChangeSupport
import com.example.myhazardgameapp.other.FragmentStack
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


        //set up starting fragment depending which was last
        if (FragmentStack.gameSelectedStack.peek().id == 0){
            fragmentManager?.beginTransaction()
                ?.replace(R.id.game_selected_fragment_container,
                    FragmentStack.gameSelectedStack.peek())
                ?.commit()
        } else{
            fragmentManager?.beginTransaction()
                ?.replace(R.id.game_selected_fragment_container,
                    FragmentStack.currentGameSelectedStack)
                ?.commit()
        }

        gameSelectedMenu.title = SelectedGameChangeSupport.SelectedGame.title

        gameSelectedMenu.setNavigationOnClickListener {
            val fragmentTransaction = fragmentManager?.beginTransaction()
            if (FragmentStack.gameSelectedStack.peek() is GameSelectedListFragment){
                FragmentStack.mainStack.pop()
                FragmentStack.gameSelectedStack.pop()
                fragmentTransaction
                    ?.setCustomAnimations(
                        R.anim.slide_in_left,
                        R.anim.slide_out_left)
                    ?.replace(R.id.main_fragment_container, FragmentStack.mainStack.peek())
                    ?.commit()
            } else{
                FragmentStack.gameSelectedStack.pop()
                fragmentTransaction
                    ?.setCustomAnimations(
                        R.anim.slide_in_left,
                        R.anim.slide_out_left)
                    ?.replace(R.id.game_selected_fragment_container,
                    FragmentStack.gameSelectedStack.peek())
                    ?.commit()
            }
        }

        gameSelectedMenu.setOnMenuItemClickListener { menuItem ->
            when( menuItem.itemId ){
                R.id.game_selected_menu_settings ->{
                    val fragmentTransaction = fragmentManager?.beginTransaction()
                    fragmentTransaction
                        ?.setCustomAnimations(
                            R.anim.slide_in_right,
                            R.anim.slide_out_right)
                        ?.replace(R.id.main_fragment_container, SettingsFragment())
                        ?.commit()
                    true
                }else ->{
                    false
                }
            }

        }
    }
}