package com.astrophysics.workflow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataProcessingStepTest {

    @Test
    public void testExecute() {
        // Create an instance of DataProcessingStep to be tested
        DataProcessingStep dataProcessingStep = new DataProcessingStep();

        // Ensure that executing the step throws the expected StepExecutionException
        assertThrows(StepExecutionException.class, () -> {
            dataProcessingStep.execute();
        }, "DataProcessingStep execution should throw StepExecutionException.");
    }
}
