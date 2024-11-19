package com.astrophysics.workflow;

/**
 * Represents a custom exception that handles errors during the execution of a workflow.
 * This exception can be thrown when a critical issue occurs in any workflow step,
 * indicating that the execution should stop or be handled with specific logic.
 */
public class WorkflowExecutionException extends Exception {

    /**
     * Constructor to create a WorkflowExecutionException with a custom error message.
     *
     * @param message A descriptive message explaining the cause of the exception.
     */
    public WorkflowExecutionException(String message) {
        super(message); // Pass the message to the superclass (Exception)
    }

    /**
     * Constructor to create a WorkflowExecutionException with a message and a root cause.
     *
     * @param message A descriptive message explaining the cause of the exception.
     * @param cause   The original exception that caused this exception, useful for debugging.
     */
    public WorkflowExecutionException(String message, Throwable cause) {
        super(message, cause); // Pass both message and cause to the superclass (Exception)
    }
}
