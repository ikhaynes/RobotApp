package com.example.robotapp20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
//import android.widget.Toast
import androidx.activity.viewModels

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var yellowRobot: ImageView
    private lateinit var redRobot: ImageView
    private lateinit var whiteRobot: ImageView

    private lateinit var robotImages: MutableList<ImageView>

    private val robots = listOf(
        Robot(false, R.drawable.robot_red_large, R.drawable.robot_red_small),
        Robot(false, R.drawable.robot_white_large, R.drawable.robot_white_small),
        Robot(false, R.drawable.robot_yellow_large, R.drawable.robot_yellow_small)
    )

    private val robotViewModel: RobotViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        yellowRobot = findViewById(R.id.yellow_robot_large)
        redRobot = findViewById(R.id.red_robot_large)
        whiteRobot = findViewById(R.id.white_robot_large)

        robotImages = mutableListOf(redRobot, whiteRobot, yellowRobot)

        yellowRobot.setOnClickListener {
            advanceTurn()
            Toast.makeText(this, "TurnCount : ${robotViewModel.turnCount}", Toast.LENGTH_SHORT)
                .show()
        }

        redRobot.setOnClickListener {
            advanceTurn()
        }

        whiteRobot.setOnClickListener {
            advanceTurn()
        }

    }// end of onCreate


    private fun advanceTurn() {
        robotViewModel.turnCount += 1
        if (robotViewModel.turnCount > 3) {
            robotViewModel.turnCount = 1
        }
        setRobotTurn()
        setImages()
    }

    private fun setImages() {
        for (indy in robots.indices) {
            if (robots[indy].myTurn) {
                robotImages[indy].setImageResource(robots[indy].largeImgRes)
            } else {
                robotImages[indy].setImageResource(robots[indy].smallImgRes)
            }
        }
    }


    private fun setRobotTurn() {
        for (robot in robots) {
            robot.myTurn = false
        }
        robots[robotViewModel.turnCount - 1].myTurn = true
    }
}