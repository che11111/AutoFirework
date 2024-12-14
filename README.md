# Fireworklauncher

# AutoFirework

<img src="AutoFirework.png" width="310" height="300" alt="image">

## Plugin Functions

It's a firework launcher plugin. It can achieve the effect of continuously launching fireworks at a certain position. It's used for festivals and celebrations as well as for decoration purposes. The launched fireworks are random ones.

## Plugin Information

**Tested Version**: 1.20.1

**Server Software**: Spigot, Paper, etc.

**Command Shortcut**: /af

## Plugin Commands

Enter the following in the game (only players with operator (op) permissions can use these commands):

1. Command to create a firework launching point:

```yaml
/af create <firework launcher name> <number of ticks for the launch interval>
# Example
# /af create test 100
```

This will generate a "launcher" (virtual) right under your feet. Then a firework will be launched every few seconds.

2. Command to delete a firework launching point:

```yaml
/af delete <firework launcher name>
# Example
# /af delete test
```

This will delete this firework launcher.

## Configuration File

data.yml

Example:

```yml
fireworks:
  # Firework name
  firework1:
    # Launch location (coordinates, worldname:x:y:z:yaw:pitch)
    location: world:0:0:0:0:0
    # Launch height (launch height: the firework explodes 2 seconds after launch)
    height: 2
    # Launch interval (20 ticks is 1 second, here is 4 seconds)
    delay: 20
  firework2:
    location: world:0:0:0:0:0
    height: 2
    delay: 20

```

PS: You don't have to use commands to create them. You can directly edit the configuration file to create firework generation points. 
