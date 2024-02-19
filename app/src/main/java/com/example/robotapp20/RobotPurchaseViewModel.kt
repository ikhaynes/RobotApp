//package com.example.robotappv3
package com.example.robotapp20
import androidx.lifecycle.ViewModel

class RobotPurchaseViewModel : ViewModel() {

    private var _purchasePower : Int = 2

    var purchasePower : Int
        get() = _purchasePower
        set(value) {_purchasePower = value}

    fun purchaseItem(cost : Int, reward : String): String {
        return if (_purchasePower >= cost){
            _purchasePower -= cost
            "$reward Purchased"
        } else {
            "Insufficient Resources"
        }
    }
}