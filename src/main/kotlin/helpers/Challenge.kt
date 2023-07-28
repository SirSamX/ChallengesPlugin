package me.sirsam.challenges.helpers

open class Challenge {
    /**
     * Will only be called if the challenge is enabled!
     */
    open fun onEnable() {}
    open fun onDisable() {}
}