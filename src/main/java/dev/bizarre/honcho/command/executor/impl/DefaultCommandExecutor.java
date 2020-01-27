package dev.bizarre.honcho.command.executor.impl;

import dev.bizarre.honcho.command.Command;
import dev.bizarre.honcho.command.actor.CommandActor;
import dev.bizarre.honcho.command.executor.CommandExecutor;
import dev.bizarre.honcho.command.provider.CommandProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        // iterate over methods and find closest one to arguments provided
        for (Method method : command.getMethods()) {
            if (primary == null) { primary = method; continue; }

            if (method.getParameterCount() == args.length) {
                primary = method;
            }
        }

        if (primary != null) {
            Parameter[] types = primary.getParameters();
            List<Object> parameters = new ArrayList<>();

            Object senderObj = actor.to();
            Class sender = types[0].getType();

            // actor representation is equal to first parameter of chosen method
            if (senderObj != null && senderObj.getClass().equals(sender)) {
                // add actor representation to parameters
                parameters.add(senderObj);
            }

            int index = parameters.size();
            for (String argument : args) {
                // add provider-transmuted obj to parameters
                parameters.add(providers.get(types[index].getType()).provide(argument));
            }

            try {
                primary.invoke(command.getInstance(), parameters.toArray());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }

}
