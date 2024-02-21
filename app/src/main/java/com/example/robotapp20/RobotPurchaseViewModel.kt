package com.example.robotapp20
import androidx.lifecycle.ViewModel

class RobotPurchaseViewModel : ViewModel() {

    private var _robotEnergy : Int = 2

    var robotEnergy : Int
        get() = _robotEnergy
        set(value) {_robotEnergy = value}

    fun purchaseItem(cost : Int, reward : String): String {
        return if (_robotEnergy >= cost){
            _robotEnergy -= cost
            "$reward Purchased"
        } else {
            "Insufficient Resources"
        }
    }
}