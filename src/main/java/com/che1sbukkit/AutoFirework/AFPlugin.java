package com.che1sbukkit.AutoFirework;

import com.che1sbukkit.AutoFirework.commands.AFManageCommand;
import com.che1sbukkit.AutoFirework.utils.config.ConfigCursor;
import com.che1sbukkit.AutoFirework.utils.config.ConfigUtil;
import com.che1sbukkit.AutoFirework.utils.config.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;

public class AFPlugin extends JavaPlugin {

    // 静态变量，保存插件的实例
    private static AFPlugin instance;

    // 获取插件实例的方法
    public static AFPlugin getInstance() {
        return instance;
    }

    /**
     * 插件启用时执行的方法。
     * 这个方法会初始化插件的命令和配置，并启动一个定时任务来发射烟花。
     */
    @Override
    public void onEnable() {
        // 设置插件实例
        instance = this;
        // 初始化命令
        initCommand();
        // 初始化配置
        initConfig();
        // 启动一个定时任务，每 20 个游戏刻（即 1 秒）执行一次 FireworkRunnale 类的 run 方法
        Bukkit.getScheduler().runTaskTimer(this, new FireworkRunnale(), 0, 20);
        // 记录日志，表明插件已启用
        getLogger().info("AutoFirework enabled!");
    }

    /**
     * 插件禁用时执行的方法。
     * 这个方法会记录日志，表明插件已禁用。
     */
    @Override
    public void onDisable() {
        // 记录日志，表明插件已禁用
        getLogger().info("AutoFirework disabled!");
    }

    /**
     * 初始化插件命令的方法。
     * 这个方法会通过反射获取 Bukkit 服务器的命令映射，并注册一个新的命令。
     */
    private void initCommand() {
        try {
            // 通过反射获取 Bukkit 服务器的命令映射字段
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            // 设置字段可访问
            commandMapField.setAccessible(true);
            // 获取命令映射实例
            SimpleCommandMap commandMap = (SimpleCommandMap) commandMapField.get(Bukkit.getServer());
            // 注册命令
            commandMap.register("af", new AFManageCommand());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // 捕获异常并打印堆栈跟踪
            e.printStackTrace();
        }
        // 记录日志，表明命令已注册
        getLogger().info("FireworkLauncher command registered!");
    }

    /**
     * 初始化插件配置的方法。
     * 这个方法会检查配置文件是否存在，如果不存在则创建一个默认的配置文件。
     * 然后，它会从配置文件中读取所有的烟花数据，并将它们添加到一个映射中。
     */
    private void initConfig() {
        // 创建一个文件对象，指向插件的数据文件夹中的 data.yml 文件
        File datas = new File(getDataFolder(), "data.yml");
        // 如果文件不存在
        if (!datas.exists()) {
            // 将插件的默认配置选项复制到新的配置文件中
            this.getConfig().options().copyDefaults(false);
        }
        // 创建一个新的配置工具实例
        ConfigUtil dataConfig = new ConfigUtil(this, "data.yml");
        // 创建一个新的配置光标实例
        ConfigCursor cursor = new ConfigCursor(dataConfig, "fireworks");
        // 遍历配置中的所有键
        for (String key : cursor.getKeys()) {
            // 将键转换为小写，作为烟花的名称
            String name = key.toLowerCase();
            // 从配置中获取烟花的延迟时间
            long delay = cursor.getLong(name + ".delay");
            // 从配置中获取烟花的高度
            int height = cursor.getInt(name + ".height");
            // 从配置中获取烟花的位置，并反序列化为 Location 对象
            Location location = LocationUtil.deserialize(cursor.getString(name + ".location"));
            // 创建一个新的 AFFirework 对象
            AFFirework AFFirework = new AFFirework(name, delay, height, location);
            // 将新的 AFFirework 对象添加到烟花映射中
            FireworksHandler.getAFFireworkMap().put(name, AFFirework);
        }
    }
}
