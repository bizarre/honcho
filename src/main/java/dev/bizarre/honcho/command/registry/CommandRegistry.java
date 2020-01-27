package dev.bizarre.honcho.command.registry;

import dev.bizarre.honcho.command.Command;

public interface CommandRegistry {

    boolean register(Object command);
    Command get(String label);

}
