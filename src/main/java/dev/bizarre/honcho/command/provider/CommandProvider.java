package dev.bizarre.honcho.command.provider;

/**
 * Implementations of this interface are responsible for transmuting single strings.
 * @param <T> The type to convert to.
 */
public interface CommandProvider<T> {

    T provide(String argument);

}
