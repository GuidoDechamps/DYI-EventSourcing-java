package be.tripled.io.dyi.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ClassGeneratorTest {

    @Test
    void generate() {
        final String exampleClass = ClassGenerator.classGenerator()
                                                  .withPackage("be.tripled.io.dyi.generator.example")
                                                  .withName("ExampleClass")
                                                  .withFieldName("customerId")
                                                  .withFieldName("otherStuff")
                                                  .generate();
        System.out.println(exampleClass);
        assertTrue(exampleClass.contains("customerId"));
    }
}