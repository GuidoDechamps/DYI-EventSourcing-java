package be.tripled.io.dyi;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldTest {
    @Test
    public void helloGuido() {
        final String response = new HelloWorld().hello("guido");
        assertEquals("Hello guido", response);
    }

}