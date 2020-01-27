package dev.bizarre.honcho.command.executor;

import dev.bizarre.honcho.command.actor.CommandActor;
import dev.bizarre.honcho.command.provider.CommandProvider;

/**
 * Implementations of this interface are responsible for handling command execution.
 */
public interface CommandExecutor {

    void addProvider(Class type, CommandProvider provider);

}
