package com.astrophysics.workflow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SegmentationStepTest {

    @Test
    public void testExecute() {
        // Initialize an instance of the SegmentationStep class
        SegmentationStep segmentationStep = new SegmentationStep();

        // Verify that the execute method completes without throwing exceptions
        assertDoesNotThrow(() -> segmentationStep.execute(),
                "SegmentationStep execution should not throw any exceptions.");

        // Verify that the step is marked as completed after execution
        assertTrue(segmentationStep.isCompleted(),
                "SegmentationStep should be marked as completed after execution.");
    }

    @Test
    public void testExecuteWithContext() {
        // Initialize an instance of the SegmentationStep class
        SegmentationStep segmentationStep = new SegmentationStep();

        // Verify that the overloaded execute method with context does not throw exceptions
        assertDoesNotThrow(() -> segmentationStep.execute("Context for segmentation"),
                "SegmentationStep execution with context should not throw any exceptions.");

        // Verify that the step is marked as completed after execution with context
        assertTrue(segmentationStep.isCompleted(),
                "SegmentationStep should be marked as completed after execution with context.");
    }

    @Test
    public void testExecuteWithInvalidContext() {
        // Initialize an instance of the SegmentationStep class
        SegmentationStep segmentationStep = new SegmentationStep();

        // Verify that executing with a null context throws an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> segmentationStep.execute(null),
                "Executing SegmentationStep with null context should throw IllegalArgumentException.");

        // Verify that executing with an empty context throws an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> segmentationStep.execute(""),
                "Executing SegmentationStep with empty context should throw IllegalArgumentException.");

        // Verify that the step is not marked as completed after a failed execution
        assertFalse(segmentationStep.isCompleted(),
                "SegmentationStep should not be marked as completed if execution fails.");
    }

    @Test
    public void testGenerateReport() {
        // Initialize an instance of the SegmentationStep class
        SegmentationStep segmentationStep = new SegmentationStep();

        // Execute the step
        assertDoesNotThrow(() -> segmentationStep.execute(),
                "SegmentationStep execution should not throw any exceptions.");

        // Verify the generated report
        String report = segmentationStep.generateReport();
        assertEquals("[SegmentationStep] Segmentation step completed. Data has been segmented and logged.", report,
                "Generated report should match the expected output.");
    }
}
