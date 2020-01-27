package dev.bizarre.honcho;

import dev.bizarre.honcho.command.actor.impl.DefaultCommandActor;
import dev.bizarre.honcho.command.example.ExampleCommand;
import org.junit.Test;

import java.util.logging.Logger;

public class HonchoTests {

    private static Logger log = Logger.getLogger("honcho");

    @Test public void testCommand() {
        Honcho honcho = new Honcho.Builder().build();

        honcho.register(new ExampleCommand());

        honcho.execute(new DefaultCommandActor(), "chat hello!");
    }

}
