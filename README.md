# Fireworklauncher


# AutoFirework

<img src="AutoFirework.png" width="300" height="300" alt="image">



## 插件功能

是一款烟花筒插件

可以实现在某位置一直释放烟花的效果

节日庆典用，装饰用

释放的烟花为随机烟花



## 插件信息

**插件名字：**   Fireworklauncher

**已测试版本： **  1.20.1 

**服务端： ** Spigot、Paper等

**指令简写：**  / fl



## 茶碱指令

在游戏中输入：（仅op有权限）

1、创建烟花释放点的指令

```yaml
/fl create <烟花筒名字> <发射间隔的tick数>
#举例
#/fl create test 100
```

就可以在你的脚下生成一个 ”发射器“（虚拟的）

然后每隔几秒就会发射出来一个烟花



2、删除烟花释放点的指令

```yaml
/fl delete <烟花筒名字>
#举例
#/fl delete test
```

即可删除这个烟花筒



### 四、**配置文件：**

data.yml

举例：

```yml
fireworks:
  #烟花名称
  firework1:
    #发射位置 (坐标，worldname:x:y:z:yaw:pitch)
    location: world:0:0:0:0:0
    #发射高度（发射高度：烟花的发射2秒后爆炸）
    height: 2
    #发射间隔 （20 tick为1秒，此处为4秒）
    delay: 20
  # Firework name
  firework2:
      # Launch location (coordinates, worldname:x:y:z:yaw:pitch)
    location: world:0:0:0:0:0
      # Launch height (launch height: the firework explodes 2 seconds after launch)
    height: 2
      # Launch interval (20 ticks is 1 second, here is 4 seconds)
    delay: 20

```

ps：可以不用使用指令创建，直接编辑配置文件去创建烟花生成点
