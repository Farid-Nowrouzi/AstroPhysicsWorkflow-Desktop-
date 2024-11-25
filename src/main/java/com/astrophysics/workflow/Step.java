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

    /**
     * Overloaded execute method with additional parameter for customization.
     * Demonstrates overloading polymorphism.
     *
     * @param context Additional context or configuration for the step execution.
     * @throws StepExecutionException if an error occurs during execution.
     */
    default void execute(String context) throws StepExecutionException {
        System.out.println("Executing step with context: " + context);
        execute(); // Calls the default execution logic.
    }

    /**
     * Method to describe the step.
     * Demonstrates parametric polymorphism where implementations can define specific behaviors.
     *
     * @return A string describing the step.
     */
    default String describeStep() {
        return "Generic Step Implementation";
    }
}
