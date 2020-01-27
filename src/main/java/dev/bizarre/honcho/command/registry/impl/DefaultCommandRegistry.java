package dev.bizarre.honcho.command.registry.impl;

import dev.bizarre.honcho.command.CommandMeta;
import dev.bizarre.honcho.command.registry.CommandRegistry;

public class DefaultCommandRegistry implements CommandRegistry {

    @Override
    public boolean register(Object command) {
        Class clazz = command.getClass();

        if (clazz.isAnnotationPresent(CommandMeta.class)) {
            CommandMeta meta = (CommandMeta) clazz.getAnnotation(CommandMeta.class);

            

        }

        return false;
    }

}
