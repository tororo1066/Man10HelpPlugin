package man10helpplugin.mhelp

import man10helpplugin.mhelp.mhelp.Companion.plugin
import man10helpplugin.mhelp.mhelp.Companion.prefix
import man10helpplugin.mhelp.mhelp.Companion.shownow
import man10helpplugin.mhelp.mhelp.Companion.shownumber
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

object util {

    fun changeint(s: String, p: Player): Int {
        var i = 0
        try {
            i = s.toInt()
        } catch (e: NumberFormatException) {
            p.sendMessage(prefix + "数字を使用してください！")
        }
        return i
    }

    fun firsthelp(p: Player, i: Int) {
        if (shownow[p] == true) return
        shownow[p] = true
        shownumber[p] = plugin.config.getInt("show$i.kaisuu")
        p.gameMode = GameMode.SPECTATOR
        plugin.config.getLocation("show$i.first.loc")?.let { p.teleport(it) }
        p.sendTitle(plugin.config.getString("show$i.first.txt"), plugin.config.getString("show$i.first.subtxt"), plugin.config.getInt("show$i.fadein"), plugin.config.getInt("show$i.title"), plugin.config.getInt("show$.fadeout"))
        showhelp(p,i)
    }


    fun showhelp(p: Player, i : Int) {


        object : BukkitRunnable() {
            override fun run() {

                plugin.config.getLocation("show$i.${plugin.config.getInt("show$i.kaisuu") - shownumber[p]!!}.loc")?.let { p.teleport(it) }
                p.sendTitle(plugin.config.getString("show$i.${plugin.config.getInt("show$i.kaisuu") - shownumber[p]!!}.txt"), plugin.config.getString("show$i.${plugin.config.getInt("show$i.kaisuu") - shownumber[p]!!}.subtxt"), plugin.config.getInt("show$i.fadein"), plugin.config.getInt("show$i.title"), plugin.config.getInt("show$i.fadeout"))
                shownumber[p] = shownumber[p]?.minus(1)!!
                if (shownumber[p] == -1 || shownow[p] == false) {
                    plugin.config.getLocation("show$i.last.loc")?.let { p.teleport(it) }
                    p.sendTitle(plugin.config.getString("show$i.last.txt"), plugin.config.getString("show$i.last.subtxt"), plugin.config.getInt("show$i.fadein"), plugin.config.getInt("show$i.title"), plugin.config.getInt("show$i.fadeout"))
                    p.gameMode = GameMode.SURVIVAL
                    shownow[p] = false
                    return
                }
                showhelp(p,i)
            }
        }.runTaskLater(plugin, plugin.config.getInt("show$i.title").toLong())

    }

}