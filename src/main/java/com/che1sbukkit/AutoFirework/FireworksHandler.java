package com.che1sbukkit.AutoFirework;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class FireworksHandler {

    /**
     * 存储所有自动烟花的映射
     * 键是烟花的名称，值是 AFFirework 对象
     */
    @Getter
    private static Map<String, AFFirework> AFFireworkMap = new HashMap<>();

}
