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

    fun changeint(s : String, p : Player): Int {
        var i = 0
        try {
            i = s.toInt()
        }catch (e : NumberFormatException){
            p.sendMessage(prefix + "数字を使用してください！")
        }
        return i
    }

    fun firsthelp(p : Player){
        if (shownow[p] == true)return
        shownow[p] = true
        shownumber[p] = plugin.config.getInt("show1.kaisuu")
        p.gameMode = GameMode.SPECTATOR
        plugin.config.getLocation("show1.first.loc")?.let { p.teleport(it) }
        p.sendTitle(plugin.config.getString("show1.first.txt"),plugin.config.getString("show1.first.subtxt"),plugin.config.getInt("show1.fadein"),plugin.config.getInt("show1.title"),plugin.config.getInt("show1.fadeout"))
        showhelp(p)
    }

    fun firsthelp2(p : Player){
        if (shownow[p] == true)return
        shownow[p] = true
        shownumber[p] = plugin.config.getInt("show2.kaisuu")
        p.gameMode = GameMode.SPECTATOR
        plugin.config.getLocation("show2.first.loc")?.let { p.teleport(it) }
        p.sendTitle(plugin.config.getString("show2.first.txt"),plugin.config.getString("show2.first.subtxt"),plugin.config.getInt("show2.fadein"),plugin.config.getInt("show2.title"),plugin.config.getInt("show2.fadeout"))
        showhelp2(p)
    }

    fun firsthelp3(p : Player){
        if (shownow[p] == true)return
        shownow[p] = true
        shownumber[p] = plugin.config.getInt("show3.kaisuu")
        p.gameMode = GameMode.SPECTATOR
        plugin.config.getLocation("show3.first.loc")?.let { p.teleport(it) }
        p.sendTitle(plugin.config.getString("show3.first.txt"),plugin.config.getString("show3.first.subtxt"),plugin.config.getInt("show3.fadein"),plugin.config.getInt("show3.title"),plugin.config.getInt("show3.fadeout"))
        showhelp3(p)
    }

    fun showhelp(p: Player){

        if (shownumber[p] == -1 || shownow[p] == false){
            plugin.config.getLocation("show1.last.loc")?.let { p.teleport(it) }
            p.sendTitle(plugin.config.getString("show1.last.txt"), plugin.config.getString("show1.last.subtxt"),plugin.config.getInt("show1.fadein"),plugin.config.getInt("show1.title"),plugin.config.getInt("show1.fadeout"))
            p.gameMode = GameMode.SURVIVAL
            shownow[p] = false
            return
        }
        object : BukkitRunnable(){
            override fun run() {

                plugin.config.getLocation("show1.${plugin.config.getInt("show1.kaisuu") - shownumber[p]!!}.loc")?.let { p.teleport(it) }
                p.sendTitle(plugin.config.getString("show1.${plugin.config.getInt("show1.kaisuu") - shownumber[p]!!}.txt"), plugin.config.getString("show1.${plugin.config.getInt("show1.kaisuu") - shownumber[p]!!}.subtxt"), plugin.config.getInt("show1.fadein"), plugin.config.getInt("show1.title"), plugin.config.getInt("show1.fadeout"))
                shownumber[p] = shownumber[p]?.minus(1)!!
                showhelp(p)
            }
        }.runTaskLater(plugin,plugin.config.getInt("show1.title").toLong())

    }

    fun showhelp2(p : Player){

        if (shownumber[p] == -1 || shownow[p] == false){
            plugin.config.getLocation("show2.last.loc")?.let { p.teleport(it) }
            p.sendTitle(plugin.config.getString("show2.last.txt"), plugin.config.getString("show2.last.subtxt"),plugin.config.getInt("show2.fadein"),plugin.config.getInt("show2.title"),plugin.config.getInt("show2.fadeout"))
            p.gameMode = GameMode.SURVIVAL
            shownow[p] = false
            return
        }
        object : BukkitRunnable(){
            override fun run() {

                plugin.config.getLocation("show2.${plugin.config.getInt("show2.kaisuu") - shownumber[p]!!}.loc")?.let { p.teleport(it) }
                p.sendTitle(plugin.config.getString("show2.${plugin.config.getInt("show2.kaisuu") - shownumber[p]!!}.txt"), plugin.config.getString("show2.${plugin.config.getInt("show2.kaisuu") - shownumber[p]!!}.subtxt"), plugin.config.getInt("show2.fadein"), plugin.config.getInt("show2.title"), plugin.config.getInt("show2.fadeout"))
                shownumber[p] = shownumber[p]?.minus(1)!!
                showhelp2(p)
            }
        }.runTaskLater(plugin,plugin.config.getInt("show2.title").toLong())

    }

    fun showhelp3(p : Player){

        if (shownumber[p] == -1 || shownow[p] == false){
            plugin.config.getLocation("show3.last.loc")?.let { p.teleport(it) }
            p.sendTitle(plugin.config.getString("show3.last.txt"), plugin.config.getString("show3.last.subtxt"),plugin.config.getInt("show3.fadein"),plugin.config.getInt("show3.title"),plugin.config.getInt("show3.fadeout"))
            p.gameMode = GameMode.SURVIVAL
            shownow[p] = false
            return
        }
        object : BukkitRunnable(){
            override fun run() {

                plugin.config.getLocation("show3.${plugin.config.getInt("show3.kaisuu") - shownumber[p]!!}.loc")?.let { p.teleport(it) }
                p.sendTitle(plugin.config.getString("show3.${plugin.config.getInt("show3.kaisuu") - shownumber[p]!!}.txt"), plugin.config.getString("show2.${plugin.config.getInt("show3.kaisuu") - shownumber[p]!!}.subtxt"), plugin.config.getInt("show3.fadein"), plugin.config.getInt("show3.title"), plugin.config.getInt("show3.fadeout"))
                shownumber[p] = shownumber[p]?.minus(1)!!
                showhelp3(p)
            }
        }.runTaskLater(plugin,plugin.config.getInt("show3.title").toLong())

    }
}