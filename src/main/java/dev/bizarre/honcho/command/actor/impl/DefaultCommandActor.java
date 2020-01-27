package dev.bizarre.honcho.command.actor.impl;

import dev.bizarre.honcho.command.actor.CommandActor;

public class DefaultCommandActor implements CommandActor  {
    public void message(String message) {
        System.out.println(message);
    }
}
