package dev.bizarre.honcho.feature.bukkit.provider;

import dev.bizarre.honcho.command.provider.CommandProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerCommandProvider implements CommandProvider<Player> {
    @Override
    public Player provide(String argument) {
        return Bukkit.getPlayer(argument);
    }
}
