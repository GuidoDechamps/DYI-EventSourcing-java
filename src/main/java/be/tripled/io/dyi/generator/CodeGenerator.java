package be.tripled.io.dyi.generator;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class CodeGenerator {
    private final Configuration configuration;
    private String currentGenerationPackage = "unknown";
    private String currentMarkerInterface = "unknown";
    private String outputFilePath;
    private DTOGenerator dtoGenerator = DTOGenerator.create();

    CodeGenerator(Configuration configuration) {
        this.configuration = configuration;
        this.outputFilePath = configuration.eventsOutputPath;
    }

    void generateCode(File file) throws IOException {
        final YAMLFactory factory = new YAMLFactory();
        final YAMLParser parser = factory.createParser(file);
        while (processNextToken(parser)) ;
    }

    private static boolean quit() {
        System.out.println("---Quit Code Generation-----");
        return false;
    }

    private boolean processNextToken(YAMLParser parser) throws IOException {
        final JsonToken jsonToken = parser.nextValue();
        if (jsonToken == null)
            return quit();
        else {
            processToken(parser, jsonToken);
            return true;
        }
    }

    private void processToken(YAMLParser parser, JsonToken jsonToken) {

        switch (jsonToken) {
            case START_OBJECT:
                processStartToken(parser);
                break;
            case END_OBJECT:
                processEndToken(parser);
                break;
            case VALUE_STRING:
                processValue(parser);
                break;
            default:
                System.out.println("Ignoring " + jsonToken);
        }
    }

    private void processValue(YAMLParser parser) {
        dtoGenerator.withFieldName(getCurrentName(parser));
    }

    private void processEndToken(YAMLParser parser) {
        if (isEvents(parser)) {
        } else if (isCommands(parser)) {

        } else if (isEndFile(parser)) {
            handleEndFile();
        } else {
            handleEndDTO(parser);
        }
    }

    private void handleEndFile() {
        generateSpecificationInterface();
    }

    private void generateSpecificationInterface() {
        this.outputFilePath = configuration.specificationOutputPath;
        writeTextToFile(
                "package " + configuration.specificationPackage + ";" +
                        "import " + configuration.commandsPackage + "." + configuration.commandsMarkerInterface + ";" +
                        "import " + configuration.eventsPackage + "." + configuration.eventsMarkerInterface + ";" +
                        "import java.util.Collection;" +
                        "public interface Specification {" +
                        "    Specification given(Event... events);" +
                        "    Specification given(Collection<Event> events);" +
                        "    Specification when(Command command);" +
                        "    void then(Event... events);" +
                        "    void then(Collection<Event> events);" +
                        "}", "Specification");
    }

    private void handleEndDTO(YAMLParser parser) {
        final String classText = dtoGenerator.generate();
        final String clazzName = getCurrentName(parser);
        writeTextToFile(classText, clazzName);
        dtoGenerator = null;
    }

    private void writeTextToFile(String classText, String clazzName) {
        final String fileName = outputFilePath + clazzName + ".java";
        final FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.write(classText);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Unable to write file " + clazzName);
        }
    }

    private void processStartToken(YAMLParser parser) {
        if (isEvents(parser)) {
            handleStartEventSection();
        } else if (isCommands(parser)) {
            handleStartCommandSection();
        } else {
            handleStartDTO(parser);
        }
    }

    private void handleStartDTO(YAMLParser parser) {
        dtoGenerator = DTOGenerator.create();
        dtoGenerator.withName(getCurrentName(parser));
        dtoGenerator.withPackage(currentGenerationPackage);
        dtoGenerator.withMarkerInterface(currentMarkerInterface);
    }

    private void handleStartCommandSection() {
        currentGenerationPackage = configuration.commandsPackage;
        currentMarkerInterface = configuration.commandsMarkerInterface;
        outputFilePath = configuration.commandsOutputPath;
        generateCommandMarkerInterface();
    }

    private void handleStartEventSection() {
        currentGenerationPackage = configuration.eventsPackage;
        currentMarkerInterface = configuration.eventsMarkerInterface;
        outputFilePath = configuration.eventsOutputPath;
        generateEventMarkerInterface();
    }

    private void generateEventMarkerInterface() {
        writeTextToFile("package " + configuration.eventsPackage + ";" + "public interface " +
                configuration.eventsMarkerInterface +
                " {}", configuration.eventsMarkerInterface);
    }

    private void generateCommandMarkerInterface() {
        writeTextToFile("package " + configuration.commandsPackage + ";" + "public interface " +
                configuration.commandsMarkerInterface +
                " {}", configuration.commandsMarkerInterface);
    }

    private boolean isCommands(YAMLParser parser) {
        return isCurrentName(parser, "commands");
    }

    private boolean isEndFile(YAMLParser parser) {
        return getCurrentName(parser) == null;
    }

    private boolean isEvents(YAMLParser parser) {
        return isCurrentName(parser, "events");
    }

    private boolean isCurrentName(YAMLParser parser, String events) {
        return events
                .equalsIgnoreCase(getCurrentName(parser));
    }

    private String getCurrentName(YAMLParser parser) {
        try {
            return parser.getCurrentName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
