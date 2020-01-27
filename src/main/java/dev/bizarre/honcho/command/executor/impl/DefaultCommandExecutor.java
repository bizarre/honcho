package dev.bizarre.honcho.command.executor.impl;

import dev.bizarre.honcho.command.Command;
import dev.bizarre.honcho.command.actor.CommandActor;
import dev.bizarre.honcho.command.executor.CommandExecutor;
import dev.bizarre.honcho.command.provider.CommandProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class DefaultCommandExecutor implements CommandExecutor {

    private Map<Class, CommandProvider> providers = new HashMap<>();

    public void addProvider(Class type, CommandProvider provider) {
        providers.put(type, provider);
    }

    @Override
    public void execute(CommandActor actor, Command command, String arguments) {
        String[] args = arguments.split(" ");

        Method primary = null;
        for (Method method : command.getMethods()) {
            if (primary == null) { primary = method; continue; }

            if (method.getParameterCount() == args.length) {
                primary = method;
            }
        }

        if (primary != null) {
            Parameter[] types = primary.getParameters();
            Object[] parameters = new Object[primary.getParameterCount()];

            for (int i = 0; i < args.length; i++) {
                parameters[i] = providers.get(types[i].getType()).provide(args[i]);
            }

            try {
                primary.invoke(command.getInstance(), parameters);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }

}
