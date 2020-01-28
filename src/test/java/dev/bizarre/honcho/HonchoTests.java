package dev.bizarre.honcho;

import dev.bizarre.honcho.command.CommandMeta;
import dev.bizarre.honcho.command.actor.impl.DefaultCommandActor;
import dev.bizarre.honcho.command.example.ExampleCommand;
import dev.bizarre.honcho.command.executor.impl.DefaultCommandExecutor;
import dev.bizarre.honcho.command.provider.impl.StringCommandProvider;
import org.junit.Test;

import java.util.logging.Logger;

public class HonchoTests {

    private static Logger log = Logger.getLogger("honcho");
    private TestObject testObject = new TestObject();

    private class TestObject {
        boolean bool = false;
        int number = 0;
        String status = "";
    }

    @CommandMeta("test")
    public class TestCommand {
        public void execute(String status) {
            testObject.bool = true;
            testObject.number = 1337;
            testObject.status = status;
        }
    }

    @Test public void testCommand() {
        Honcho honcho = new Honcho.Builder().build();

        honcho.register(new TestCommand());
        honcho.register(String.class, new StringCommandProvider());

        String status = "success";

        honcho.execute(new DefaultCommandActor(), "test", status);

        assert testObject.status.equals(status) : "TestCommand#status does not equal " + status;
        assert testObject.bool : "TestCommand#bool not true";
        assert testObject.number == 1337 : "TestCommand#number !== 1337";
    }

}
