package com.astrophysics.workflow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DataValidationStepTest {

    @Test
    public void testExecute() {
        // Instantiate the DataValidationStep to be tested
        DataValidationStep dataValidationStep = new DataValidationStep();

        // Ensure that executing the step does not throw any exceptions
        assertDoesNotThrow(() -> dataValidationStep.execute(),
                "DataValidationStep execution should not throw any exceptions.");
    }
}
