package me.sirsam.challenges
import org.bukkit.GameRule
import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.WorldType

object PvpDimension {
    var world: World

    init {
        val worldCreator = WorldCreator("pvp_dimension")
        worldCreator.type(WorldType.FLAT)
        worldCreator.generateStructures(false)
        world = Main.getPlugin().server.createWorld(worldCreator)!!
        world.keepSpawnInMemory = false
        world.pvp = true
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false)
        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false)
        world.setGameRule(GameRule.DISABLE_RAIDS, true)
        world.setGameRule(GameRule.KEEP_INVENTORY, true)
        world.setGameRule(GameRule.RANDOM_TICK_SPEED, 0)
        world.setGameRule(GameRule.SPAWN_RADIUS, 0)
        world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true)
    }
}