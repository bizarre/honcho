package dev.bizarre.honcho.command.executor.impl;

import dev.bizarre.honcho.command.actor.CommandActor;
import dev.bizarre.honcho.command.executor.CommandExecutor;
import dev.bizarre.honcho.command.provider.CommandProvider;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DefaultCommandExecutor implements CommandExecutor {

    private Map<Class, CommandProvider> providers = new HashMap<>();

    public void addProvider(Class type, CommandProvider provider) {
        providers.put(type, provider);
    }

}
