honcho
=====

honcho is a simple, lightweight command framework

```java
@CommandMeta("echo")
public class EchoCommand {

  public void execute(DefaultCommandActor actor, String message) {
    actor.message(message);
  }

}
```

honcho removes the boilerplate associated with transmuting user inputted strings
```java
public class PersonCommandProvider implements CommandProvider<Person> {
    @Override
    public Person provide(String argument) {
        if (argument.length() > 16) {
          return Person.getByFullName(argument);
        } else {
          return Person.getBySurname(argument);
        }
    }
}

// now you can use it in a command

@CommandMeta("info")
public class InfoCommand {

  public void execute(DefaultCommandActor actor, Person person) {
    actor.message(person.getName());
  }

}
```

## custom actors
You can implement the `CommandActor` interface to allow using your own custom objects in command functions.

```java
@AllArgsConstructor
public class PlayerCommandActor implements CommandActor<Player> {

    private Player player;

    ...

    @Override
    public Player to() {
        return player;
    }

}

// now you can use the Player type as a command actor (assuming your CommandExecutor) supports it.

@CommandMeta("broadcast", "bc")
public class BroadcastCommand {

  public void execute(Player player, String message) {
    Bukkit.broadcastMessage(player.getDisplayName() + " says: " + message);
  }

}
```
