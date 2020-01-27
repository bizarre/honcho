package dev.bizarre.honcho.command.registry.impl;

import dev.bizarre.honcho.command.Command;
import dev.bizarre.honcho.command.CommandMeta;
import dev.bizarre.honcho.command.registry.CommandRegistry;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DefaultCommandRegistry implements CommandRegistry {

    private Set<Command> commands = new HashSet<>();

    @Override
    public boolean register(Object command) {
        Class clazz = command.getClass();

        if (clazz.isAnnotationPresent(CommandMeta.class)) {
            CommandMeta meta = (CommandMeta) clazz.getAnnotation(CommandMeta.class);

            commands.add(new Command(command, meta, command.getClass().getDeclaredMethods()));

            return true;
        }

        return false;
    }

    @Override
    public Command get(String label) {
        return commands.stream()
                .filter(command -> Arrays.asList(command.getMeta().value()).contains(label.toLowerCase()))
                .findFirst().orElse(null);
    }
}
