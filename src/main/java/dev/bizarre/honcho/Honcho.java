package dev.bizarre.honcho;

import dev.bizarre.honcho.command.actor.CommandActor;
import dev.bizarre.honcho.command.executor.CommandExecutor;
import dev.bizarre.honcho.command.executor.impl.DefaultCommandExecutor;
import dev.bizarre.honcho.command.provider.CommandProvider;
import dev.bizarre.honcho.command.provider.impl.StringCommandProvider;
import dev.bizarre.honcho.command.registry.CommandRegistry;
import dev.bizarre.honcho.command.registry.impl.DefaultCommandRegistry;
import dev.bizarre.honcho.feature.Feature;

import java.util.HashMap;
import java.util.Map;

public class Honcho {

    private CommandRegistry registry;
    private CommandExecutor executor;

    private Honcho(Builder builder) {
        this.registry = builder.registry;
        this.executor = builder.executor;

        builder.providers.forEach(this::register);
    }

    public void register(Object command) {
        registry.register(command);
    }

    public void register(Class type, CommandProvider provider) {
        executor.addProvider(type, provider);
    }

    public void execute(CommandActor actor, String command, String arguments) {
        executor.execute(actor, registry.get(command), arguments);
    }

    public static class Builder {
        private CommandRegistry registry = new DefaultCommandRegistry();
        private CommandExecutor executor = new DefaultCommandExecutor();
        private Map<Class, CommandProvider> providers = new HashMap<>();

        public Builder() {
            this.providers.put(String.class, new StringCommandProvider());
        }

        @Deprecated public Builder withRegistry(CommandRegistry registry) {
            this.registry = registry;
            return this;
        }

        @Deprecated public Builder withExecutor(CommandExecutor executor) {
            this.executor = executor;
            return this;
        }

        public Builder withFeature(Feature feature) {
            this.registry = feature.getRegistry();
            this.executor = feature.getExecutor();
            this.providers.putAll(feature.getDefaultProviders());

            return this;
        }

        public Honcho build() {
            return new Honcho(this);
        }

    }

}
