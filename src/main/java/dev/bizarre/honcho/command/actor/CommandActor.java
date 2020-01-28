package dev.bizarre.honcho.command.actor;

/**
 * Serves as an abstraction layer to allow modular command senders via type conversion.
 * @param <T> The type to target and convert.
 */
public interface CommandActor<T> {

    void message(String message);
    boolean hasPermission(String permission);
    T to();

}
