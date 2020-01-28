package dev.bizarre.honcho.feature.bukkit.actor;

import dev.bizarre.honcho.command.actor.CommandActor;
import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class ConsoleCommandActor implements CommandActor<CommandSender> {

    private CommandSender sender;

    @Override
    public void message(String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }

    @Override
    public CommandSender to() {
        return sender;
    }

}
