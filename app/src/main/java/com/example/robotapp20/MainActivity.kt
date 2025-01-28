package com.example.robotapp20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private lateinit var yellowRobot: ImageView
    private lateinit var redRobot: ImageView
    private lateinit var whiteRobot: ImageView
    private lateinit var purchaseReward: Button

    private lateinit var robotImages: MutableList<ImageView>
    private val robotViewModel: RobotViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        yellowRobot = findViewById(R.id.yellow_robot_large)
        redRobot = findViewById(R.id.red_robot_large)
        whiteRobot = findViewById(R.id.white_robot_large)
        robotImages = mutableListOf(redRobot, whiteRobot, yellowRobot)
        purchaseReward = findViewById(R.id.purchase_reward)

        //sets images if app is re-opened or rotated
        if (robotViewModel.turnCount != 0){
            setImages()
        }

        yellowRobot.setOnClickListener {
            robotViewModel.advanceTurn()
//            Toast.makeText(this, "TurnCount : ${robotViewModel.turnCount}", Toast.LENGTH_SHORT).show()
            setImages()
        }

        redRobot.setOnClickListener {
            robotViewModel.advanceTurn()
            setImages()
        }

        whiteRobot.setOnClickListener {
            robotViewModel.advanceTurn()
            Toast.makeText(this, "My Energy : ${robotViewModel.robots[robotViewModel.turnCount-1].myEnergy}.", Toast.LENGTH_SHORT).show()
            setImages()
        }

        purchaseReward.setOnClickListener {
            if (robotViewModel.turnCount == 0){
                robotViewModel.robots[0].myEnergy = 1
                val intent = RobotPurchaseActivity.newIntent(this, robotViewModel.robots[0].myEnergy)
                startActivity(intent)
            } else {
                val intent = RobotPurchaseActivity.newIntent(this, robotViewModel.robots[robotViewModel.turnCount - 1].myEnergy)
                startActivity(intent)
            }
        }

    }// end of onCreate

    private fun setImages() {
        for (indy in robotViewModel.robots.indices) {
            if (robotViewModel.robots[indy].myTurn) {
                robotImages[indy].setImageResource(robotViewModel.robots[indy].largeImgRes)
            } else {
                robotImages[indy].setImageResource(robotViewModel.robots[indy].smallImgRes)
            }
        }
    }

}