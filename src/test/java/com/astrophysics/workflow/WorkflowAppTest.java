package com.astrophysics.workflow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkflowAppTest {

    @Test
    public void testWorkflowAppInitialization() {
        // Ensure the application's main method initializes without errors
        assertDoesNotThrow(() -> WorkflowApp.main(new String[]{}),
                "WorkflowApp should initialize without throwing exceptions.");
    }

    @Test
    public void testWorkflowComponentsInitialization() {
        // Test initialization of workflow components
        Workflow<Step> workflow = new Workflow<>();
        assertNotNull(workflow, "Workflow instance should not be null.");
        assertTrue(workflow.getSteps().isEmpty(), "Workflow steps should initially be empty.");

        // Add a step to the workflow and ensure it's added successfully
        Step step = new SegmentationStep();
        assertDoesNotThrow(() -> workflow.addStep(step),
                "Adding a step to the workflow should not throw exceptions.");
        assertEquals(1, workflow.getSteps().size(), "Workflow should contain one step after addition.");
    }

    @Test
    public void testWorkflowExecution() {
        // Create a workflow with multiple steps
        Workflow<Step> workflow = new Workflow<>();
        workflow.addStep(new SegmentationStep());
        workflow.addStep(new DetectionStep());

        // Execute the workflow and ensure it completes without exceptions
        assertDoesNotThrow(() -> workflow.execute(),
                "Workflow execution should not throw any exceptions.");
    }
}
