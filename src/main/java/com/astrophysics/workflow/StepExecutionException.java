package com.astrophysics.workflow;

/**
 * Custom exception to indicate errors during the execution of a workflow step.
 * This exception is thrown when a step encounters an issue that prevents it from completing successfully.
 */
public class StepExecutionException extends Exception {

    /**
     * Constructor to create a StepExecutionException with a specific error message.
     *
     * @param message the detailed error message explaining the cause of the exception
     */
    public StepExecutionException(String message) {
        super(message); // Pass the error message to the parent Exception class
    }
}
