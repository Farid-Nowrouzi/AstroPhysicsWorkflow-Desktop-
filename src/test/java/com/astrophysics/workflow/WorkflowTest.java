package com.astrophysics.workflow;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WorkflowTest {

    @Test
    public void testExecuteWorkflow() {
        // Initialize steps
        Step processingStep = new DataProcessingStep();
        Step validationStep = new DataValidationStep();

        // Initialize workflow with the steps
        Workflow workflow = new Workflow(Arrays.asList(processingStep, validationStep));

        // Verify that executing the workflow does not throw any exceptions
        assertDoesNotThrow(() -> workflow.execute(),
                "Workflow execution should not throw any exceptions.");
    }
}
