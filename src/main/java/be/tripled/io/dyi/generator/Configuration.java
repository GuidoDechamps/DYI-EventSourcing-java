package be.tripled.io.dyi.generator;

class Configuration {
    final String eventsOutputPath;
    final String eventsPackage;
    final String commandsPackage;
    final String commandsOutputPath;

    private Configuration(Builder builder) {
        eventsOutputPath = builder.eventsOutputPath;
        eventsPackage = builder.eventsPackage;
        commandsPackage = builder.commandsPackage;
        commandsOutputPath = builder.commandsOutputPath;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String eventsOutputPath;
        private String eventsPackage;
        private String commandsPackage;
        private String commandsOutputPath;

        private Builder() {
        }

        public Builder withEventsOutputPath(String val) {
            eventsOutputPath = val;
            return this;
        }

        public Builder withEventsPackage(String val) {
            eventsPackage = val;
            return this;
        }

        public Builder withCommandsPackage(String val) {
            commandsPackage = val;
            return this;
        }

        public Builder withCommandsOutputPath(String val) {
            commandsOutputPath = val;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }
}