package com.che1sbukkit.AutoFirework;

import com.che1sbukkit.AutoFirework.utils.config.ConfigCursor;
import com.che1sbukkit.AutoFirework.utils.config.ConfigUtil;
import com.che1sbukkit.AutoFirework.utils.config.LocationUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

@RequiredArgsConstructor
public class AFFirework {

    // 定义一个静态的随机数生成器，用于生成随机的烟花效果
    private static Random random = new Random();

    // 烟花的名称，使用 @Getter 注解自动生成 getter 方法
    @Getter private final String name;
    // 烟花的延迟时间，使用 @Getter 注解自动生成 getter 方法
    @Getter private final long delay;
    // 烟花的高度，使用 @Getter 注解自动生成 getter 方法
    @Getter private final int height;
    // 烟花的位置，使用 @Getter 注解自动生成 getter 方法
    @Getter private final Location location;

    // 烟花的最后发射时间，初始值为 -1，表示尚未发射，使用 @Getter 注解自动生成 getter 方法
    @Getter
    private long lastLaunch = -1;

    /**
     * 发射烟花的方法。
     * 这个方法会在指定的位置生成一个烟花实体，并设置随机的烟花效果，然后更新最后发射时间。
     */
    public void launch() {
        // 在烟花的位置上方 0.5 个方块处生成一个烟花实体
        Firework firework = location.getWorld().spawn(location.clone().add(0,0.5,0), Firework.class);
        // 获取烟花的元数据
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        // 设置烟花的威力（即飞行高度）
        fireworkMeta.setPower(height);
        // 添加一个随机的烟花效果
        fireworkMeta.addEffect(FireworkEffect.builder()
                // 随机设置是否闪烁
                .flicker(random.nextBoolean())
                // 随机设置是否拖尾
                .trail(random.nextBoolean())
                // 随机选择一个烟花类型
                .with(FireworkEffect.Type.values()[random.nextInt(FireworkEffect.Type.values().length)])
                // 随机选择一个颜色
                .withColor(Color.fromRGB(random.nextInt(255), random.nextInt(255), random.nextInt(255)))
                // 随机选择一个渐变颜色
                .withFade(Color.fromRGB(random.nextInt(255), random.nextInt(255), random.nextInt(255)))
                // 构建烟花效果
                .build());
        // 设置烟花的元数据
        firework.setFireworkMeta(fireworkMeta);
        // 更新最后发射时间
        lastLaunch = System.currentTimeMillis();
    }

    /**
     * 保存烟花数据的方法。
     * 这个方法会将烟花的位置和其他相关数据保存到配置文件中。
     */
    public void saveFirework() {
        // 创建一个新的配置工具实例
        ConfigUtil configUtil = new ConfigUtil(AFPlugin.getInstance(), "data.yml");
        // 创建一个新的配置光标实例
        ConfigCursor cursor = new ConfigCursor(configUtil, "fireworks." + name);
        // 设置延迟值
        cursor.set("delay", delay);
        // 设置高度值
        cursor.set("height", height);
        // 序列化位置并设置
        cursor.set("location", LocationUtil.serialize(location));
        // 保存配置
        cursor.save();
    }
}
