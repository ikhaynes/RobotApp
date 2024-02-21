package com.example.robotapp20

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
//import com.example.robotappv3.RobotPurchaseViewModel

private const val EXTRA_ROBOT_ENERGY = "com.example.robotapp20.current_robot_energy"
class RobotPurchaseActivity : AppCompatActivity() {

    // initialize variables
    private lateinit var rewardButtonA: Button
    private lateinit var rewardButtonB: Button
    private lateinit var rewardButtonC: Button
    private lateinit var purchasePowerView: TextView
    private lateinit var costRewardA: TextView
    private lateinit var costRewardB: TextView
    private lateinit var costRewardC: TextView
    private lateinit var retMessage: String
    private val robotPurchaseViewModel: RobotPurchaseViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_robot_purchase)

        rewardButtonA = findViewById(R.id.reward_a)
        costRewardA = findViewById(R.id.cost_reward_a)

        rewardButtonB = findViewById(R.id.reward_b)
        costRewardB = findViewById(R.id.cost_reward_b)

        rewardButtonC = findViewById(R.id.reward_c)
        costRewardC = findViewById(R.id.cost_reward_c)

        purchasePowerView = findViewById(R.id.robot_purchase_power)


        robotPurchaseViewModel.purchasePower = intent.getIntExtra(EXTRA_ROBOT_ENERGY, 6)
        purchasePowerView.text = robotPurchaseViewModel.purchasePower.toString()

        rewardButtonA.setOnClickListener {
            purchaseItem(costRewardA.text.toString().toInt(), "Reward A")
        }

        rewardButtonB.setOnClickListener {
            purchaseItem(costRewardB.text.toString().toInt(), "Reward B")
        }

        rewardButtonC.setOnClickListener {
            purchaseItem(costRewardC.text.toString().toInt(), "Reward C")
        }
    }   // end of onCreate

    companion object{
        fun newIntent(packageContext: Context, robot_energy : Int): Intent {
            return Intent(packageContext, RobotPurchaseActivity::class.java).apply{
                putExtra(EXTRA_ROBOT_ENERGY, robot_energy)
            }
        }
    }
    private fun purchaseItem(cost : Int, reward : String) {
        retMessage = robotPurchaseViewModel.purchaseItem(cost, reward)
        Toast.makeText(this, retMessage, Toast.LENGTH_SHORT).show()
        purchasePowerView.text = robotPurchaseViewModel.purchasePower.toString()
    }
}