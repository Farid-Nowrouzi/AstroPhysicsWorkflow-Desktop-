package com.astrophysics.workflow;

/**
 * Represents the segmentation step in the workflow.
 * Implements multiple types of polymorphism as required.
 * This step is responsible for segmenting data or items based on workflow needs.
 */
public class SegmentationStep extends BaseStep {

    // Constructor to set the name of the step
    public SegmentationStep() {
        super("Segmentation Step");
    }

    /**
     * Override the execute method to define the specific logic for segmentation.
     *
     * @throws StepExecutionException if an error occurs during execution.
     */
    @Override
    public void execute() throws StepExecutionException {
        System.out.println("[SegmentationStep] Starting segmentation process...");

        try {
            // Simulate segmentation process with delays
            Thread.sleep(1000); // Simulates the initial stage of segmentation
            System.out.println("[SegmentationStep] Segmentation in progress...");

            // Simulate successful completion
            Thread.sleep(1000); // Simulates the completion of segmentation
            System.out.println("[SegmentationStep] Segmentation completed successfully.");

            // Mark the segmentation step as completed
            setCompleted(true);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted state
            throw new StepExecutionException("Segmentation process was interrupted.", e);
        } catch (Exception e) {
            throw new StepExecutionException("An unexpected error occurred during the segmentation process.", e);
        }
    }

    /**
     * Overloaded execute method with additional context.
     * Demonstrates overloading polymorphism.
     *
     * @param context Additional context for segmentation processing.
     * @throws StepExecutionException if an error occurs during execution.
     */
    public void execute(String context) throws StepExecutionException {
        if (context == null || context.isEmpty()) {
            throw new IllegalArgumentException("Context cannot be null or empty.");
        }
        System.out.println("[SegmentationStep] Context: " + context);
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
        return "Step: Segmentation, Status: " + (isCompleted() ? "Completed" : "Pending");
    }

    /**
     * Performs specific segmentation logic.
     * This method is required for coercion-based polymorphism in Workflow.
     */
    public void performSegmentation() {
        System.out.println("[SegmentationStep] Performing detailed segmentation logic...");
        // Add detailed segmentation logic here
        System.out.println("[SegmentationStep] Detailed segmentation logic executed.");
    }

    /**
     * Generates a detailed report for this step.
     * Demonstrates method customization in the subclass.
     *
     * @return A string describing the segmentation results.
     */
    public String generateReport() {
        return "[SegmentationStep] Segmentation step completed. Data has been segmented and logged.";
    }
}
