package com.che1sbukkit.AutoFirework;

import java.util.Map;

public class FireworkRunnale implements Runnable {

    @Override
    public void run() {
        // 获取所有的AFFirework对象
        Map<String, AFFirework> AFFireworkMap = FireworksHandler.getAFFireworkMap();
        // 遍历所有的AFFirework对象
        for (AFFirework AFFirework : AFFireworkMap.values()) {
            // 计算自上次发射以来的时间（毫秒）
            long havingLaunched = System.currentTimeMillis() - AFFirework.getLastLaunch();
            // 判断是否应该发射烟花
            boolean shouldLaunch = (havingLaunched >= AFFirework.getDelay()/20 * 1000) || AFFirework.getLastLaunch() == -1;
            // 如果应该发射烟花
            if (shouldLaunch) {
                // 发射烟花
                AFFirework.launch();
            }
        }
    }

}
