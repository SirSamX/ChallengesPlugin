package me.sirsam.challenges.challanges

import me.sirsam.challenges.ChallengeTimer
import me.sirsam.challenges.helpers.ChallengeStatus
import org.bukkit.Material
import org.bukkit.entity.Player

class AllItems {
    fun newItem(player: Player) {
        if (ChallengeTimer.timer.getStatus() != ChallengeStatus.ACTIVE) return
        val item = Material.values().random()
        val loc = player.location
        loc.y += 2.5
        //val display =
        //player.world.spawn(loc, ItemDisplay)
    }
}