package dev.bizarre.honcho;

import dev.bizarre.honcho.command.executor.CommandExecutor;
import dev.bizarre.honcho.command.executor.impl.DefaultCommandExecutor;
import dev.bizarre.honcho.command.registry.CommandRegistry;
import dev.bizarre.honcho.command.registry.impl.DefaultCommandRegistry;
import dev.bizarre.honcho.feature.Feature;

public class Honcho {

    private CommandRegistry registry;
    private CommandExecutor executor;

    private Honcho(Builder builder) {
        this.registry = builder.registry;
        this.executor = builder.executor;
    }

    public void register(Class command) {

    }

    public static class Builder {
        private CommandRegistry registry = new DefaultCommandRegistry();
        private CommandExecutor executor = new DefaultCommandExecutor();

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

            return this;
        }

        public Honcho build() {
            return new Honcho(this);
        }

    }

}
