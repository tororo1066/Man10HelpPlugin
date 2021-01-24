package man10helpplugin.mhelp

import man10helpplugin.mhelp.mhelp.Companion.plugin
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
    fun leave(e : PlayerQuitEvent){
        if (shownow[e.player] == true) {
            shownow[e.player] = false
            e.player.gameMode = GameMode.SURVIVAL
            plugin.config.getLocation("show.last.loc")?.let { e.player.teleport(it) }
        }
    }

    @EventHandler
    fun move(e : PlayerMoveEvent){

        if (shownow[e.player] == true){
            e.isCancelled = true
        }
    }
}