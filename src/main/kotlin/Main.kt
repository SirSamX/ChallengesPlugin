package me.sirsam.challenges

import me.sirsam.challenges.commands.Challenge
import me.sirsam.challenges.commands.TimerCommand
import me.sirsam.challenges.commands.TimerCompleter
import me.sirsam.challenges.listeners.OnInventoryClick
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

    }

    private fun registerCommands() {
        getCommand("challenge")?.setExecutor(Challenge())
        getCommand("timer")?.setExecutor(TimerCommand())
        getCommand("timer")?.tabCompleter = TimerCompleter()
    }

    private fun registerEvents() {
        Bukkit.getPluginManager().registerEvents(OnInventoryClick(), this)
    }
}