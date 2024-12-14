package com.che1sbukkit.AutoFirework.utils.config;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ConfigCursor
{
    // 配置文件工具类
    private ConfigUtil fileConfig;
    // 当前操作的路径
    private String path;

    /**
     * 检查当前路径是否存在。
     *
     * @return 如果存在则返回 true，否则返回 false。
     */
    public boolean exists() {
        return this.exists(null);
    }

    /**
     * 检查指定路径是否存在。
     *
     * @param path 要检查的路径。
     * @return 如果存在则返回 true，否则返回 false。
     */
    public boolean exists(String path) {
        return this.fileConfig.getConfig().contains(this.path + ((path == null) ? "" : ("." + path)));
    }

    /**
     * 获取当前路径下的所有键。
     *
     * @return 包含所有键的集合。
     */
    public Set<String> getKeys() {
        return this.getKeys(null);
    }

    /**
     * 获取指定路径下的所有键。
     *
     * @param path 要获取键的路径。
     * @return 包含所有键的集合。
     */
    public Set<String> getKeys(String path) {
        return (Set<String>)this.fileConfig.getConfig().getConfigurationSection(this.path + ((path == null) ? "" : ("." + path))).getKeys(false);
    }

    /**
     * 获取指定路径下的 double 值。
     *
     * @param path 要获取值的路径。
     * @return double 值。
     */
    public double getDouble(String path) {
        return this.fileConfig.getConfig().getDouble(((this.path == null) ? "" : (this.path + ".")) + "." + path);
    }

    /**
     * 获取指定路径下的字符串值。
     *
     * @param path 要获取值的路径。
     * @return 字符串值。
     */
    public String getString(String path) {
        return this.fileConfig.getConfig().getString(((this.path == null) ? "" : (this.path + ".")) + path);
    }

    /**
     * 获取指定路径下的布尔值。
     *
     * @param path 要获取值的路径。
     * @return 布尔值。
     */
    public boolean getBoolean(String path) {
        return this.fileConfig.getConfig().getBoolean(((this.path == null) ? "" : (this.path + ".")) + "." + path);
    }

    /**
     * 获取指定路径下的整数值。
     *
     * @param path 要获取值的路径。
     * @return 整数值。
     */
    public int getInt(String path) {
        return this.fileConfig.getConfig().getInt(((this.path == null) ? "" : (this.path + ".")) + "." + path);
    }

    /**
     * 获取指定路径下的长整数值。
     *
     * @param path 要获取值的路径。
     * @return 长整数值。
     */
    public long getLong(String path) {
        return this.fileConfig.getConfig().getLong(((this.path == null) ? "" : (this.path + ".")) + "." + path);
    }

    /**
     * 获取指定路径下的字符串列表。
     *
     * @param path 要获取值的路径。
     * @return 字符串列表。
     */
    public List<String> getStringList(String path) {
        return this.fileConfig.getConfig().getStringList(((this.path == null) ? "" : (this.path + ".")) + "." + path);
    }

    /**
     * 获取指定路径下的 UUID 值。
     *
     * @param path 要获取值的路径。
     * @return UUID 值。
     */
    public UUID getUuid(String path) {
        return UUID.fromString(this.fileConfig.getConfig().getString(this.path + "." + path));
    }

    /**
     * 获取指定路径下的世界对象。
     *
     * @param path 要获取值的路径。
     * @return 世界对象。
     */
    public World getWorld(String path) {
        return Bukkit.getWorld(this.fileConfig.getConfig().getString(this.path + "." + path));
    }

    /**
     * 设置指定路径的值。
     *
     * @param value 要设置的值。
     */
    public void set(Object value) {
        this.set(null, value);
    }

    /**
     * 设置指定路径的值。
     *
     * @param path 要设置值的路径。
     * @param value 要设置的值。
     */
    public void set(String path, Object value) {
        this.fileConfig.getConfig().set(this.path + ((path == null) ? "" : ("." + path)), value);
    }

    /**
     * 保存配置文件。
     */
    public void save() {
        this.fileConfig.save();
    }

    /**
     * 构造函数，用于初始化 ConfigCursor 对象。
     *
     * @param fileConfig 配置文件工具类。
     * @param path 初始路径。
     */
    public ConfigCursor(ConfigUtil fileConfig, String path) {
        this.fileConfig = fileConfig;
        this.path = path;
    }

    /**
     * 获取配置文件工具类。
     *
     * @return 配置文件工具类。
     */
    public ConfigUtil getFileConfig() {
        return this.fileConfig;
    }

    /**
     * 获取当前路径。
     *
     * @return 当前路径。
     */
    public String getPath() {
        return this.path;
    }

    /**
     * 设置当前路径。
     *
     * @param path 要设置的路径。
     */
    public void setPath(String path) {
        this.path = path;
    }
}
