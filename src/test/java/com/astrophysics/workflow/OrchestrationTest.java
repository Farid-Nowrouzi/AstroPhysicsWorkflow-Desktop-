package com.astrophysics.workflow;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class OrchestrationTest {

    @Test
    public void testOrchestrationExecution() {
        // Initialize steps
        Step processingStep = new DataProcessingStep();
        Step validationStep = new DataValidationStep();

        // Initialize workflow with the steps
        Workflow workflow = new Workflow(Arrays.asList(processingStep, validationStep));

        // Initialize orchestration with the workflow steps
        Orchestration orchestration = new Orchestration(workflow.getSteps());

        // Verify that executing orchestration does not throw any exceptions
        assertDoesNotThrow(() -> orchestration.start(),
                "Orchestration execution should not throw any exceptions.");
    }
}
