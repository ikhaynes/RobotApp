package com.example.robotapp20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
//import android.widget.Toast
import androidx.activity.viewModels

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var yellowRobot : ImageView
    private lateinit var redRobot : ImageView
    private lateinit var whiteRobot : ImageView
    private lateinit var rotCWButton : ImageButton
    private lateinit var rotCCWButton : ImageButton

    private var turnCount = 0

    private val robots = listOf(
    Robot(false, R.drawable.robot_red_large, R.drawable.robot_red_small),
    Robot(false, R.drawable.robot_white_large, R.drawable.robot_white_small),
    Robot(false, R.drawable.robot_yellow_large, R.drawable.robot_yellow_small)
    )

    private val robotViewModel : RobotViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "OnCreate(savedInstanceState)")
        val robots = listOf(
            Robot(false, R.drawable.robot_red_large, R.drawable.robot_red_small),
            Robot(false, R.drawable.robot_white_large, R.drawable.robot_white_small),
            Robot(false, R.drawable.robot_yellow_large, R.drawable.robot_yellow_small)

        )

        yellowRobot = findViewById(R.id.yellow_robot_large)
        redRobot = findViewById(R.id.red_robot_large)
        whiteRobot = findViewById(R.id.white_robot_large)

        rotCWButton = findViewById(R.id.rot_cw)
        rotCCWButton = findViewById(R.id.rot_ccw)
//
        rotCWButton.setOnClickListener{
            reverseTurn()
        }

        rotCCWButton.setOnClickListener {
            advanceTurn()
        }

//        yellowRobot.setOnClickListener {
//            advanceTurn()
//        }
//
//        redRobot.setOnClickListener {
//            advanceTurn()
//        }
//
//        whiteRobot.setOnClickListener {
//            advanceTurn()
//        }
    }// end of onCreate


    private fun advanceTurn(){
        turnCount += 1
        if(turnCount > 3){
            turnCount = 1
        }
        setImages()
    }

    private fun reverseTurn(){
        turnCount -= 1
        if (turnCount == -1){
            turnCount = 1
        }
        if(turnCount < 1 ){
            turnCount = 3
        }
        setImages()
    }

    private fun setImages(){

        if(turnCount == 1){
            redRobot.setImageResource(R.drawable.robot_red_large)
            whiteRobot.setImageResource(R.drawable.robot_white_small)
            yellowRobot.setImageResource(R.drawable.robot_yellow_small)
        } else if(turnCount == 2){
            redRobot.setImageResource(R.drawable.robot_red_small)
            whiteRobot.setImageResource(R.drawable.robot_white_large)
            yellowRobot.setImageResource(R.drawable.robot_yellow_small)
        } else {
            redRobot.setImageResource(R.drawable.robot_red_small)
            whiteRobot.setImageResource(R.drawable.robot_white_small)
            yellowRobot.setImageResource(R.drawable.robot_yellow_large)
        }
    }


    private fun setRobotTurn(){
        for(robot in robots){
            robot.myTurn = false
        }
        robots[turnCount - 1].myTurn = true
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
}