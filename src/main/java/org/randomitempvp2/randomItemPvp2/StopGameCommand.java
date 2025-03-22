package org.randomitempvp2.randomItemPvp2;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class StopGameCommand implements CommandExecutor {
    private final JavaPlugin plugin; // 플러그인 인스턴스 저장

    // 생성자에서 플러그인 인스턴스를 전달받음
    public StopGameCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (GameHandler.TASK != null) {
            Bukkit.getServer().broadcastMessage("게임 종료");
            GameHandler.TASK.cancel();
        }

        return true;
    }
}
