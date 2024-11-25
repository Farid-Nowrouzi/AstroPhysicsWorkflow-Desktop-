package com.astrophysics.workflow;

/**
 * Represents the base implementation of a workflow step, providing common functionality
 * and structure for all derived step classes.
 */
public abstract class BaseStep implements Step {

    // Name of the step, used to identify and describe it
    protected String stepName;

    // A flag to indicate whether the step has been successfully completed
    private boolean completed = false;

    // Constructor to initialize the step with a specific name
    public BaseStep(String stepName) {
        if (stepName == null || stepName.isEmpty()) {
            throw new IllegalArgumentException("Step name cannot be null or empty");
        }
        this.stepName = stepName;
    }

    // Getter to retrieve the name of the step
    public String getStepName() {
        return stepName;
    }

    // Setter to update the completion status of the step
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // Method to check if the step is completed
    public boolean isCompleted() {
        return completed;
    }

    // Abstract method that must be implemented by subclasses to define the specific step logic
    @Override
    public abstract void execute() throws StepExecutionException;

    // Overloaded execute method with additional context parameter (overloading polymorphism)
    @Override
    public void execute(String context) throws StepExecutionException {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        System.out.println("Executing step: " + stepName + " with context: " + context);
        execute(); // Call the default execution logic
    }

    // Default implementation for describing the step (parametric polymorphism)
    @Override
    public String describeStep() {
        return "Step Name: " + stepName + ", Status: " + (completed ? "Completed" : "In Progress or Not Started");
    }

    // Method to provide a status message describing the current state of the step
    public String getStatus() {
        return completed ? stepName + " is completed." : stepName + " is in progress or not started.";
    }
}
