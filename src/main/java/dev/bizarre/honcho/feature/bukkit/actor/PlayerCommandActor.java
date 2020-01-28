package dev.bizarre.honcho.feature.bukkit.actor;

import dev.bizarre.honcho.command.actor.CommandActor;
import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class PlayerCommandActor implements CommandActor<Player> {

    private Player player;

    @Override
    public void message(String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    @Override
    public boolean hasPermission(String permission) {
        return player.hasPermission(permission);
    }

    @Override
    public Player to() {
        return player;
    }

}
