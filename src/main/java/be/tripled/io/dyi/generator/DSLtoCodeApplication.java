package be.tripled.io.dyi.generator;

import java.io.File;
import java.io.IOException;

class DSLtoCodeApplication {
    private static final String EVENTS_PACKAGE = "be.tripled.io.dyi.events";
    private static final String COMMANDS_PACKAGE = "be.tripled.io.dyi.commands";
    private static final String EVENTS_OUTPUT_FILE_PATH = ".//src//main//java//be//tripled//io/dyi//events//";
    private static final String COMMANDS_OUTPUT_FILE_PATH = ".//src//main//java//be//tripled//io/dyi//commands//";
    private static final String EVENT = "Event";
    private static final String COMMAND = "Command";

    /**
     * Run the application with the path to the YAML file.
     * For example ./src/main/resources/ACME.yml
     *
     * @param args
     * @throws IOException
     */
    public static void main(String... args) throws IOException {
        final String fileName = getFileName(args);
        final File file = new File(fileName);
        if (file.canRead()) {
            new CodeGenerator(buildConfiguration()).generateCode(file);
        } else
            System.out.println("[ERROR] Unable to read file " + fileName + " from " + file.getAbsolutePath());
    }

    private static Configuration buildConfiguration() {
        return Configuration.newBuilder()
                            .withCommandsOutputPath(COMMANDS_OUTPUT_FILE_PATH)
                            .withCommandsPackage(COMMANDS_PACKAGE)
                            .withEventsOutputPath(EVENTS_OUTPUT_FILE_PATH)
                            .withEventsPackage(EVENTS_PACKAGE)
                            .withEventsMarkerInterface(EVENT)
                            .withCommandsMarkerInterface(COMMAND)
                            .build();
    }

    private static String getFileName(String... args) {
        if (args.length == 0) {
            System.out.println("[ERROR] No YAML file name given");
            System.exit(-1);
        }
        return args[0];
    }
}
