package com.astrophysics.workflow;

/**
 * Represents the segmentation step in the workflow. This step is responsible
 * for dividing an image into smaller, meaningful segments for further processing.
 */
public class SegmentationStep extends BaseStep {

    /**
     * Constructor to initialize the step with its name.
     */
    public SegmentationStep() {
        super("Segmentation Step"); // Set the step name using the BaseStep constructor
    }

    /**
     * Executes the segmentation logic for this step. Simulates the process
     * with a delay to mimic real-world computation.
     *
     * @throws StepExecutionException if an error occurs during execution
     */
    @Override
    public void execute() throws StepExecutionException {
        System.out.println("Segmenting image..."); // Log the start of segmentation

        try {
            // Simulate segmentation process with a delay
            Thread.sleep(1500);

            // Placeholder for future segmentation logic
            // Here, the actual logic for image segmentation would be implemented
            // (e.g., dividing the image into regions or detecting specific areas).

        } catch (InterruptedException e) {
            // Handle interruptions and restore the thread's interrupted state
            Thread.currentThread().interrupt();
            System.err.println("Segmentation process was interrupted.");
        }
    }
}
