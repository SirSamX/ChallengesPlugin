package me.sirsam.challenges.challanges

import me.sirsam.challenges.helpers.Challenge
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material

enum class ChallengeManager(val challengeName: Component, val clazz: Challenge, val description: List<Component>, val item: Material) {
    TEST(
        challengeName = Component.text("Test", NamedTextColor.DARK_RED),
        clazz = TestChallenge(),
        description = mutableListOf(Component.text("Test", NamedTextColor.DARK_RED)),
        item = Material.BARRIER
    ),
    ALL_ITEMS(
        challengeName = Component.text("All Items", NamedTextColor.GOLD),
        clazz = AllItems(),
        description = listOf(Component.text("Collect all items!", NamedTextColor.YELLOW)),
        item = Material.ITEM_FRAME
    )
}