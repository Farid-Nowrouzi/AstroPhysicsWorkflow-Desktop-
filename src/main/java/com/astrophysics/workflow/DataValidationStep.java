package com.astrophysics.workflow;

// Ensure all necessary imports are present
import com.astrophysics.workflow.BaseStep;
import com.astrophysics.workflow.StepExecutionException;

public class DataValidationStep extends BaseStep {

    public DataValidationStep() {
        super("Data Validation Step");  // Call the superclass constructor with the step name
    }

    @Override
    public void execute() throws StepExecutionException {
        System.out.println("Validating data...");
        // Placeholder for validation logic
    }
}
