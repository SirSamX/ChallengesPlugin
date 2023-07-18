package me.sirsam.challenges

import me.sirsam.challenges.helpers.Timer

object ChallengeTimer {
    val timer = Timer()
    init {
        timer.show(false)
    }
}