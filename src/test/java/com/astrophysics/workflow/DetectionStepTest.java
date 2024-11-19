package com.astrophysics.workflow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DetectionStepTest {

    @Test
    public void testExecute() {
        // Create an instance of DetectionStep
        DetectionStep detectionStep = new DetectionStep();

        // Ensuring that executing this step does not throw any exceptions
        assertDoesNotThrow(() -> detectionStep.execute(),
                "DetectionStep execution should not throw any exceptions.");
    }
}
