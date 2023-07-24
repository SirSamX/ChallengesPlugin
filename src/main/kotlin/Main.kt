package me.sirsam.challenges

import me.sirsam.challenges.commands.Challenge
import me.sirsam.challenges.commands.Kit
import me.sirsam.challenges.commands.TimerCommand
import me.sirsam.challenges.listeners.OnInventoryClick
import me.sirsam.challenges.listeners.OnJoin
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class Main : JavaPlugin() {

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

        logger.info("Plugin enabled!")
    }

    override fun onDisable() {
        config.set("timer.status", ChallengeTimer.timer.getStatus())
        config.set("timer.seconds", ChallengeTimer.timer.get())
        saveConfig()
    }

    private fun registerCommands() {
        getCommand("challenge")?.setExecutor(Challenge())
        getCommand("challenge")?.tabCompleter = Challenge()
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