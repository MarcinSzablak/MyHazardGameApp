package com.example.myhazardgameapp.other

import android.util.Log
import androidx.fragment.app.Fragment
import com.example.myhazardgameapp.fragments.GameSelectedListFragment
import java.util.Stack

object FragmentStack {
    var mainStack: Stack<Fragment> = Stack()
    var gameSelectedStack: Stack<Fragment> = Stack()
}
