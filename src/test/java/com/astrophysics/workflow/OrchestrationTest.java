package com.astrophysics.workflow;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class OrchestrationTest {

    @Test
    public void testOrchestrationExecution() {
        // Create individual steps for processing and validation
        Step processingStep = new DataProcessingStep();
        Step validationStep = new DataValidationStep();

        // Create a workflow by combining the defined steps
        Workflow workflow = new Workflow(Arrays.asList(processingStep, validationStep));

        // Set up orchestration to manage the workflow execution
        Orchestration orchestration = new Orchestration(workflow.getSteps());

        // Validate that the orchestration runs successfully without throwing any exceptions
        assertDoesNotThrow(() -> orchestration.start(),
                "Orchestration execution should not throw any exceptions.");
    }
}
