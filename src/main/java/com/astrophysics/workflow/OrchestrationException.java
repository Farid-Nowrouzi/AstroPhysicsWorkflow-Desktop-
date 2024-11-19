package com.astrophysics.workflow;

/**
 * Custom exception to indicate errors during the orchestration process.
 * This exception is used to halt the workflow execution when a critical
 * error occurs in one of the steps.
 */
public class OrchestrationException extends Exception {

    /**
     * Constructor to create an OrchestrationException with a specific error message.
     *
     * @param message the error message describing the cause of the exception
     */
    public OrchestrationException(String message) {
        super(message); // Pass the message to the parent Exception class
    }

    /**
     * Constructor to create an OrchestrationException with a specific error message
     * and the underlying cause of the exception.
     *
     * @param message the error message describing the cause of the exception
     * @param cause   the Throwable that caused this exception
     */
    public OrchestrationException(String message, Throwable cause) {
        super(message, cause); // Pass both message and cause to the parent Exception class
    }
}
