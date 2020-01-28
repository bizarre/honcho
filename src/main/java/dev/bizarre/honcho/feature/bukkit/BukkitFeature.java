package dev.bizarre.honcho.feature.bukkit;

import dev.bizarre.honcho.command.executor.CommandExecutor;
import dev.bizarre.honcho.command.provider.CommandProvider;
import dev.bizarre.honcho.command.registry.CommandRegistry;
import dev.bizarre.honcho.feature.Feature;
import dev.bizarre.honcho.feature.bukkit.executor.BukkitCommandExecutor;
import dev.bizarre.honcho.feature.bukkit.provider.PlayerCommandProvider;
import dev.bizarre.honcho.feature.bukkit.provider.WorldCommandProvider;
import dev.bizarre.honcho.feature.bukkit.registry.BukkitCommandRegistry;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BukkitFeature implements Feature {

    private JavaPlugin plugin;

    private BukkitCommandExecutor executor;
    private BukkitCommandRegistry registry;
    private Map<Class, CommandProvider> providers = new HashMap<>();

    public BukkitFeature(JavaPlugin plugin) {
        this.plugin = plugin;
        this.executor = new BukkitCommandExecutor();

        try {
            this.registry = new BukkitCommandRegistry(plugin, executor);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        this.providers.put(Player.class, new PlayerCommandProvider());
        this.providers.put(World.class, new WorldCommandProvider());
    }

    public CommandExecutor getExecutor() {
        return executor;
    }

    public CommandRegistry getRegistry() {
        return registry;
    }

    public Map<Class, CommandProvider> getDefaultProviders() {
        return providers;
    }
}
