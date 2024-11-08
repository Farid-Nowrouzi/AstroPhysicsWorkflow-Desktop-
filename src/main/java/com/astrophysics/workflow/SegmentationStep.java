package com.astrophysics.workflow;

public class SegmentationStep extends BaseStep {

    public SegmentationStep() {
        super("Segmentation Step");
    }

    @Override
    public void execute() throws StepExecutionException {
        System.out.println("Segmenting image...");
        try {
            // Simulate segmentation process with a delay
            Thread.sleep(1500);
            // Here, we might add future segmentation logic
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted state
        }
    }
}
