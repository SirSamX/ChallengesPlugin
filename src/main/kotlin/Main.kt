package me.sirsam.challenges

import me.sirsam.challenges.challanges.ChallengeManager
import me.sirsam.challenges.commands.ChallengeCommand
import me.sirsam.challenges.commands.Kit
import me.sirsam.challenges.commands.TimerCommand
import me.sirsam.challenges.helpers.Utilities
import me.sirsam.challenges.listeners.OnInventoryClick
import me.sirsam.challenges.listeners.OnJoin
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    private val utils = Utilities()

    companion object {
        lateinit var instance: Main

        fun getPlugin(): Main {
            return instance
        }
    }

    override fun onEnable() {
        instance = this
        registerCommands()
        registerEvents()
        utils.setEnabled(ChallengeManager.ALL_ITEMS, true, this)
        enableChallenges()

        logger.info("Plugin enabled!")
    }

    override fun onDisable() {
        config.set("timer.status", ChallengeTimer.timer.getStatus())
        config.set("timer.seconds", ChallengeTimer.timer.get())
        disableChallenges()
        saveConfig()

        logger.info("Plugin disabled!")
    }

    private fun enableChallenges() {
        ChallengeManager.values().forEach {
                challenge ->
            if (utils.isEnabled(challenge, this)) challenge.clazz.onPluginEnable()
        }
    }

    private fun disableChallenges() {
        ChallengeManager.values().forEach {
            challenge ->
            if (utils.isEnabled(challenge, this)) challenge.clazz.onPluginDisable()
        }
    }

    private fun registerCommands() {
        getCommand("challenge")?.setExecutor(ChallengeCommand())
        getCommand("challenge")?.tabCompleter = ChallengeCommand()
        getCommand("timer")?.setExecutor(TimerCommand())
        getCommand("timer")?.tabCompleter = TimerCommand()
        getCommand("kit")?.setExecutor(Kit())
        getCommand("kit")?.tabCompleter = Kit()
    }

    private fun registerEvents() {
        Bukkit.getPluginManager().registerEvents(OnInventoryClick(), this)
        Bukkit.getPluginManager().registerEvents(OnJoin(), this)
    }
}