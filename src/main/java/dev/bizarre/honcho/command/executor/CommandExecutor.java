package dev.bizarre.honcho.command.executor;

import dev.bizarre.honcho.command.Command;
import dev.bizarre.honcho.command.actor.CommandActor;
import dev.bizarre.honcho.command.provider.CommandProvider;

/**
 * Implementations of this interface are responsible for handling command execution.
 */
public interface CommandExecutor {

    void addProvider(Class type, CommandProvider provider);

    /**
     * This function is responsible for transmuting command arguments
     * into objects via {@link dev.bizarre.honcho.command.provider.CommandProvider}s and
     * mapping and then calling (via reflection) the method that best fits the arguments provided.
     */
    void execute(CommandActor actor, Command command, String arguments);

}
