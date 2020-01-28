package dev.bizarre.honcho.command;

import lombok.Getter;

import java.lang.reflect.Method;

public class Command {

    @Getter private Object instance;
    @Getter private CommandMeta meta;
    @Getter private Method[] methods;

    public Command(Object instance, CommandMeta meta, Method[] methods) {
        this.instance = instance;
        this.meta = meta;
        this.methods = methods;
    }

}
