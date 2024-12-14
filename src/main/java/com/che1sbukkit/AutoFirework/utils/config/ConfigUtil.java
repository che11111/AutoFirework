package com.che1sbukkit.AutoFirework.utils.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigUtil
{
    // 配置文件对象
    private File file;
    // 配置文件内容对象
    private FileConfiguration config;

    /**
     * 构造函数，用于初始化配置文件工具类
     * @param plugin 插件对象
     * @param fileName 配置文件名
     */
    public ConfigUtil(JavaPlugin plugin, String fileName) {
        // 创建配置文件对象，位于插件的数据文件夹下
        this.file = new File(plugin.getDataFolder(), fileName);
        // 如果配置文件不存在
        if (!this.file.exists()) {
            // 创建配置文件的父目录
            this.file.getParentFile().mkdirs();
            // 如果插件资源中不存在该配置文件
            if (plugin.getResource(fileName) == null) {
                try {
                    // 创建新的配置文件
                    this.file.createNewFile();
                }
                catch (IOException var4) {
                    // 记录错误信息
                    plugin.getLogger().severe("Failed to create new file " + fileName);
                }
            }
            else {
                // 从插件资源中复制配置文件到数据文件夹
                plugin.saveResource(fileName, false);
            }
        }
        // 加载配置文件内容
        this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
    }

    /**
     * 保存配置文件
     */
    public void save() {
        try {
            // 将配置内容保存到文件中
            this.config.save(this.file);
        }
        catch (IOException var2) {
            // 记录错误信息
            Bukkit.getLogger().severe("Could not save config file " + this.file.toString());
            var2.printStackTrace();
        }
    }

    /**
     * 获取配置文件对象
     * @return 配置文件对象
     */
    public File getFile() {
        return this.file;
    }

    /**
     * 获取配置文件内容对象
     * @return 配置文件内容对象
     */
    public FileConfiguration getConfig() {
        return this.config;
    }
}
