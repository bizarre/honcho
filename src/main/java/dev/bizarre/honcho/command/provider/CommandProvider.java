package dev.bizarre.honcho.command.provider;

public interface CommandProvider<T> {

    T provide(String argument);

}
