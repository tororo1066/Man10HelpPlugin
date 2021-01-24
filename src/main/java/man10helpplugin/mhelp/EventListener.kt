package man10helpplugin.mhelp

import man10helpplugin.mhelp.mhelp.Companion.plugin
import man10helpplugin.mhelp.mhelp.Companion.shownow
import man10helpplugin.mhelp.util.showhelp
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerQuitEvent

object EventListener : Listener {

    @EventHandler
    fun leave(e : PlayerQuitEvent){
        if (shownow[e.player] == true) {
            shownow[e.player] = false
            e.player.gameMode = GameMode.SURVIVAL
        }
    }

    @EventHandler
    fun move(e : PlayerMoveEvent){

        if (shownow[e.player] == true){
            e.isCancelled = true
        }
    }
    @EventHandler
    fun chat(e : AsyncPlayerChatEvent){
        if (e.message == "skip"){
            shownow[e.player] = false
            e.isCancelled = true
        }
    }
}