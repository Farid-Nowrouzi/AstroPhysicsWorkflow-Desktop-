package com.astrophysics.workflow;

/**
 * Custom exception to indicate errors during the orchestration process.
 * This exception is thrown to halt the workflow execution when a critical
 * error occurs in one of the steps during orchestration.
 */
public class OrchestrationException extends Exception {

    /**
     * Constructs an OrchestrationException with a specific error message.
     *
     * @param message the error message describing the cause of the exception
     */
    public OrchestrationException(String message) {
        super(message); // Pass the error message to the superclass
    }

    /**
     * Constructs an OrchestrationException with a specific error message
     * and the underlying cause of the exception.
     *
     * @param message the error message describing the cause of the exception
     * @param cause   the Throwable that caused this exception
     */
    public OrchestrationException(String message, Throwable cause) {
        super(message, cause); // Pass both the message and cause to the superclass
    }
}
