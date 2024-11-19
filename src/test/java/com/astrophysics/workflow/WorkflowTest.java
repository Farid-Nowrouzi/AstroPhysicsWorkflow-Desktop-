package com.astrophysics.workflow;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WorkflowTest {

    @Test
    public void testExecuteWorkflow() {
        // Step 1: Initialize individual steps for the workflow
        Step processingStep = new DataProcessingStep();
        Step validationStep = new DataValidationStep();

        // Step 2: Combine the steps into a workflow
        Workflow workflow = new Workflow(Arrays.asList(processingStep, validationStep));

        // Step 3: Ensure that executing the workflow runs smoothly without exceptions
        assertDoesNotThrow(() -> workflow.execute(),
                "Workflow execution should not throw any exceptions.");
    }
}
