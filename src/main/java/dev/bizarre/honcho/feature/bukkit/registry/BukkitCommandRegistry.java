package dev.bizarre.honcho.feature.bukkit.registry;

import dev.bizarre.honcho.command.Command;
import dev.bizarre.honcho.command.registry.impl.DefaultCommandRegistry;
import dev.bizarre.honcho.feature.bukkit.executor.BukkitCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BukkitCommandRegistry extends DefaultCommandRegistry {

    private CommandMap commandMap;
    private JavaPlugin plugin;
    private BukkitCommandExecutor executor;
    private Map<String, BukkitCommandExecutor.BukkitCommandWrapper> wrappers = new HashMap<>();

    public BukkitCommandRegistry(JavaPlugin plugin, BukkitCommandExecutor executor) throws NoSuchFieldException, IllegalAccessException {
        this.commandMap = fetchCommandMap();
        this.plugin = plugin;
        this.executor = executor;
    }

    private CommandMap fetchCommandMap() throws NoSuchFieldException, IllegalAccessException {
        Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        field.setAccessible(true);

        return (CommandMap) field.get(Bukkit.getServer());
    }

    @Override
    public boolean register(Object obj) {
        if (super.register(obj)) {
            Command command = super.commands.stream().filter(cmd -> cmd.getInstance() == obj).findFirst().orElse(null);
            if (command != null) {

                for (String label : command.getMeta().value()) {
                    String root = label.split(" ")[0].toLowerCase();
                    BukkitCommandExecutor.BukkitCommandWrapper wrapper = wrappers.get(root);

                    if (wrapper == null) {
                        wrapper = executor.new BukkitCommandWrapper(root);

                        commandMap.register(plugin.getName(), wrapper);

                        wrappers.put(root, wrapper);
                    }

                    wrapper.add(command);
                }

                return true;
            }

            return false;
        }



        return false;
    }
}
