package com.example.robotapp20

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "RobotViewModel"
class RobotViewModel : ViewModel(){
    init{
        Log.d(TAG, "RobotViewModel about to be created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "RobotViewModel about to be cleared")
    }
}