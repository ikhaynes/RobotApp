package com.example.robotapp20

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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

        setTurnText(robotViewModel.turnCount)


        yellowRobot.setOnClickListener {
            advanceTurn()
        }

        redRobot.setOnClickListener {
            advanceTurn()
        }

        whiteRobot.setOnClickListener {
            advanceTurn()
        }

        purchaseReward.setOnClickListener {
            if (robotViewModel.turnCount == 0){
                Toast.makeText(this, "Click on a robot to start the game", Toast.LENGTH_SHORT).show()
            } else {
                val intent = RobotPurchaseActivity.newIntent(this)
                intent.putExtra("robotData", robotViewModel.robots[robotViewModel.turnCount-1])
                purchaseLauncher.launch(intent)
            }
        }

    }// end of onCreate


    private val purchaseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
//        if(result.resultCode == Activity.RESULT_CANCELED)
//            Toast.makeText(this,"Data Canceled ", Toast.LENGTH_SHORT).show()

        if(result.resultCode == Activity.RESULT_OK) {
            // returns value sent from RobotPurchaseActivity
            val robotPurchaseMade = result.data?.getStringExtra(EXTRA_ROBOT_PURCHASE_MADE) ?: "0"
            val rewardPurchased = result.data?.getStringExtra("reward")

            // update robot energy and rewardsPurchased
            for (robot in robotViewModel.robots) {
                if (robot.myTurn){
                    robot.myEnergy -= robotPurchaseMade.toInt()
                    robot.rewardsPurchased.add(0, rewardPurchased.toString())
                }
            }
        }
    }


    private fun advanceTurn() {
        robotViewModel.turnCount += 1
        if (robotViewModel.turnCount > 3) {
            robotViewModel.turnCount = 1
        }
        setRobotTurn()
        setImages()
        setTurnText(robotViewModel.turnCount)
        showPurchasedRewards(robotViewModel.turnCount-1)
    }

    private fun setRobotTurn() {
        for (robot in robotViewModel.robots) {
            robot.myTurn = false
        }

        //step 4
        if (robotViewModel.turnCount == 0) {
            robotViewModel.robots[0].myTurn = true
            robotViewModel.robots[0].myEnergy += 1
        } else {
            robotViewModel.robots[robotViewModel.turnCount - 1].myTurn = true
            robotViewModel.robots[robotViewModel.turnCount - 1].myEnergy += 1


            // show last reward purchased (Task 1)
            // comment out below lines and call showPurchasedRewards() in advanceTurn for final
//            if (robotViewModel.robots[robotViewModel.turnCount - 1].rewardsPurchased.size > 0){
//                Toast.makeText(this, "Last Purchase: ${robotViewModel.robots[robotViewModel.turnCount-1].rewardsPurchased[0]}", Toast.LENGTH_SHORT).show()
//            }
        }
    }

    private fun setImages() {
        for (indy in robotViewModel.robots.indices) {
            if (robotViewModel.robots[indy].myTurn) {
                robotImages[indy].setImageResource(robotViewModel.robots[indy].largeImgRes)
            } else {
                robotImages[indy].setImageResource(robotViewModel.robots[indy].smallImgRes)
            }
        }
    }

    private fun setTurnText(count : Int){
        val robotTurnText = findViewById<TextView>(R.id.robot_turn_text)

        when (count) {
            1 -> {
                robotTurnText.setText(R.string.red_robot_turn)
            }
            2 -> {
                robotTurnText.setText(R.string.white_robot_turn)
            }
            3 -> {
                robotTurnText.setText(R.string.yellow_robot_turn)
            }
        }
    }

    private fun showPurchasedRewards(indy : Int ) {
        var toastMessage = "Purchased Rewards:"
        if ( (indy >= 0) and (robotViewModel.robots[indy].rewardsPurchased.size > 0) ) {
            for (reward in robotViewModel.robots[indy].rewardsPurchased) {
                toastMessage += " $reward"
            }

            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }
}