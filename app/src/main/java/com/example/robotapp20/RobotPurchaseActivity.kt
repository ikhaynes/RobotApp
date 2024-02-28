package com.example.robotapp20

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

private const val EXTRA_ROBOT_ENERGY = "com.example.robotapp20.current_robot_energy"
const val EXTRA_ROBOT_PURCHASE_MADE = "com.example.robotapp20.current_robot_purchase_made"
class RobotPurchaseActivity : AppCompatActivity() {

    // initialize variables
    private lateinit var rewardButtonA: Button
    private lateinit var rewardButtonB: Button
    private lateinit var rewardButtonC: Button
    private lateinit var robotEnergyView: TextView
    private val robotPurchaseViewModel: RobotPurchaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_robot_purchase)

        rewardButtonA = findViewById(R.id.reward_a)
        rewardButtonB = findViewById(R.id.reward_b)
        rewardButtonC = findViewById(R.id.reward_c)

        robotEnergyView = findViewById(R.id.robot_purchase_power)

        robotPurchaseViewModel.robotEnergy = intent.getIntExtra(EXTRA_ROBOT_ENERGY, 6)
        robotEnergyView.text = robotPurchaseViewModel.robotEnergy.toString()

        rewardButtonA.setOnClickListener {
            purchaseItem(1, "Reward A")
        }

        rewardButtonB.setOnClickListener {
            purchaseItem(2, "Reward B")
        }

        rewardButtonC.setOnClickListener {
            purchaseItem(3, "Reward C")
        }
    }   // end of onCreate

    private fun setWhichItemPurchased(robotPurchaseMade : Int){
        val data = Intent().apply{
            putExtra(EXTRA_ROBOT_PURCHASE_MADE, robotPurchaseMade.toString())
        }
        setResult(Activity.RESULT_OK, data)
    }
    companion object{
        fun newIntent(packageContext: Context, robotEnergy : Int): Intent {
            return Intent(packageContext, RobotPurchaseActivity::class.java).apply{
                putExtra(EXTRA_ROBOT_ENERGY, robotEnergy)
            }
        }
    }
    private fun purchaseItem(cost : Int, reward : String) {
        val retMessage = robotPurchaseViewModel.purchaseItem(cost, reward)
        Toast.makeText(this, retMessage, Toast.LENGTH_SHORT).show()
        robotEnergyView.text = robotPurchaseViewModel.robotEnergy.toString()

        // idea for HW
        setWhichItemPurchased(cost)
        // only do this if there were sufficient resources. . .
        if ("Purchased" in retMessage){
            finish()
        }
//        finish()
    }

    // might be able to use the previous function (with the one in robotPurchaseViewModel)
    // instead of the one below. . .
//    private fun purchaseItem(cost : Int, reward : String) {
//        if (robotPurchaseViewModel.robotEnergy >= cost){
//            robotPurchaseViewModel.robotEnergy -= cost
//            Toast.makeText(this, "$reward Purchased", Toast.LENGTH_SHORT).show()
//
//        } else {
//            Toast.makeText(this, "Insufficient Resources", Toast.LENGTH_SHORT).show()
//        }
//        robotEnergyView.text = robotPurchaseViewModel.robotEnergy.toString()
//    }

}