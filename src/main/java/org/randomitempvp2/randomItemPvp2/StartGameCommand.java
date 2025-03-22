package org.randomitempvp2.randomItemPvp2;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class StartGameCommand implements CommandExecutor {
    private final JavaPlugin plugin; // 플러그인 인스턴스 저장

    // 생성자에서 플러그인 인스턴스를 전달받음
    public StartGameCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        Bukkit.getServer().broadcastMessage("새 게임 시작");
        GameHandler.TASK = Bukkit.getScheduler().runTaskTimer(plugin,() -> {
            Bukkit.getServer().broadcastMessage("아이템 지급!");
            for (Player player : Bukkit.getOnlinePlayers()) {
                for (int i = 0; i < 3; i++) {
                    Random random = new Random();
                    Material random_type = Material.values()[random.nextInt(Material.values().length)];
                    ItemStack new_item = new ItemStack(random_type,1);
                    player.getInventory().addItem(new_item);
                }
            }
        },0L,200L);

        return true;
    }
}
