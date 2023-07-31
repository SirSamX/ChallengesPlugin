package me.sirsam.challenges.challanges

import me.sirsam.challenges.helpers.Challenge
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material

enum class ChallengeManager(val challengeName: Component, val clazz: Challenge, val description: MutableList<Component>, val item: Material) {
    TEST(
        challengeName = Component.text("Test", NamedTextColor.DARK_RED),
        clazz = TestChallenge(),
        description = mutableListOf(Component.text("Test", NamedTextColor.DARK_RED)),
        item = Material.BARRIER
    ),
    ALL_ITEMS(
        challengeName = Component.text("All Items", NamedTextColor.GOLD),
        clazz = AllItems(),
        description = mutableListOf(Component.text("Collect all items!", NamedTextColor.YELLOW)),
        item = Material.ITEM_FRAME
    ),
    FALLING_ANVILS(
        challengeName = Component.text("Falling Anvils", NamedTextColor.DARK_GRAY),
        clazz = FallingAnvils(),
        description = mutableListOf(Component.text("Anvils spawn 35 blocks above you!", NamedTextColor.YELLOW), Component.text("...always", NamedTextColor.GRAY)),
        item = Material.ANVIL
    ),
    LevelBorder(
      challengeName = Component.text("LevelBorder", NamedTextColor.GREEN),
      clazz = LevelBorder(),
      description = mutableListOf(Component.text("Per Level the bordersize will increase!", NamedTextColor.GREEN)),
      item = Material.EXPERIENCE_BOTTLE
    );

    companion object {
        fun getNames(): List<String> {
            return ChallengeManager.values().map { it.name }
        }
    }
}