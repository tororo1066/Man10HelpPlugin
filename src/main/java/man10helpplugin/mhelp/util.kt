package man10helpplugin.mhelp

import man10helpplugin.mhelp.mhelp.Companion.plugin
import man10helpplugin.mhelp.mhelp.Companion.shownow
import man10helpplugin.mhelp.mhelp.Companion.shownumber
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

object util {

    fun firsthelp(p : Player){
        if (shownow[p] == true)return
        shownow[p] = true
        shownumber[p] = plugin.config.getInt("show.kaisuu")
        p.gameMode = GameMode.SPECTATOR
        plugin.config.getLocation("show.first.loc")?.let { p.teleport(it) }
        p.sendTitle(plugin.config.getString("show.first.txt"),plugin.config.getString("show.first.subtxt"),plugin.config.getInt("show.fadein"),plugin.config.getInt("show.title"),plugin.config.getInt("show.fadeout"))

        object : BukkitRunnable(){
            override fun run() {
                showhelp(p)
            }
        }.runTaskLater(plugin,plugin.config.getInt("show.title").toLong())
    }
    fun showhelp(p: Player){
        if (shownow[p] == false)return
        if (shownumber[p] == -1){
            plugin.config.getLocation("show.last.loc")?.let { p.teleport(it) }
            p.sendTitle(plugin.config.getString("show.last.txt"), plugin.config.getString("show.last.subtxt"),plugin.config.getInt("show.fadein"),plugin.config.getInt("show.title"),plugin.config.getInt("show.fadeout"))
            p.gameMode = GameMode.SURVIVAL
            shownow[p] = false
            return
        }
        object : BukkitRunnable(){
            override fun run() {

                plugin.config.getLocation("show.${plugin.config.getInt("show.kaisuu") - shownumber[p]!!}.loc")?.let { p.teleport(it) }
                p.sendTitle(plugin.config.getString("show.${plugin.config.getInt("show.kaisuu") - shownumber[p]!!}.txt"), plugin.config.getString("show.${plugin.config.getInt("show.kaisuu") - shownumber[p]!!}.subtxt"), plugin.config.getInt("show.fadein"), plugin.config.getInt("show.title"), plugin.config.getInt("show.fadeout"))
                shownumber[p] = shownumber[p]?.minus(1)!!
                showhelp(p)
            }
        }.runTaskLater(plugin,plugin.config.getInt("show.title").toLong())

    }
}