package com.astrophysics.workflow;

/**
 * Represents the data processing step in the workflow.
 * Demonstrates overriding polymorphism and enhanced error handling.
 */
public class DataProcessingStep extends BaseStep {

    // Constructor to set the name of the step
    public DataProcessingStep() {
        super("Data Processing Step");
    }

    /**
     * Override the abstract execute method to define specific logic for data processing.
     * Demonstrates overriding polymorphism.
     *
     * @throws StepExecutionException if an error occurs during execution.
     */
    @Override
    public void execute() throws StepExecutionException {
        System.out.println("[DataProcessingStep] Starting data processing..."); // Log the start of data processing

        try {
            // Simulate data processing with a delay to mimic real-world computation
            Thread.sleep(2000);

            // Simulate successful completion
            setCompleted(true);
            System.out.println("[DataProcessingStep] Data processing completed successfully.");
        } catch (InterruptedException e) {
            // Handle thread interruption and restore the interrupted state
            Thread.currentThread().interrupt();
            throw new StepExecutionException("Thread was interrupted during data processing.", e);
        } catch (Exception e) {
            throw new StepExecutionException("An unexpected error occurred during data processing.", e);
        }
    }

    /**
     * Overloaded execute method with additional context.
     * Demonstrates overloading polymorphism.
     *
     * @param context Additional context for data processing.
     * @throws StepExecutionException if an error occurs during execution.
     */
    public void execute(String context) throws StepExecutionException {
        if (context == null || context.isEmpty()) {
            throw new IllegalArgumentException("Context cannot be null or empty.");
        }
        System.out.println("[DataProcessingStep] Context: " + context);
        execute(); // Calls the main execute logic
    }

    /**
     * Provides a description of the step.
     * Demonstrates parametric polymorphism by customizing the description.
     *
     * @return A string describing the step.
     */
    @Override
    public String describeStep() {
        return "Step: Data Processing, Status: " + (isCompleted() ? "Completed" : "Pending");
    }
}
