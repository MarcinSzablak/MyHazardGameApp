package com.example.myhazardgameapp.fragments.toolsFragments.chohan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

object ChohanCalculatorData: ViewModel() {
    private val _choPointsSum = MutableLiveData(0)
    val choPointsSum: LiveData<Int> = _choPointsSum

    fun incrementChoPointsSum(value: Int){
        _choPointsSum.value = (_choPointsSum.value ?: 0) + value

        _pointsSum.value = (_pointsSum.value ?: 0) + value
    }

    fun decrementChoPointsSum(value: Int){
        _choPointsSum.value = (_choPointsSum.value ?: 0) - value

        _pointsSum.value = (_pointsSum.value ?: 0) - value
    }

    private val _hanPointsSum = MutableLiveData(0)
    var hanPointsSum: LiveData<Int> = _hanPointsSum

    fun incrementHanPointsSum(value: Int){
        _hanPointsSum.value = (_hanPointsSum.value ?: 0) + value

        _pointsSum.value = (_pointsSum.value ?: 0) + value
    }

    fun decrementHanPointsSum(value: Int){
        _hanPointsSum.value = (_hanPointsSum.value ?: 0) - value

        _pointsSum.value = (_pointsSum.value ?: 0) - value
    }

    private val _pointsSum = MutableLiveData(0)
    var pointsSum: LiveData<Int> = _pointsSum

}