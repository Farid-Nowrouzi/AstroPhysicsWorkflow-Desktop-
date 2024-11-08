package com.astrophysics.workflow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DataValidationStepTest {

    @Test
    public void testExecute() {
        // Create an instance of DataValidationStep
        DataValidationStep dataValidationStep = new DataValidationStep();

        // Verify that executing this step does not throw any exceptions
        assertDoesNotThrow(() -> dataValidationStep.execute(),
                "DataValidationStep execution should not throw any exceptions.");
    }
}
