package com.astrophysics.workflow;

/**
 * Represents a generic workflow step. This interface defines the contract
 * for all steps in the workflow, ensuring consistency in their implementation.
 * Each step must implement the `execute` method to define its specific behavior.
 */
public interface Step {

    /**
     * Executes the logic specific to the workflow step.
     *
     * @throws StepExecutionException if an error occurs during the execution of the step
     */
    void execute() throws StepExecutionException;
}
