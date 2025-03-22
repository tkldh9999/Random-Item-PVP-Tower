package org.randomitempvp2.randomItemPvp2;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class RandomItemPvp2 extends JavaPlugin implements Listener {
    final World world = Bukkit.getServer().getWorld("world");
    @Override
    public void onEnable() {
        final WorldBorder border = world.getWorldBorder();
        final Location center = new Location(world, 0, 0, 0);
        border.setCenter(center);
        border.setSize(50);

        this.getCommand("start_game").setExecutor(new StartGameCommand(this));
        this.getCommand("stop_game").setExecutor(new StopGameCommand(this));
        this.getCommand("arena").setExecutor(new MakeArenaCommand(this));
        this.getCommand("resetarena").setExecutor(new ResetArenaCommand());

        getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("플러그인 실핻됨");

    }

    @Override
    public void onDisable() {
        getLogger().info("플러그인 종료됨");
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        final Player player = event.getEntity();
        player.setGameMode(GameMode.SPECTATOR);
    }

    @EventHandler
    public void playerRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(new Location(getServer().getWorld("world"), 0, 0, 0));
    }
}
