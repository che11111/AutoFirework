package com.che1sbukkit.AutoFirework.commands;

import com.che1sbukkit.AutoFirework.AFFirework;
import com.che1sbukkit.AutoFirework.AFPlugin;
import com.che1sbukkit.AutoFirework.FireworksHandler;
import com.che1sbukkit.AutoFirework.utils.config.ConfigCursor;
import com.che1sbukkit.AutoFirework.utils.config.ConfigUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class AFManageCommand extends Command {
    public AFManageCommand() {
        // 初始化命令，设置命令名为 "/af"，并设置命令的使用方法
        super("af");
        this.setUsage(ChatColor.GOLD + "/af" + ChatColor.WHITE + " create <name> <delayTicks> <height>\n " +
                ChatColor.GOLD + "/af" + ChatColor.WHITE + " delete <name>");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        // 判断命令的发送者是否为玩家
        if (!(commandSender instanceof Player)) {
            // 如果不是玩家，则发送错误消息
            commandSender.sendMessage(ChatColor.RED + "This command can only be executed by players.");
            return true;
        }
        // 判断命令参数是否为空
        if (strings.length == 0) {
            return false;
        }
        // 将命令发送者转换为玩家对象
        Player player = (Player) commandSender;
        // 判断玩家是否为管理员
        if (!player.isOp()) {
            // 如果不是管理员，则发送错误消息
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }
        // 获取主命令
        String mainCommand = strings[0];
        // 判断主命令是否为 "create"
        if (mainCommand.equalsIgnoreCase("create")) {
            // 判断命令参数是否为 4 个
            if (strings.length != 4) {
                return false;
            }
            // 获取烟花名称，并转换为小写
            String name = strings[1].toLowerCase();
            try {
                // 将延迟时间和高度转换为整数
                int delay = Integer.parseInt(strings[2]);
                int height = Integer.parseInt(strings[3]);
                // 判断高度是否大于等于 127
                if (height >= 127) {
                    // 如果高度大于等于 127，则发送错误消息
                    player.sendMessage(ChatColor.RED + "The height must be less than 127.");
                    return true;
                }
                // 获取玩家当前位置
                Location location = player.getLocation();
                // 判断烟花名称是否已存在
                if (FireworksHandler.getAFFireworkMap().containsKey(name)) {
                    // 如果烟花名称已存在，则发送错误消息
                    player.sendMessage(ChatColor.RED + "A firework with that name already exists.");
                    return true;
                }
                // 创建新的烟花对象
                AFFirework firework = new AFFirework(name, delay, height, location);
                // 将新的烟花对象添加到烟花映射中
                FireworksHandler.getAFFireworkMap().put(name, firework);
                // 保存烟花数据
                firework.saveFirework();
                // 发送成功消息
                player.sendMessage(ChatColor.GREEN + "Firework '" + name + "' created.");
                return true;
            } catch (NumberFormatException e) {
                // 如果转换失败，则发送错误消息
                player.sendMessage(ChatColor.RED + "Please insert valid numbers.");
                return true;
            }

        }
        // 判断主命令是否为 "delete"
        if (mainCommand.equalsIgnoreCase("delete")) {
            // 判断命令参数是否为 2 个
            if (strings.length != 2) {
                return false;
            }
            // 获取烟花名称，并转换为小写
            String name = strings[1].toLowerCase();
            // 判断烟花名称是否存在
            if (!FireworksHandler.getAFFireworkMap().containsKey(name)) {
                // 如果烟花名称不存在，则发送错误消息
                player.sendMessage(ChatColor.RED + "A firework with that name does not exist.");
                return true;
            }
            // 从烟花映射中移除烟花对象
            AFFirework firework = FireworksHandler.getAFFireworkMap().remove(name);
            // 判断烟花对象是否为空
            if (firework == null) {
                // 如果烟花对象为空，则发送错误消息
                player.sendMessage(ChatColor.RED + "A firework with that name does not exist.");
                return true;
            }
            // 创建配置光标对象
            ConfigCursor cursor = new ConfigCursor(new ConfigUtil(AFPlugin.getInstance(), "data.yml"), "fireworks." + name);
            // 将配置项设置为空
            cursor.set(null);
            // 保存配置
            cursor.save();
            // 发送成功消息
            player.sendMessage(ChatColor.RED + "Firework '" + name + "' deleted.");
            return true;
        }
        return false;
    }
}
