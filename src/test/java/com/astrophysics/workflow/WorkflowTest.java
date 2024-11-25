package com.astrophysics.workflow;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class WorkflowTest {

    @Test
    public void testExecuteWorkflow() {
        // Initialize individual steps for the workflow
        Step processingStep = new DataProcessingStep();
        Step validationStep = new DataValidationStep();

        // Combine the steps into a workflow
        Workflow<Step> workflow = new Workflow<>(Arrays.asList(processingStep, validationStep));

        // Ensure that executing the workflow runs smoothly without exceptions
        assertDoesNotThrow(() -> workflow.execute(),
                "Workflow execution should not throw any exceptions.");
    }

    @Test
    public void testEmptyWorkflow() {
        // Create an empty workflow
        Workflow<Step> emptyWorkflow = new Workflow<>();

        // Ensure that executing an empty workflow does not throw exceptions
        assertDoesNotThrow(() -> emptyWorkflow.execute(),
                "Executing an empty workflow should not throw any exceptions.");
    }

    @Test
    public void testWorkflowWithSingleStep() {
        // Create a workflow with a single step
        Step segmentationStep = new SegmentationStep();
        Workflow<Step> workflow = new Workflow<>(Collections.singletonList(segmentationStep));

        // Ensure that executing the workflow with one step runs smoothly
        assertDoesNotThrow(() -> workflow.execute(),
                "Workflow with a single step should execute without throwing exceptions.");
    }

    @Test
    public void testWorkflowExecutionException() {
        // Create a workflow with a step that throws an exception
        Step failingStep = new DataProcessingStep(); // Assuming it throws StepExecutionException
        Workflow<Step> workflow = new Workflow<>(Collections.singletonList(failingStep));

        // Ensure that the workflow execution propagates the exception correctly
        assertThrows(WorkflowExecutionException.class, workflow::execute,
                "Workflow execution should throw WorkflowExecutionException if a step fails.");
    }

    @Test
    public void testAddStepToWorkflow() {
        // Create an empty workflow
        Workflow<Step> workflow = new Workflow<>();

        // Add a step and verify it's added correctly
        Step detectionStep = new DetectionStep();
        assertDoesNotThrow(() -> workflow.addStep(detectionStep),
                "Adding a step to the workflow should not throw any exceptions.");
        assertEquals(1, workflow.getSteps().size(),
                "Workflow should contain one step after addition.");
    }

    @Test
    public void testExecuteSpecificStep() {
        // Create a workflow and add steps
        Workflow<Step> workflow = new Workflow<>();
        Step segmentationStep = new SegmentationStep();
        Step validationStep = new DataValidationStep();
        workflow.addStep(segmentationStep);
        workflow.addStep(validationStep);

        // Ensure that a specific step executes successfully
        assertDoesNotThrow(() -> workflow.executeSpecificStep(segmentationStep),
                "Specific step execution should not throw any exceptions.");
    }
}
