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
