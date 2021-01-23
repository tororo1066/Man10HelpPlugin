package man10helpplugin.mhelp

import man10helpplugin.mhelp.mhelp.Companion.shownow
import man10helpplugin.mhelp.util.showhelp
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerQuitEvent

object EventListener : Listener {
    @EventHandler
    fun join(e : PlayerJoinEvent){
        if (!e.player.hasPlayedBefore())showhelp(e.player)
    }

    @EventHandler
    fun leave(e : PlayerQuitEvent){
        if (shownow[e.player] == true) {
            shownow[e.player] = false
            e.player.gameMode = GameMode.SURVIVAL
            e.player.teleport(Location(e.player.world,-311.0,63.0,-332.0))
        }
    }

    @EventHandler
    fun move(e : PlayerMoveEvent){

        if (shownow[e.player] == true){
            e.isCancelled = true
        }
    }
}