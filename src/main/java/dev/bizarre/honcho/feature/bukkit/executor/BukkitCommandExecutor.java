package dev.bizarre.honcho.feature.bukkit.executor;

import dev.bizarre.honcho.command.Command;
import dev.bizarre.honcho.command.actor.CommandActor;
import dev.bizarre.honcho.command.executor.impl.DefaultCommandExecutor;
import dev.bizarre.honcho.feature.bukkit.actor.ConsoleCommandActor;
import dev.bizarre.honcho.feature.bukkit.actor.PlayerCommandActor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class BukkitCommandExecutor extends DefaultCommandExecutor {

    public class BukkitCommandWrapper extends BukkitCommand {

        private Map<String, Command> commands = new HashMap<>();

        public BukkitCommandWrapper(String name) {
            super(name);
        }

        public void add(Command command) {
            for (String label : command.getMeta().value()) {
                if (label.startsWith(this.getLabel())) {
                    commands.put(label, command);
                }
            }
        }

        @Override
        public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            CommandActor actor;
            if (sender instanceof Player) {
                actor = new PlayerCommandActor(((Player) sender).getPlayer());
            } else {
                actor = new ConsoleCommandActor(sender);
            }

            Command command;
            String current = "";
            for (int i = args.length; i >= 0; i--) {
                command = commands.get(commandLabel + current);
                if (command != null) {
                    return BukkitCommandExecutor.this.execute(actor, command, StringUtils.join(args, " "));
                }

                current = " " + StringUtils.join(args, " ", 0, i).toLowerCase();
            }

            sender.sendMessage("command not found");
            return false;
        }
    }

}
