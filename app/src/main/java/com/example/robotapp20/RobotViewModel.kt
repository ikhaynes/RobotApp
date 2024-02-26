package com.example.robotapp20

import androidx.lifecycle.ViewModel

class RobotViewModel : ViewModel() {

    private var _turnCount = 0
    var turnCount: Int
        get() = _turnCount
        set(value) {
            _turnCount = value
        }

    val robots = listOf(
        Robot(false, R.drawable.robot_red_large, R.drawable.robot_red_small, myEnergy = 0),
        Robot(false, R.drawable.robot_white_large, R.drawable.robot_white_small, myEnergy = 0),
        Robot(false, R.drawable.robot_yellow_large, R.drawable.robot_yellow_small, myEnergy = 0)
    )

    fun advanceTurn() {
        _turnCount += 1
        if (_turnCount > 3) {
            _turnCount = 1
        }
        setRobotTurn()
    }

    private fun setRobotTurn() {
        for (robot in robots) {
            robot.myTurn = false
        }

        //step 4
        if (_turnCount == 0) {
            robots[0].myTurn = true
            robots[0].myEnergy += 1
        } else {
            robots[_turnCount - 1].myTurn = true
            robots[_turnCount - 1].myEnergy += 1
        }
    }

}