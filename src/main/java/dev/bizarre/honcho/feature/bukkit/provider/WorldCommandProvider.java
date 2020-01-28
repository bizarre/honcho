package dev.bizarre.honcho.feature.bukkit.provider;

import dev.bizarre.honcho.command.provider.CommandProvider;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class WorldCommandProvider implements CommandProvider<World> {
    @Override
    public World provide(String argument) {
        return Bukkit.getWorld(argument);
    }
}
