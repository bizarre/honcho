package dev.bizarre.honcho.command;

import lombok.Getter;

import java.lang.reflect.Method;
import java.util.List;

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
