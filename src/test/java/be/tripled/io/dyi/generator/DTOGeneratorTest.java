package be.tripled.io.dyi.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DTOGeneratorTest {

    @Test
    void generate() {
        final String exampleClass = DTOGenerator.create()
                                                .withPackage("be.tripled.io.dyi.generator.example")
                                                .withName("ExampleClass")
                                                .withFieldName("customerId")
                                                .withFieldName("otherStuff")
                                                .withMarkerInterface("Event")
                                                .generate();
        assertTrue(exampleClass.contains("customerId"));
    }
}