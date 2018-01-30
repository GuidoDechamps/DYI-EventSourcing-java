package be.tripled.io.dyi.generator;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.File;
import java.io.IOException;

class CodeGenerator {

    public static void main(String... args) throws IOException {
        final String fileName = getFileName(args);
        final File file = new File(fileName);
        if (file.canRead()) {
            generateCode(file);
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

    private static void generateCode(File file) throws IOException {
        final YAMLFactory factory = new YAMLFactory();
        final YAMLParser parser = factory.createParser(file);
        while (true)
            printIt(parser);
    }

    private static void printIt(YAMLParser parser) throws IOException {
        final JsonToken jsonToken = parser.nextValue();
        if (jsonToken == null)
            quit();


        switch (jsonToken) {
            case START_OBJECT:
                System.out.println("Started " + parser.getCurrentName());
                break;
            case END_OBJECT:
                break;
            case FIELD_NAME:
                System.out.println("Field name " + parser.getCurrentName());
                break;
            case VALUE_STRING:
                System.out.println("Field value " + parser.getCurrentName() + " : " + parser.getText());
                break;
            default:
                System.out.println("Ignoring " + jsonToken);
        }
    }

    private static void quit() {
        System.out.println("--------");
        System.out.println("The end");
        System.exit(1);
    }

}
