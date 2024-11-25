package com.astrophysics.workflow;

/**
 * Represents the detection step in the workflow.
 * Implements multiple types of polymorphism as required.
 * This step is responsible for detecting specific items based on workflow needs.
 */
public class DetectionStep extends BaseStep {

    // Constructor to set the name of the step
    public DetectionStep() {
        super("Detection Step");
    }

    /**
     * Override the execute method to define the specific logic for detection.
     *
     * @throws StepExecutionException if an error occurs during execution.
     */
    @Override
    public void execute() throws StepExecutionException {
        System.out.println("[DetectionStep] Starting detection process...");

        try {
            // Simulate detection process with delays
            Thread.sleep(1000); // Simulates the initial stage of detection
            System.out.println("[DetectionStep] Detection in progress...");

            // Simulate successful completion
            Thread.sleep(1000); // Simulates the completion of detection
            System.out.println("[DetectionStep] Detection completed successfully.");

            // Mark the detection step as completed
            setCompleted(true);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted state
            throw new StepExecutionException("Detection process was interrupted.", e);
        } catch (Exception e) {
            throw new StepExecutionException("An unexpected error occurred during the detection process.", e);
        }
    }

    /**
     * Overloaded execute method with additional context.
     * Demonstrates overloading polymorphism.
     *
     * @param context Additional context for detection processing.
     * @throws StepExecutionException if an error occurs during execution.
     */
    public void execute(String context) throws StepExecutionException {
        if (context == null || context.isEmpty()) {
            throw new IllegalArgumentException("Context cannot be null or empty.");
        }
        System.out.println("[DetectionStep] Context: " + context);
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
        return "Step: Detection, Status: " + (isCompleted() ? "Completed" : "Pending");
    }

    /**
     * Generates a detailed report for this step.
     * Demonstrates method customization in the subclass.
     *
     * @return A string describing the detection results.
     */
    public String generateReport() {
        return "[DetectionStep] Detection step completed. All relevant items have been detected and logged.";
    }
}
