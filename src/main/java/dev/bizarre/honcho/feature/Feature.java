package dev.bizarre.honcho.feature;

import dev.bizarre.honcho.command.executor.CommandExecutor;
import dev.bizarre.honcho.command.provider.CommandProvider;
import dev.bizarre.honcho.command.registry.CommandRegistry;

import java.util.Set;

/**
 * This interface
 */
public interface Feature {

    CommandExecutor getExecutor();
    CommandRegistry getRegistry();
    Set<CommandProvider> getDefaultProviders();

}
