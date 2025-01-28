package com.example.robotapp20

import java.io.Serializable

data class Robot (
    var myTurn : Boolean,
    val largeImgRes : Int,
    val smallImgRes : Int,
    var myEnergy : Int,
    val rewardsPurchased : MutableList<String> = arrayListOf()
) : Serializable