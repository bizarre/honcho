package dev.bizarre.honcho.command.example;

import dev.bizarre.honcho.command.CommandMeta;
import dev.bizarre.honcho.command.actor.CommandActor;

import java.util.logging.Logger;

@CommandMeta({"chat", "example"})
public class ExampleCommand {

    public void execute(String message) {
        Logger.getLogger("test").info(message);
    }

}
