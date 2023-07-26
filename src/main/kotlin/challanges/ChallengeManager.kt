package me.sirsam.challenges.challanges

import me.sirsam.challenges.helpers.Challenge

enum class ChallengeManager(val challengeName: String, val namespace: String, val clazz: Challenge) {
    TEST("Test", "test", TestChallenge()),
    ALL_ITEMS("All Items", "all_items", AllItems())
}