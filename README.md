# Homes Plugin v1.0

## Overview
A lightweight, high-performance homes management plugin for Spigot/Bukkit servers. Players can set multiple homes and teleport to them with simple commands.

## Features
- ✓ Set multiple named homes
- ✓ Teleport to saved homes
- ✓ Delete homes
- ✓ List all homes with coordinates
- ✓ Optimized performance
- ✓ Permission-based access control
- ✓ YAML-based storage
- ✓ No external dependencies

## Installation
1. Build the plugin using Maven: `mvn clean package`
2. Copy the generated JAR file from `target/` to your server's `plugins/` directory
3. Restart your server
4. Done! The plugin is ready to use

## Commands

| Command | Alias | Permission | Description |
|---------|-------|-----------|-------------|
| `/home [name]` | `/h` | `homes.home` | Teleport to a home |
| `/sethome [name]` | `/sh` | `homes.sethome` | Set current location as home |
| `/delhome [name]` | `/dh` | `homes.delhome` | Delete a home |
| `/listhomes` | `/lh` | `homes.listhomes` | List all your homes |

## Permissions

| Permission | Default | Description |
|-----------|---------|-------------|
| `homes.use` | true | Allow player to use home commands |
| `homes.admin` | op | Admin access to homes plugin |
| `homes.home` | true | Teleport to home |
| `homes.sethome` | true | Set home |
| `homes.delhome` | true | Delete home |
| `homes.listhomes` | true | List homes |
| `homes.reload` | op | Reload plugin |

## Project Structure
```
Homes/
├── src/
│   └── main/
│       ├── java/exe/gurbaksh/homes/
│       │   ├── HomesPlugin.java (Main plugin class)
│       │   ├── commands/ (Command handlers)
│       │   ├── storage/ (Data persistence)
│       │   ├── config/ (Configuration management)
│       │   └── listeners/ (Event listeners)
│       └── resources/
│           ├── plugin.yml (Plugin configuration)
│           └── config.yml (User configuration)
├── pom.xml (Maven build configuration)
└── README.md (This file)
```

## Performance Optimizations
- Lazy loading of homes
- Efficient HashMap-based caching
- Minimal file I/O operations
- Clean separation of concerns
- No blocking operations on main thread

## Building
```bash
mvn clean package
```

## Author
Gurbaksh

## Version
1.0
