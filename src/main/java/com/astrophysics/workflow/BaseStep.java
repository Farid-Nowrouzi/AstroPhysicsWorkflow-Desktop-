package com.astrophysics.workflow;

public abstract class BaseStep implements Step {

    // Name of the step, used to identify and describe it
    protected String stepName;

    // A flag to indicate whether the step has been successfully completed
    private boolean completed = false;

    // Constructor to initialize the step with a specific name
    public BaseStep(String stepName) {
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

    // Method to provide a status message describing the current state of the step
    public String getStatus() {
        return completed ? stepName + " is completed." : stepName + " is in progress or not started.";
    }
}
