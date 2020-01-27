package dev.bizarre.honcho;

import dev.bizarre.honcho.command.CommandMeta;
import org.junit.Test;

import java.util.logging.Logger;

public class HonchoTests {

    private static Logger log = Logger.getLogger("honcho");

    @CommandMeta
    private class ExampleCommand {

    }

    @Test public void testCommand() {
        Honcho honcho = new Honcho.Builder();
    }

}
