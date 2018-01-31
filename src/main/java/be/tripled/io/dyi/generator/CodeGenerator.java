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
    private String outputFilePath;
    private ClassGenerator classGenerator = ClassGenerator.classGenerator();

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
        System.out.println("--------");
        System.out.println("The end");
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
        classGenerator.withFieldName(parser.getCurrentName());
    }

    private void processEndToken(YAMLParser parser) throws IOException {
        System.out.println("Ended " + parser.getCurrentName());
        if (isEvents(parser)) {
        } else if (isCommands(parser)) {

        } else if (isEndFile(parser)) {
            System.out.println("--EndFile--");
        } else {
            final String classText = classGenerator.generate();
            final String clazzName = parser.getCurrentName();
            writeTextToFile(classText, clazzName);
            classGenerator = null;
        }
    }

    private void writeTextToFile(String classText, String clazzName) throws IOException {
        final String fileName = outputFilePath + clazzName + ".java";
        final FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(classText);
        fileWriter.close();
    }

    private void processStartToken(YAMLParser parser) throws IOException {
        System.out.println("Started " + parser.getCurrentName());
        if (isEvents(parser)) {
            currentGenerationPackage = configuration.eventsPackage;
            outputFilePath = configuration.eventsOutputPath;
        } else if (isCommands(parser)) {
            currentGenerationPackage = configuration.commandsPackage;
            outputFilePath = configuration.commandsOutputPath;
        } else {
            classGenerator = ClassGenerator.classGenerator();
            classGenerator.withName(parser.getCurrentName());
            classGenerator.withPackage(currentGenerationPackage);
        }
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
