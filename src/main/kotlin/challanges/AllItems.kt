package me.sirsam.challenges.challanges

import me.sirsam.challenges.ChallengeTimer
import me.sirsam.challenges.helpers.ChallengeStatus
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.BlockDisplay
import org.bukkit.entity.EntityType
import org.bukkit.entity.ItemDisplay
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryPickupItemEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.MainHand
import org.bukkit.scheduler.BukkitRunnable
import org.eclipse.sisu.launch.Main
import javax.swing.event.ChangeEvent

class AllItems {
    private var item: MutableMap<Player, Material> = mutableMapOf()

    fun newItem(player: Player) {
        item[player] = Material.values().random()

        if (ChallengeTimer.timer.getStatus() != ChallengeStatus.ACTIVE || item[player] == null) return
        val loc = player.location
        val armorStand = loc.world.spawnEntity(loc, EntityType.ARMOR_STAND) as ArmorStand
        armorStand.isInvisible = true
        armorStand.isSmall = true
        armorStand.isInvulnerable = true
        armorStand.setItem(EquipmentSlot.HEAD, ItemStack(item[player]!!))
        player.addPassenger(armorStand)

        /*if (item[player]!!.isBlock) {
            player.sendMessage("BLOCK")
            val display = player.world.spawn(loc, BlockDisplay::class.java)
            display.block = Bukkit.createBlockData(item[player]!!)
            object : BukkitRunnable() {
                override fun run() {
                    val location = player.location
                    loc.y += 2.5
                    display.teleport(location)
                }
            }.runTaskTimer(me.sirsam.challenges.Main.getPlugin(), 0, 1L)
        } else {
            player.sendMessage("ITEM")
            val display = player.world.spawn(loc, ItemDisplay::class.java)
            display.itemStack = ItemStack(item.getValue(player))
            object : BukkitRunnable() {
                override fun run() {
                    val location = player.location
                    loc.y += 2.5
                    display.teleport(location)
                }
            }.runTaskTimer(Main.getPlugin(), 0, 1L)
        }*/
    }

    fun getItem(player: Player): Material? {
        return item[player]
    }

    fun getBossbar(player: Player): BossBar {
        if (item[player] == null) {
            newItem(player)
        }
        val item = ItemStack(item[player]!!)
        return BossBar.bossBar(Component.text("Current Item: ", NamedTextColor.YELLOW).append(item.displayName()), 1f, BossBar.Color.YELLOW, BossBar.Overlay.PROGRESS)
    }

    fun sendBossbar(player: Player) {
        player.showBossBar(getBossbar(player))
    }

    /*fun collectedItem(mainHand: MainHand, player: Player) {
        var main =
        if (item !is )
    }*/

    @EventHandler
    fun itemCollected(event: InventoryPickupItemEvent, player: Player) {
        if (event.item == item) {
            newItem(player)
        }
    }

}