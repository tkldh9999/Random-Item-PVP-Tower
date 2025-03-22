package org.randomitempvp2.randomItemPvp2;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ResetArenaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Location min = new Location(Bukkit.getWorld("world"),-25,-64,-25);
        Location max = new Location(Bukkit.getWorld("world"),25,319,25);

        for (int x = min.getBlockX(); x <= max.getBlockX(); x++) {
            for (int y = min.getBlockY(); y <= max.getBlockY(); y++) {
                for (int z = min.getBlockZ(); z <= max.getBlockZ(); z++) {
                    Location point = new Location(Bukkit.getWorld("world"),x,y,z);
                    Block block = point.getBlock();
                    block.setType(Material.AIR);
                }
            }
        }
        return true;
    }
}
