package org.randomitempvp2.randomItemPvp2;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MakeArenaCommand implements CommandExecutor {
    private final JavaPlugin plugin; // 플러그인 인스턴스 저장

    // 생성자에서 플러그인 인스턴스를 전달받음
    public MakeArenaCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length != 4) {
            commandSender.sendMessage("좁은 베이스 여러개를 만들어주는 명령어 입니다.");
            commandSender.sendMessage("사용법: /arena <베이스 갯수> <높이> <중심과 베이스 사이 거리> <블록 종류>");

            return false;
        }
        int sides;
        int height;
        int radius;

        try {
            sides = Integer.parseInt(strings[0]);
            height = Integer.parseInt(strings[1]);
            radius = Integer.parseInt(strings[2]);
        } catch (NumberFormatException e) {
            commandSender.sendMessage("숫자를 필요로 하는 부분에 다른 문자가 입력되었습니다.");
            return false;
        }


        //commandSender.sendMessage(strings[0] + " " + strings[1] + " " + strings[2]);

        Material block = Material.matchMaterial(strings[3]);
        if (block == null || !block.isBlock()) {
            commandSender.sendMessage("재대로된 블록 종류를 입력하세요.");
            return false;
        }
        if (radius > 20) {
            commandSender.sendMessage("베이스와 중심 사이 거리는 20을 초과 할 수 없습니다.");
            return false;
        }

        if (sides > 8) {
            commandSender.sendMessage(" 베이스 갯수는 최대 8개 까지만 배치 가능합니다.");
            return false;
        }


        Location center = new Location(Bukkit.getWorld("world"), 0, 0, 0); // 중심 (0, 0, 0)

        for (int i = 0; i < sides; i++) {
            double angle = 360.0 * i / sides;
            double radian = Math.toRadians(angle);
            int x = (int) Math.round(center.getX() + radius * Math.cos(radian));
            int z = (int) Math.round(center.getZ() + radius * Math.sin(radian));
            for (int j = 0; j < height; j++) {
                Location point = new Location(center.getWorld(), x, j, z);
                point.getBlock().setType(block);
            }
            Location spawnpoint = new Location(center.getWorld(), x, height+4, z);
            List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
            if (onlinePlayers.size() > i) {
                onlinePlayers.get(i).teleport(spawnpoint);
            }
        }
        return true;
    }
}
