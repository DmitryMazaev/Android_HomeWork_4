package com.example.android_homework_4

import kotlin.random.Random

class Check {
    var isManSex: Boolean = true
    var isNotification: Boolean = true
    //lateinit var user: User
    fun isValidUserName (userName: CharSequence?): Boolean {
        if (!userName.isNullOrEmpty()) {
            return true
        }
        else {
            return false
        }
    }
    fun isValidTelephoneNumber (userTelephone: CharSequence?): Boolean {
        return if ((!userTelephone.isNullOrEmpty() && userTelephone.length == 11 && !userTelephone.contains("+")) || (!userTelephone.isNullOrEmpty() && userTelephone.length == 12 && userTelephone.contains("+"))) {
            true
        } else {
            false
        }
    }
    fun checkAutorisation():Boolean {
        return true
    }
    fun checkNovelty(): Boolean {
        return true
    }

    fun balls (): Int {
        var balls: Int = Random.nextInt(101)
        return balls
    }
    fun progress(count: Int): String {
        return "$count/100"
    }

    fun ckeckUser(user: User):Boolean {
        if ((user.userName !=null && user.userTelephone !=null &&
                    user.isManSex !=null && user.userNotification !=null &&
                    user.userAutorisation !=null) ||
            (user.userName !=null && user.userTelephone !=null &&
                    user.isManSex !=null && user.userNotification !=null &&
                    user.userNovelty !=null)) {
            return true
        }
        return false
    }

}