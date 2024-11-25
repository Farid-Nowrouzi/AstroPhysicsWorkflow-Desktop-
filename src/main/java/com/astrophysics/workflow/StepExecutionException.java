package com.astrophysics.workflow;

/**
 * Custom exception for errors occurring during step execution.
 */
public class StepExecutionException extends Exception {

    /**
     * Constructor to create a StepExecutionException with a specific message.
     *
     * @param message the detail message.
     */
    public StepExecutionException(String message) {
        super(message);
    }

    /**
     * Constructor to create a StepExecutionException with a specific message and cause.
     *
     * @param message the detail message.
     * @param cause the cause of the exception.
     */
    public StepExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
