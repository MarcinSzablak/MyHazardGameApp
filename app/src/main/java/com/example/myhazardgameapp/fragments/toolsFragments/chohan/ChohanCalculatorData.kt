package com.example.myhazardgameapp.fragments.toolsFragments.chohan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.math.BigDecimal

object ChohanCalculatorData: ViewModel() {
    //cho
    private val _choPointsSum = MutableLiveData(0L)
    val choPointsSum: LiveData<Long> = _choPointsSum

    fun resetAllSums(){
        resetChoPointsSum()
        resetHanPointsSum()
        resetPointsSum()
    }

    private fun resetChoPointsSum(){
        _choPointsSum.value = 0
    }

    fun incrementChoPointsSum(value: Int){
        _choPointsSum.value = (_choPointsSum.value ?: 0) + value

        incrementPointsSum(value)
    }

    fun decrementChoPointsSum(value: Int){
        _choPointsSum.value = (_choPointsSum.value ?: 0) - value

        decrementPointsSum(value)
    }

    //han
    private val _hanPointsSum = MutableLiveData(0L)
    val hanPointsSum: LiveData<Long> = _hanPointsSum

    private fun resetHanPointsSum(){
        _hanPointsSum.value = 0
    }

    fun incrementHanPointsSum(value: Int){
        _hanPointsSum.value = (_hanPointsSum.value ?: 0) + value

        incrementPointsSum(value)
    }

    fun decrementHanPointsSum(value: Int){
        _hanPointsSum.value = (_hanPointsSum.value ?: 0) - value

        decrementPointsSum(value)
    }

    //cho + han = pointsSum
    private val _pointsSum = MutableLiveData(0L)
    val pointsSum: MutableLiveData<Long> = _pointsSum

    private fun resetPointsSum(){
        _pointsSum.value = 0
    }

    private fun incrementPointsSum(value: Int){
        _pointsSum.value = (_pointsSum.value ?: 0) + value
    }

    private fun decrementPointsSum(value: Int){
        _pointsSum.value = (_pointsSum.value ?: 0) - value
    }

}