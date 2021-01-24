package man10helpplugin.mhelp

import man10helpplugin.mhelp.util.changeint
import man10helpplugin.mhelp.util.firsthelp
import man10helpplugin.mhelp.util.firsthelp2
import man10helpplugin.mhelp.util.firsthelp3
import man10helpplugin.mhelp.util.showhelp
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.lang.NumberFormatException

class mhelp : JavaPlugin(),CommandExecutor{

    companion object{
        const val prefix = "[§dm§fhelp] "
        lateinit var plugin : mhelp
        val shownow = HashMap<Player,Boolean>()
        val shownumber = HashMap<Player,Int>()
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
                sender.sendMessage("§a======================Man10HelpPlugin======================")
                sender.sendMessage("§a/mhelp commands (ページ)コマンドのヘルプを表示します")
                sender.sendMessage("§a/mhelp kinnsaku おすすめの金策")
                sender.sendMessage("§a/mhelp words man10の単語集")
                sender.sendMessage("§a/mhelp show ムービーが見れます")
                if (sender.hasPermission("admin")){
                    sender.sendMessage("§a/mhelp show (Player) ムービーを見せることができます")
                    sender.sendMessage("§a/mhelp set (Int(0~)) (title) (subtitle) (point) ムービーの場所、")
                    sender.sendMessage("§aタイトル、順番を指定できます")
                    sender.sendMessage("§a/mhelp reload configをリロードします(誰かがムービーを見てるとできません)")
                    sender.sendMessage("§a/mhelp showsetting(ss) ムービーの設定ができます")

                }
                sender.sendMessage("§a======================Man10HelpPlugin======================")
                return true
            }
            when(args[0]){
                "show"->{

                    if (args.size == 3){
                        if (!sender.hasPermission("admin")){
                            sender.sendMessage(prefix + "あなたはこのコマンドを実行する権限がありません！")
                            return true
                        }
                        val p = Bukkit.getPlayer(args[1])

                        if (p == null){
                            sender.sendMessage(prefix + "そのプレイヤーは存在しません！")
                            return true
                        }
                        if (!p.isOnline){
                            sender.sendMessage(prefix + "そのプレイヤーはオフラインです！")
                            return true
                        }
                        when(changeint(args[2],sender)) {
                            1 -> {
                                firsthelp(p)
                                return true
                            }
                            2 -> {
                                firsthelp2(p)
                                return true
                            }
                            3 ->{
                                firsthelp3(p)
                                return true
                            }
                        }

                        return true
                    }else{
                        if (args.size != 2)return true
                        when(changeint(args[1],sender)){
                            1->{
                                firsthelp(sender)
                                return true
                            }
                            2->{
                                firsthelp2(sender)
                                return true
                            }
                            3->{
                                firsthelp3(sender)
                                return true
                            }
                        }
                    }


                }
                "set"->{
                    if (args.size != 5)return true
                    val n : Int
                    val point : Int
                    try {
                        n = args[1].toInt()
                        point = args[4].toInt()
                    }catch (e : NumberFormatException){
                        sender.sendMessage(prefix + "指定は数字でしてください！")
                        return true
                    }
                    val title = args[2].replace("&","§")
                    val subtitle = args[3].replace("&","§")


                    plugin.config.set("show$point.$n.txt",title)
                    plugin.config.set("show$point.$n.subtxt",subtitle)
                    plugin.config.set("show$point.$n.loc",sender.location)
                    sender.sendMessage(prefix + "コンフィグへの書き込みが完了しました")
                    sender.sendMessage("$prefix/mhelp reloadで設定を変更してください")
                    return true
                }

                "showsetting","ss"->{
                    if (!sender.hasPermission("admin")){
                        sender.sendMessage(prefix + "あなたはこのコマンドを実行する権限がありません！")
                        return true
                    }
                    if (args.size == 1){
                        sender.sendMessage("§a=========================Man10HelpPlugin=========================")
                        sender.sendMessage("§akaisuu (Int) (point) タイトルの表示回数を指定します")
                        sender.sendMessage("§atitle (Int) (point) タイトルの表示時間をtick単位で指定します")
                        sender.sendMessage("§afadein (Int) (point) タイトルのフェードインの時間をtick単位で指定します")
                        sender.sendMessage("§afadeout (Int) (point) タイトルのフェードアウトの時間をtick単位で指定します")
                        sender.sendMessage("§afirst (title) (subtitle) (point) 最初のタイトルを設定します")
                        sender.sendMessage("§alast (title) (subtitle) (point) 最後のタイトルを設定します")
                        sender.sendMessage("§a=========================Man10HelpPlugin=========================")
                    }else {
                        when (args[1]) {

                            "kaisuu" -> {
                                if (args.size != 4) {
                                    sender.sendMessage(prefix + "args.sizeが非正規です！")
                                    return true
                                }
                                val n: Int
                                val point : Int
                                try {
                                    n = args[2].toInt()
                                    point = args[3].toInt()
                                } catch (e: NumberFormatException) {
                                    sender.sendMessage(prefix + "Int型で指定してください！")
                                    return true
                                }
                                plugin.config.set("show$point.kaisuu", n)
                                sender.sendMessage(prefix + "コンフィグへの書き込みが完了しました")
                                sender.sendMessage("$prefix/mhelp reloadで設定を変更してください")
                                return true

                            }

                            "title" -> {
                                if (args.size != 4) {
                                    sender.sendMessage(prefix + "args.sizeが非正規です！")
                                    return true
                                }
                                val n: Int
                                val point : Int
                                try {
                                    n = args[2].toInt()
                                    point = args[3].toInt()
                                } catch (e: NumberFormatException) {
                                    sender.sendMessage(prefix + "Int型で指定してください！")
                                    return true
                                }
                                plugin.config.set("show$point.title", n)
                                sender.sendMessage(prefix + "コンフィグへの書き込みが完了しました")
                                sender.sendMessage("$prefix/mhelp reloadで設定を変更してください")
                                return true

                            }

                            "fadein" -> {
                                if (args.size != 4) {
                                    sender.sendMessage(prefix + "args.sizeが非正規です！")
                                    return true
                                }
                                val n: Int
                                val point : Int
                                try {
                                    n = args[2].toInt()
                                    point = args[3].toInt()
                                } catch (e: NumberFormatException) {
                                    sender.sendMessage(prefix + "Int型で指定してください！")
                                    return true
                                }
                                plugin.config.set("show$point.fadein", n)
                                sender.sendMessage(prefix + "コンフィグへの書き込みが完了しました")
                                sender.sendMessage("$prefix/mhelp reloadで設定を変更してください")
                                return true
                            }

                            "fadeout" -> {
                                if (args.size != 4) {
                                    sender.sendMessage(prefix + "args.sizeが非正規です！")
                                    return true
                                }
                                val n: Int
                                val point : Int
                                try {
                                    n = args[2].toInt()
                                    point = args[3].toInt()
                                } catch (e: NumberFormatException) {
                                    sender.sendMessage(prefix + "Int型で指定してください！")
                                    return true
                                }
                                plugin.config.set("show$point.fadeout", n)
                                sender.sendMessage(prefix + "コンフィグへの書き込みが完了しました")
                                sender.sendMessage("$prefix/mhelp reloadで設定を変更してください")
                                return true
                            }
                            "first" -> {
                                if (args.size != 5) {
                                    sender.sendMessage(prefix + "args.sizeが非正規です！")
                                    return true
                                }
                                val title = args[2].replace("&","§")
                                val subtitle = args[3].replace("&","§")
                                val point = changeint(args[4],sender)
                                plugin.config.set("show$point.first.txt", title)
                                plugin.config.set("show$point.first.subtxt", subtitle)
                                plugin.config.set("show$point.first.loc", sender.location)
                                sender.sendMessage(prefix + "コンフィグへの書き込みが完了しました")
                                sender.sendMessage("$prefix/mhelp reloadで設定を変更してください")
                                return true
                            }

                            "last" -> {
                                if (args.size != 5) {
                                    sender.sendMessage(prefix + "args.sizeが非正規です！")
                                    return true
                                }
                                val title = args[2].replace("&","§")
                                val subtitle = args[3].replace("&","§")
                                val point = changeint(args[4],sender)
                                plugin.config.set("show$point.last.txt", title)
                                plugin.config.set("show$point.last.subtxt", subtitle)
                                plugin.config.set("show$point.last.loc", sender.location)
                                sender.sendMessage(prefix + "コンフィグへの書き込みが完了しました")
                                sender.sendMessage("$prefix/mhelp reloadで設定を変更してください")
                                return true
                            }
                            else -> return true
                        }
                    }
                }

                "commands"->{

                }
                "reload"->{
                    for (n in Bukkit.getOnlinePlayers()){
                        if (shownow[n] == true){
                            sender.sendMessage(prefix + n.name + "がムービーを見ています！")
                            return true
                        }
                    }
                    saveConfig()
                    sender.sendMessage(prefix + "コンフィグのリロードが完了しました")
                    return true
                }
                else->return true
            }
        }
        return true
    }
}