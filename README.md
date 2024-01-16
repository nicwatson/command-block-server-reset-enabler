# Command Block Server Reset Enabler (bresetenabler)

Command Block Server Reset Enabler is a simple, server-side Minecraft mod (versions 1.7.10, 1.10.2, 1.11.2, and 1.12), that adds a new command, `/breset`.

`/breset` functions much like the `/stop` command, but unlike `/stop`, it can be issued from a command block. This allows you to make an in-game button or redstone system capable of stopping your server. A configurable lockout system prevents serial abuse.

This mod is experimental software. It may make it somewhat easier for malicious users to cause trouble on your server. Use at your own risk.

Requires [Minecraft Forge](https://files.minecraftforge.net/net/minecraftforge/forge/).

## Use Case
You might like this mod if...
- You use a server script (not included in this mod!) that is configured to automatically restart your server process after it has stopped.
- Your server performance benefits from occasional resets, e.g. to clear memory leaks.
- You run a private or semi-private server on which the players are relatively trustworthy.
- You would like non-op players to be able to reset the server.

## Why it's necessary
For quite sensible security reasons, vanilla Minecraft does not allow the `/stop` command to be issued from a command block. However, on a private server with trusthworthy players, the benefits of allowing command-block resets may outweigh the potential risks. This mod adds a new command, /breset, which simply invokes `/stop`, but which can itself be executed by a command block.

The `/breset` command has permission level 2, which allows it to be used in command blocks but restricts manual invocation to op-only.

Note: you may have to place command blocks invoking `/breset` outside of the spawn protection area for non-ops to use them.

## Configuration options
In order to prevent players from serially abusing the reset button, this mod locks out the `/breset` command if the server uptime is less than a certain amount (default 60 minutes). In the config file, a custom lockout time (in seconds) may be specified.

## Binaries
Pre-compiled JARs targetting Minecraft versions 1.7.10, 1.10.2, 1.11.2, and 1.12 are available on [CurseForge](https://www.curseforge.com/minecraft/mc-mods/command-block-server-reset-enabler/).
