package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTest {

    @Test
    void testMessage() {
        HelloController controller = new HelloController();
        assertEquals("Hello from Kubernetes!", controller.getMessage());
    }
}
