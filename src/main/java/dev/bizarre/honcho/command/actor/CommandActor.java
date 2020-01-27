package dev.bizarre.honcho.command.actor;

public interface CommandActor<T> {

    void message(String message);
    boolean hasPermission(String permission);

}
