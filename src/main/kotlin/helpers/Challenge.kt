package me.sirsam.challenges.helpers

open class Challenge {
    open fun onChallengeEnable() {}
    open fun onChallengeDisable() {}

    /**
     * Will only be called if the challenge is enabled!
     */
    open fun onPluginEnable() {}
    open fun onPluginDisable() {}
}