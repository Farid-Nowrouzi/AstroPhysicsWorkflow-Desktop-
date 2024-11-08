package com.astrophysics.workflow;

public abstract class BaseStep implements Step {

    // Common properties for all steps
    protected String stepName;
    private boolean completed = false; // Flag to track if step is completed

    // Constructor to initialize step name
    public BaseStep(String stepName) {
        this.stepName = stepName;
    }

    // Getter for the step name
    public String getStepName() {
        return stepName;
    }

    // Method to mark the step as completed
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // Method to check if the step is completed
    public boolean isCompleted() {
        return completed;
    }

    // Abstract method to be implemented by subclasses
    @Override
    public abstract void execute() throws StepExecutionException;

    // Optional: Provide a description or status message of the step
    public String getStatus() {
        return completed ? stepName + " is completed." : stepName + " is in progress or not started.";
    }
}
