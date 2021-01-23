package man10helpplugin.mhelp

import man10helpplugin.mhelp.mhelp.Companion.plugin
import man10helpplugin.mhelp.mhelp.Companion.shownow
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

object util {

    fun showhelp(p: Player){
        if (shownow[p] == true)return
        plugin.saveDefaultConfig()
        shownow[p] = true
        p.gameMode = GameMode.SPECTATOR
        p.sendTitle("§dM§fA§aN§f10について知ろう！","",5,40,5)


        var timer = 30
        val c = plugin.config
                object : BukkitRunnable(){
                    override fun run() {
                        when(timer){
                            27->{
                                p.sendTitle("§dm§fa§an§f10ロビー","皆が集う場所 (/warp man10で行けるよ！)",5,100,5)
                                p.teleport(Location(plugin.server.getWorld(c.getString("show.man10")!!),-300.0,80.0,-314.0,15f,30f))
                            }
                            24->{
                                p.sendTitle("§0§kaa§r§c§lSlum§r§0§0§kaa","PvPonの危険地区 (/warp slumで行けるよ！)",5,100,5)
                                p.teleport(Location(plugin.server.getWorld(c.getString("show.slum")!!),-28.0,150.0,-50.0,90f,90f))
                            }
                            21->{
                                p.sendTitle("§6§kaa§r§e§lGacha§r§6§kaa","色んなガチャを引こう (/warp gachaで行けるよ！)",5,100,5)
                                p.teleport(Location(plugin.server.getWorld(c.getString("show.gacha")!!),-321.0,65.0,-300.0,-165f,0f))
                            }
                            18->{
                                p.sendTitle("§9§kaa§b§lFishing§r§9§kaa","釣りをしよう (/warp fishingで行けるよ！)",5,100,5)
                                p.teleport(Location(plugin.server.getWorld(c.getString("show.fishing")!!),-66.0,27.0,-177.0,30f,36f))
                            }
                            15->{
                                p.sendTitle("§2§kaa§r§4§lTomato Jungle§r§2§kaa","様々な建築が立ち並ぶ建築区域 (/warp tomatoで行けるよ！)",5,100,5)
                                p.teleport(Location(plugin.server.getWorld(c.getString("show.tomato")!!),-790.0,155.0,395.0,50f,30f))
                            }
                            12->{
                                p.sendTitle("§7§kaa§r§f§lEco§r§7§kaa","ビルが立ち並ぶ建築区域 (/warp ecoで行けるよ！)",5,100,5)
                                p.teleport(Location(plugin.server.getWorld(c.getString("show.eco")!!),-631.0,135.0,-139.0,140f,30f))
                            }
                            9->{
                                p.sendTitle("§2§kaa§r§d§lOceano§r§2§kaa","露店や洋築が立ち並ぶ建築区域 (/warp oceanoで行けるよ！)",5,100,5)
                                p.teleport(Location(plugin.server.getWorld(c.getString("show.oceano")!!),286.0,88.0,635.0,-90f,25f))
                            }
                            6->{
                                p.sendTitle("§2§kaa§r§8§l桜風町(おうふうちょう)§2§kaa","和の建物が立ち並ぶ建築区域",5,100,5)
                                p.teleport(Location(plugin.server.getWorld(c.getString("show.ouhuu")!!),350.0,110.0,222.0,170f,20f))
                            }
                            3->{
                                p.sendTitle("","他にもいろいろなことがあるよ！",5,100,5)
                                p.teleport(Location(plugin.server.getWorld(c.getString("show.tutorial")!!),18.0,4.0,-44.0,-180f,0f))
                            }
                            0->{
                                p.sendTitle("§l楽しもう！！","",5,100,5)
                                p.gameMode = GameMode.SURVIVAL

                                shownow[p] = false
                            }

                        }
                        timer--
                    }
                }.runTaskTimer(plugin,0,40)

    }
}