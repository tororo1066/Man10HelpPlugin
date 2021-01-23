package man10helpplugin.mhelp

import man10helpplugin.mhelp.util.showhelp
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class mhelp : JavaPlugin(),CommandExecutor{

    companion object{
        const val prefix = "[§dm§fhelp] "
        lateinit var plugin : mhelp
        val shownow = HashMap<Player,Boolean>()
    }

    override fun onEnable() {
        plugin = this
        getCommand("mhelp")?.setExecutor(this)
        server.pluginManager.registerEvents(EventListener, plugin)
        saveDefaultConfig()
        
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player)return false

        if (command.label == "mhelp"){
            if (args.isEmpty()) {
                sender.sendMessage("§a===============Man10HelpPlugin===============")
                sender.sendMessage("§a/mhelp commands (ページ)コマンドのヘルプを表示します")
                sender.sendMessage("§a/mhelp kinnsaku おすすめの金策")
                sender.sendMessage("§a/mhelp words man10の単語集")
                if (sender.hasPermission("admin")){
                    sender.sendMessage("§a/mhelp show (Player) man10の説明が見れます")
                }
                sender.sendMessage("§a===============Man10HelpPlugin===============")
                return true
            }
            when(args[0]){
                "show"->{
                    if (!sender.hasPermission("admin")){
                        sender.sendMessage(prefix + "あなたはこのコマンドを実行する権限がありません！")
                        return true
                    }
                    if (args.size == 2){
                        val p = Bukkit.getPlayer(args[1])

                        if (p == null){
                            sender.sendMessage(prefix + "そのプレイヤーは存在しません！")
                            return true
                        }
                        if (!p.isOnline){
                            sender.sendMessage(prefix + "そのプレイヤーはオフラインです！")
                            return true
                        }
                        showhelp(p)
                        return true
                    }
                    showhelp(sender)

                }
                "commands"->{

                }
            }
        }
        return true
    }
}