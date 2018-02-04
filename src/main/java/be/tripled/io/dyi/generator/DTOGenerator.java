package be.tripled.io.dyi.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class DTOGenerator {

    private String className;
    private String packageName;
    private List<String> fieldNames = new ArrayList<>();
    private StringBuilder stringBuilder;
    private String markerInterface;

    private DTOGenerator() {
    }

    public static DTOGenerator create() {
        return new DTOGenerator();
    }

    public DTOGenerator withName(String val) {
        className = val;
        return this;
    }

    public DTOGenerator withPackage(String val) {
        packageName = val;
        return this;
    }

    public DTOGenerator withFieldName(String val) {
        fieldNames.add(val);
        return this;
    }

    public DTOGenerator withMarkerInterface(String val) {
        markerInterface = val;
        return this;
    }

    public String generate() {
        verify();
        stringBuilder = new StringBuilder();

        addPackageLine();
        addJavaDoc();
        addClassDefinitions();

        addClassFields();
        addConstructorByBuilder();
        addBuilder();
        closeClassFile();
        return stringBuilder.toString();
    }

    private void addConstructorByBuilder() {
        stringBuilder.append("private " +
                className +
                "" +
                "(" +
                className +
                ".Builder builder) {");
        addConstructorCopyFields();
        stringBuilder.append("}")
                     .append("public static ")
                     .append(className)
                     .append(".Builder newBuilder() {return new ")
                     .append(className)
                     .append(".Builder();   };");

    }

    private void addPackageLine() {
        stringBuilder.append("package ")
                     .append(packageName)
                     .append("; \n");
    }

    private void closeClassFile() {
        stringBuilder.append("} ")
                     .append("\n");
    }

    private void addClassDefinitions() {
        stringBuilder.append("public class ")
                     .append(className)
                     .append(" implements ")
                     .append(markerInterface)
                     .append(" {\n");
    }

    private void addClassFields() {
        fieldNames.forEach(addClassField());
    }

    private void addJavaDoc() {
        stringBuilder.append("/** Generated class from dsl file. Do not add own logic. ")
                     .append("\n")
                     .append("*/")
                     .append("\n");
    }

    private void addBuilder() {
        stringBuilder.append("public static final class Builder {")
                     .append("\n");
        addBuilderFields();
        stringBuilder.append("public ")
                     .append(className)
                     .append(" build(){ return new ")
                     .append(className)
                     .append("(this); }}");
    }

    private void addBuilderFields() {
        fieldNames.forEach(addBuilderField());
    }

    private void addConstructorCopyFields() {
        fieldNames.forEach(addConstructorFieldCopy());
    }

    private Consumer<String> addBuilderField() {
        return fieldName -> stringBuilder.append("private String ")
                                         .append(fieldName)
                                         .append(";")
                                         .append("public ")
                                         .append(className)
                                         .append(".Builder with")
                                         .append(fieldName)
                                         .append("(String val) {")
                                         .append(fieldName)
                                         .append("= val;")
                                         .append("return this;")
                                         .append("}");
    }

    private void verify() {
        if (isEmpty(this.className))
            throw new RuntimeException("No classname set");
        if (isEmpty(packageName))
            throw new RuntimeException("No packageName set");
        if (isEmpty(markerInterface))
            throw new RuntimeException("No markerInterface set");
    }

    private boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    private Consumer<? super String> addClassField() {
        return (Consumer<String>) fieldName -> stringBuilder.append("public final String ")
                                                            .append(fieldName)
                                                            .append(";")
                                                            .append("\n");
    }

    private Consumer<? super String> addConstructorFieldCopy() {
        return (Consumer<String>) fieldName -> stringBuilder.append(fieldName)
                                                            .append(" = builder.")
                                                            .append(fieldName)
                                                            .append(";")
                                                            .append("\n");
    }

}
