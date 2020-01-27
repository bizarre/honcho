package dev.bizarre.honcho.command.example;

import dev.bizarre.honcho.command.CommandMeta;
import dev.bizarre.honcho.command.actor.CommandActor;

@CommandMeta({"chat", "example"})
public class ExampleCommand {

    public void execute(CommandActor actor, String message) {
        actor.message(message);
    }

}
