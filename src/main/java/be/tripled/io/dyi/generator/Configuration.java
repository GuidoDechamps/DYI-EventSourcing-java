package be.tripled.io.dyi.generator;

class Configuration {
    final String eventsOutputPath;
    final String specificationOutputPath;
    final String specificationPackage;
    final String eventsPackage;
    final String commandsPackage;
    final String commandsOutputPath;
    final String eventsMarkerInterface;
    final String commandsMarkerInterface;

    private Configuration(Builder builder) {
        eventsOutputPath = builder.eventsOutputPath;
        specificationOutputPath = builder.specificationOutputPath;
        specificationPackage = builder.specificationPackage;
        eventsPackage = builder.eventsPackage;
        commandsPackage = builder.commandsPackage;
        commandsOutputPath = builder.commandsOutputPath;
        eventsMarkerInterface = builder.eventsMarkerInterface;
        commandsMarkerInterface = builder.commandsMarkerInterface;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String eventsOutputPath;
        private String specificationOutputPath;
        private String specificationPackage;
        private String eventsPackage;
        private String commandsPackage;
        private String commandsOutputPath;
        private String eventsMarkerInterface;
        private String commandsMarkerInterface;

        private Builder() {
        }

        public Builder withEventsOutputPath(String val) {
            eventsOutputPath = val;
            return this;
        }

        public Builder withSpecificationOutputPath(String val) {
            specificationOutputPath = val;
            return this;
        }

        public Builder withSpecificationPackage(String val) {
            specificationPackage = val;
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

        public Builder withEventsMarkerInterface(String val) {
            eventsMarkerInterface = val;
            return this;
        }

        public Builder withCommandsMarkerInterface(String val) {
            commandsMarkerInterface = val;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }
}