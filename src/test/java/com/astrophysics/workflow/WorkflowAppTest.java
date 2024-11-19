package com.astrophysics.workflow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WorkflowAppTest {

    @Test
    public void testWorkflowAppInitialization() {
        // Test to ensure the application's main method initializes without errors
        assertDoesNotThrow(() -> WorkflowApp.main(new String[]{}),
                "WorkflowApp should initialize without throwing exceptions.");
    }
}
