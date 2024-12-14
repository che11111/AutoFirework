package com.che1sbukkit.AutoFirework.utils.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtil
{
    /**
     * 获取给定位置的四个面的位置数组。
     *
     * @param start 起始位置。
     * @return 包含四个面位置的数组。
     */
    public static Location[] getFaces(Location start) {
        // 创建一个 Location 数组，用于存储四个面的位置
        Location[] faces = { new Location(start.getWorld(), start.getX() + 1.0, start.getY(), start.getZ()), new Location(start.getWorld(), start.getX() - 1.0, start.getY(), start.getZ()), new Location(start.getWorld(), start.getX(), start.getY() + 1.0, start.getZ()), new Location(start.getWorld(), start.getX(), start.getY() - 1.0, start.getZ()) };
        // 返回包含四个面位置的数组
        return faces;
    }

    /**
     * 将 Location 对象序列化为字符串。
     *
     * @param location 要序列化的位置。
     * @return 序列化后的字符串。
     */
    public static String serialize(Location location) {
        // 将 Location 对象的各个属性拼接成一个字符串，用冒号分隔
        return location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":" + location.getYaw() + ":" + location.getPitch();
    }

    /**
     * 从序列化的字符串中反序列化出 Location 对象。
     *
     * @param source 序列化的字符串。
     * @return 反序列化后的 Location 对象。
     */
    public static Location deserialize(String source) {
        // 将字符串分割成数组，每个元素对应 Location 对象的一个属性
        String[] split = source.split(":");
        // 根据分割后的数组创建一个新的 Location 对象
        return new Location(Bukkit.getServer().getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), Float.parseFloat(split[4]), Float.parseFloat(split[5]));
    }
}

