package com.example.robotapp20

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import kotlin.random.nextInt

const val EXTRA_ROBOT_PURCHASE_MADE = "com.example.robotapp20.current_robot_purchase_made"
class RobotPurchaseActivity : AppCompatActivity() {

    // initialize variables
    private lateinit var rewardButtonA: Button
    private lateinit var rewardButtonB: Button
    private lateinit var rewardButtonC: Button
    private lateinit var costA: TextView
    private lateinit var costB: TextView
    private lateinit var costC: TextView
    private lateinit var robotEnergyView: TextView
    private lateinit var robotImage: ImageView

    private val rewardList = listOf("Reward A", "Reward B", "Reward C",
        "Reward D", "Reward E", "Reward F", "Reward G")
    private val rewardCostList = listOf(1, 2, 3, 3, 4, 4, 7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_robot_purchase)

        robotImage = findViewById(R.id.large_robot)
        rewardButtonA = findViewById(R.id.reward_a)
        rewardButtonB = findViewById(R.id.reward_b)
        rewardButtonC = findViewById(R.id.reward_c)

        costA = findViewById(R.id.cost_reward_a)
        costB = findViewById(R.id.cost_reward_b)
        costC = findViewById(R.id.cost_reward_c)

        setRewards()

        robotEnergyView = findViewById(R.id.robot_purchase_power)

        val robot = intent.getSerializableExtra("robotData") as Robot

        robotImage.setImageResource(robot.largeImgRes)
        robotEnergyView.text = robot.myEnergy.toString()

        rewardButtonA.setOnClickListener {
            purchaseItem(costA.text.toString().toInt(), rewardButtonA.text.toString(), robot)
        }

        rewardButtonB.setOnClickListener {
            purchaseItem(costB.text.toString().toInt(), rewardButtonB.text.toString(), robot)
        }

        rewardButtonC.setOnClickListener {
            purchaseItem(costC.text.toString().toInt(), rewardButtonC.text.toString(), robot)
        }
    }   // end of onCreate

    private fun setWhichItemPurchased(robotPurchaseMade : Int){
        val data = Intent().apply{
            putExtra(EXTRA_ROBOT_PURCHASE_MADE, robotPurchaseMade.toString())
        }
        setResult(Activity.RESULT_OK, data)
    }
    companion object{
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, RobotPurchaseActivity::class.java)
        }
    }


    private fun purchaseItem(cost : Int, reward : String, robot: Robot) {
        if ( robot.myEnergy >= cost){
            robot.myEnergy -= cost
            Toast.makeText(this, "$reward Purchased", Toast.LENGTH_SHORT).show()
            setWhichItemPurchased(cost)

        } else {
            Toast.makeText(this, "Insufficient Resources", Toast.LENGTH_SHORT).show()
        }

        robotEnergyView.text = robot.myEnergy.toString()
        finish()
    }

    private fun setRewards() {
        val randomInts = generateSequence {
            Random.nextInt(0, 7)
        }
            .distinct()
            .take(3)
            .toList()

        val rewards = mutableListOf(rewardList[ randomInts[0] ], rewardList[ randomInts[1] ], rewardList[ randomInts[2] ])
        val costs = mutableListOf(rewardCostList[ randomInts[0] ], rewardCostList[ randomInts[1] ], rewardCostList[ randomInts[2] ])
        rewards.sort()
        costs.sort()

        rewardButtonA.text = rewards[0]
        costA.text = costs[0].toString()

        rewardButtonB.text = rewards[1]
        costB.text = costs[1].toString()

        rewardButtonC.text = rewards[2]
        costC.text = costs[2].toString()
    }

}