package be.tripled.io.dyi.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class ClassGenerator {

    private String className;
    private String packageName;
    private List<String> fieldNames = new ArrayList<>();

    private ClassGenerator() {
    }

    public static ClassGenerator classGenerator() {
        return new ClassGenerator();
    }

    public ClassGenerator withName(String val) {
        className = val;
        return this;
    }

    public ClassGenerator withPackage(String val) {
        packageName = val;
        return this;
    }

    public ClassGenerator withFieldName(String val) {
        fieldNames.add(val);
        return this;
    }

    public String generate() {
        verify();

        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package ")
                     .append(packageName)
                     .append("; \n");
        stringBuilder.append("/** Generated class from dsl file. Do not add own logic. ")
                     .append("\n")
                     .append(" Generate Builders through IDE.")
                     .append("\n")
                     .append("*/")
                     .append("\n");
        stringBuilder.append("public class ")
                     .append(className)
                     .append(" {\n");

        fieldNames.forEach(addField(stringBuilder));

        stringBuilder.append("} ")
                     .append("\n");
        return stringBuilder.toString();
    }

    private void verify() {
        if(className == null || className.isEmpty())
            throw new RuntimeException("No classname set");
        if(packageName == null || packageName.isEmpty())
            throw new RuntimeException("No packageName set");
    }

    private Consumer<? super String> addField(StringBuilder stringBuilder) {
        return (Consumer<String>) fieldName -> stringBuilder.append("public final String ")
                                                    .append(fieldName)
                                                    .append(";")
                                                    .append("\n");
    }

}
