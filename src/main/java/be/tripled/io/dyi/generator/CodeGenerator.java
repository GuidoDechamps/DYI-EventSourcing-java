package be.tripled.io.dyi.generator;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class CodeGenerator {
    private static final String EVENTS_PACKAGE = "be.tripled.io.dyi.events";
    private static final String COMMANDS_PACKAGE = "be.tripled.io.dyi.commands";
    private String currentGenerationPackage = "unknown";
    private String eventsOutputFilePath = ".//src//main//java//be//tripled//io/dyi//events//";
    private String commandsOutputFilePath = ".//src//main//java//be//tripled//io/dyi//commands//";
    private String outputFilePath = eventsOutputFilePath;
    private ClassGenerator classGenerator = ClassGenerator.classGenerator();

    public static void main(String... args) throws IOException {
        final String fileName = getFileName(args);
        final File file = new File(fileName);
        if (file.canRead()) {
            new CodeGenerator().generateCode(file);
        } else
            System.out.println("[ERROR] Unable to read file " + fileName + " from " + file.getAbsolutePath());
    }

    private static String getFileName(String... args) {
        if (args.length == 0) {
            System.out.println("[ERROR] No YAML file name given");
            System.exit(-1);
        }
        return args[0];
    }

    private static void quit() {
        System.out.println("--------");
        System.out.println("The end");
        System.exit(1);
    }

    private void generateCode(File file) throws IOException {
        final YAMLFactory factory = new YAMLFactory();
        final YAMLParser parser = factory.createParser(file);
        while (true)
            processNextToken(parser);
    }

    private void processNextToken(YAMLParser parser) throws IOException {
        final JsonToken jsonToken = parser.nextValue();
        if (jsonToken == null)
            quit();
        processToken(parser, jsonToken);
    }

    private void processToken(YAMLParser parser, JsonToken jsonToken) throws IOException {

        switch (jsonToken) {
            case START_OBJECT:
                processStartToken(parser);
                break;
            case END_OBJECT:
                processEndToken(parser);
                break;
            case FIELD_NAME:
                System.out.println("Field name " + parser.getCurrentName());
                break;
            case VALUE_STRING:
                System.out.println("Field value " + parser.getCurrentName() + " : " + parser.getText());
                classGenerator.withFieldName(parser.getCurrentName());
                break;
            default:
                System.out.println("Ignoring " + jsonToken);
        }
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
            System.out.println("Writing " + clazzName + ".java with content " + classText.length());
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
            currentGenerationPackage = EVENTS_PACKAGE;
            outputFilePath=eventsOutputFilePath;
        } else if (isCommands(parser)) {
            currentGenerationPackage = COMMANDS_PACKAGE;
            outputFilePath=commandsOutputFilePath;
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
