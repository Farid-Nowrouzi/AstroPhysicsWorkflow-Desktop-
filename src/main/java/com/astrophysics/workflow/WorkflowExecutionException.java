package com.astrophysics.workflow;

/**
 * Represents a custom exception for handling errors during workflow execution.
 * This exception is thrown when a critical issue occurs in any workflow step,
 * indicating that the execution should stop or require special handling.
 */
public class WorkflowExecutionException extends Exception {

    /**
     * Constructs a WorkflowExecutionException with a descriptive error message.
     *
     * @param message A message explaining the cause of the exception.
     */
    public WorkflowExecutionException(String message) {
        super(message); // Pass the message to the superclass
    }

    /**
     * Constructs a WorkflowExecutionException with a message and the root cause.
     *
     * @param message A message explaining the cause of the exception.
     * @param cause   The underlying exception that caused this exception, useful for debugging.
     */
    public WorkflowExecutionException(String message, Throwable cause) {
        super(message, cause); // Pass both the message and cause to the superclass
    }
}
