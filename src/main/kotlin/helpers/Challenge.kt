package me.sirsam.challenges.helpers

interface Challenge {
    /**
     * Will only be called if the challenge is enabled!
     */
    fun onEnable() {}
    fun onDisable() {}
}