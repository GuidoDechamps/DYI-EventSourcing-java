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

    private void processToken(YAMLParser parser, JsonToken jsonToken) throws IOException {

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

    private void processValue(YAMLParser parser) throws IOException {
        System.out.println("Field value " + parser.getCurrentName() + " : " + parser.getText());
        dtoGenerator.withFieldName(parser.getCurrentName());
    }

    private void processEndToken(YAMLParser parser) throws IOException {
        System.out.println("Ended " + parser.getCurrentName());
        if (isEvents(parser)) {
        } else if (isCommands(parser)) {

        } else if (isEndFile(parser)) {
            handleEndFile();
        } else {
            handleEndDTO(parser);
        }
    }

    private void handleEndFile() {
        System.out.println("--EndFile--");
    }

    private void handleEndDTO(YAMLParser parser) throws IOException {
        final String classText = dtoGenerator.generate();
        final String clazzName = parser.getCurrentName();
        writeTextToFile(classText, clazzName);
        dtoGenerator = null;
    }

    private void writeTextToFile(String classText, String clazzName) throws IOException {
        final String fileName = outputFilePath + clazzName + ".java";
        final FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(classText);
        fileWriter.close();
    }

    private void processStartToken(YAMLParser parser) throws IOException {
        if (isEvents(parser)) {
            handleStartEventSection();
        } else if (isCommands(parser)) {
            handleStartCommandSection();
        } else {
            handleStartDTO(parser);
        }
    }

    private void handleStartDTO(YAMLParser parser) throws IOException {
        dtoGenerator = DTOGenerator.create();
        dtoGenerator.withName(parser.getCurrentName());
        dtoGenerator.withPackage(currentGenerationPackage);
        dtoGenerator.withMarkerInterface(currentMarkerInterface);
    }

    private void handleStartCommandSection() throws IOException {
        currentGenerationPackage = configuration.commandsPackage;
        currentMarkerInterface = configuration.commandsMarkerInterface;
        outputFilePath = configuration.commandsOutputPath;
        generateCommandMarkerInterface();
    }

    private void handleStartEventSection() throws IOException {
        currentGenerationPackage = configuration.eventsPackage;
        currentMarkerInterface = configuration.eventsMarkerInterface;
        outputFilePath = configuration.eventsOutputPath;
        generateEventMarkerInterface();
    }

    private void generateEventMarkerInterface() throws IOException {
        writeTextToFile("package " + configuration.eventsPackage + ";" + "public interface " +
                configuration.eventsMarkerInterface +
                " {}", configuration.eventsMarkerInterface);
    }

    private void generateCommandMarkerInterface() throws IOException {
        writeTextToFile("package " + configuration.commandsPackage + ";" + "public interface " +
                configuration.commandsMarkerInterface +
                " {}", configuration.commandsMarkerInterface);
    }

    private boolean isCommands(YAMLParser parser) throws IOException {
        return isCurrentName(parser, "commands");
    }

    private boolean isEndFile(YAMLParser parser) throws IOException {
        return parser.getCurrentName() == null;
    }

    private boolean isEvents(YAMLParser parser) throws IOException {
        return isCurrentName(parser, "Events");
    }

    private boolean isCurrentName(YAMLParser parser, String events) throws IOException {
        return events
                .equalsIgnoreCase(parser.getCurrentName());
    }


}
