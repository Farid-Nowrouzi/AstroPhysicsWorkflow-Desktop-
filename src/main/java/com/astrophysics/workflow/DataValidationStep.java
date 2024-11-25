package com.astrophysics.workflow;

/**
 * Represents the data validation step in the workflow.
 * Implements multiple types of polymorphism as per requirements.
 * This step is responsible for validating data against specific rules or formats.
 */
public class DataValidationStep extends BaseStep {

    /**
     * Constructor for the DataValidationStep.
     * Demonstrates subtyping polymorphism by calling the BaseStep constructor.
     */
    public DataValidationStep() {
        super("Data Validation Step");
    }

    /**
     * Executes the primary logic for data validation.
     * Demonstrates overriding polymorphism by providing specific functionality for the execute method.
     *
     * @throws StepExecutionException if an error occurs during data validation.
     */
    @Override
    public void execute() throws StepExecutionException {
        System.out.println("[DataValidationStep] Validating data...");

        try {
            // Simulate validation logic (e.g., checking for data integrity or compliance)
            Thread.sleep(1500);

            // Simulate successful validation
            setCompleted(true);
            System.out.println("[DataValidationStep] Data validation completed successfully.");
        } catch (InterruptedException e) {
            // Handle interruption of the thread
            Thread.currentThread().interrupt();
            throw new StepExecutionException("Thread was interrupted during data validation.", e);
        } catch (Exception e) {
            // Handle any unexpected errors
            throw new StepExecutionException("An unexpected error occurred during data validation.", e);
        }
    }

    /**
     * Overloaded execute method with validation rules.
     * Demonstrates overloading polymorphism by accepting additional parameters for specific use cases.
     *
     * @param validationRules A string representing validation rules to apply.
     * @throws StepExecutionException if validation fails or an error occurs.
     */
    public void execute(String validationRules) throws StepExecutionException {
        if (validationRules == null || validationRules.isEmpty()) {
            throw new IllegalArgumentException("Validation rules cannot be null or empty.");
        }
        System.out.println("[DataValidationStep] Applying validation rules: " + validationRules);
        execute(); // Calls the primary execute method
    }

    /**
     * Provides a description of the step.
     * Demonstrates parametric polymorphism by customizing the description output based on the step's status.
     *
     * @return A string describing the step.
     */
    @Override
    public String describeStep() {
        return "Step: Data Validation, Status: " + (isCompleted() ? "Completed" : "Pending");
    }
}
