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

public class DefaultCommandExecutor implements CommandExecutor {

    private Map<Class, CommandProvider> providers = new HashMap<>();

    public void addProvider(Class type, CommandProvider provider) {
        providers.put(type, provider);
    }

    @Override
    public boolean execute(CommandActor actor, Command command, String arguments) {
        String[] args = arguments.split(" ");
        Method primary = null;

        // iterate over methods and find closest one to arguments provided
        // todo: fetch method better
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

            // if actor representation is equal to (or subtype of) first parameter of chosen method
            if (senderObj != null && sender.isAssignableFrom(senderObj.getClass())) {
                // add actor representation to parameters
                parameters.add(senderObj);
            }

            int index = parameters.size();
            for (String argument : args) {

                if (index >= types.length) {

                    if (types[types.length-1].getType() == String.class) {
                        StringBuilder previous = new StringBuilder((String) parameters.get(parameters.size() - 1));
                        for (int i = index-1; i < args.length; i++) {
                            previous.append(" ").append(args[i]);
                        }

                        parameters.set(parameters.size()-1, previous.toString());
                    }

                    break;
                }

                // add provider-transmuted obj to parameters
                parameters.add(providers.get(types[index].getType()).provide(argument));
                index++;
            }

            try {
                primary.invoke(command.getInstance(), parameters.toArray());
                return true;
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        // command failed to map
        return false;
    }

}
