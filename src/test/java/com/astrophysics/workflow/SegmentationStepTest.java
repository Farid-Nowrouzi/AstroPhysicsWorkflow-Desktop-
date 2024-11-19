package com.astrophysics.workflow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class SegmentationStepTest {

    @Test
    public void testExecute() {
        // Initialize an instance of the SegmentationStep class
        SegmentationStep segmentationStep = new SegmentationStep();

        // Verify that the execute method completes without throwing exceptions
        assertDoesNotThrow(() -> segmentationStep.execute(),
                "SegmentationStep execution should not throw any exceptions.");
    }
}
