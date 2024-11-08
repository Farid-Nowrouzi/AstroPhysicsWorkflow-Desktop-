package com.astrophysics.workflow;

public class DataProcessingStep extends BaseStep {

    public DataProcessingStep() {
        super("Data Processing Step");
    }

    @Override
    public void execute() throws StepExecutionException {
        System.out.println("Processing data...");
        try {
            // Simulate data processing with a delay
            Thread.sleep(2000);
            // Simulate an error in data processing
            throw new StepExecutionException("Data processing error");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted state
        }
    }
}
