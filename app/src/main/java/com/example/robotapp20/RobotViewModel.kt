package com.example.robotapp20

import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.ViewModel

//private const val TAG = "RobotViewModel"
class RobotViewModel : ViewModel(){
//    init{
//        Log.d(TAG, "RobotViewModel about to be created")
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        Log.d(TAG, "RobotViewModel about to be cleared")
//    }


    private var _turnCount = 0
    var turnCount : Int
        get() = _turnCount
        set(value) {_turnCount = value}

    val robots = listOf(
        Robot(false, R.drawable.robot_red_large, R.drawable.robot_red_small),
        Robot(false, R.drawable.robot_white_large, R.drawable.robot_white_small),
        Robot(false, R.drawable.robot_yellow_large, R.drawable.robot_yellow_small)
    )

//    lateinit var robotImages: MutableList<ImageView>

    fun advanceTurn(){
        _turnCount += 1
        if (_turnCount > 3) {
            _turnCount = 1
        }
        setRobotTurn()
//        setImages()
    }
    private fun setRobotTurn() {
        for (robot in robots) {
            robot.myTurn = false
        }

        //step 4
        if (_turnCount == 0){
            robots[0].myTurn = true
        } else {
            robots[_turnCount - 1].myTurn = true
        }
    }

//    private fun setImages() {
//        for (indy in robots.indices) {
//            if (robots[indy].myTurn) {
//                robotImages[indy].setImageResource(robots[indy].largeImgRes)
//            } else {
//                robotImages[indy].setImageResource(robots[indy].smallImgRes)
//            }
//        }
//    }
}