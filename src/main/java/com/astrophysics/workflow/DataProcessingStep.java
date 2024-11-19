package com.astrophysics.workflow;

public class DataProcessingStep extends BaseStep {

    // Constructor to set the name of the step
    public DataProcessingStep() {
        super("Data Processing Step");
    }

    // Override the abstract execute method to define specific logic for data processing
    @Override
    public void execute() throws StepExecutionException {
        System.out.println("Processing data..."); // Log the start of data processing

        try {
            // Simulate data processing with a delay to mimic real-world computation
            Thread.sleep(2000);

            // Simulate an intentional error during the processing step
            throw new StepExecutionException("Data processing error");

        } catch (InterruptedException e) {
            // Handle thread interruption and restore the interrupted state
            Thread.currentThread().interrupt();
            System.err.println("Thread was interrupted during data processing.");
        }
    }
}
