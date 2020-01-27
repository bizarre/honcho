package dev.bizarre.honcho.command.provider.impl;

import dev.bizarre.honcho.command.provider.CommandProvider;

public class StringCommandProvider implements CommandProvider<String> {

    public String provide(String argument) {
        return argument;
    }

}
