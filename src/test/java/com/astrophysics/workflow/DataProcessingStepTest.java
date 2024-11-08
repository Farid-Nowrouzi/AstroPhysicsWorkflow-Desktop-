package com.astrophysics.workflow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataProcessingStepTest {

    @Test
    public void testExecute() {
        // Create an instance of DataProcessingStep
        DataProcessingStep dataProcessingStep = new DataProcessingStep();

        // Verify that executing this step throws a StepExecutionException
        assertThrows(StepExecutionException.class, () -> {
            dataProcessingStep.execute();
        }, "DataProcessingStep execution should throw StepExecutionException.");
    }
}
