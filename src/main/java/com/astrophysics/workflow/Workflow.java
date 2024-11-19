package com.astrophysics.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Represents a workflow comprising multiple sequential steps.
 * This class is responsible for orchestrating and executing the defined steps
 * while handling any errors encountered during the process.
 */
public class Workflow {

    // Logger instance for logging workflow execution details
    private static final Logger logger = LoggerFactory.getLogger(Workflow.class);

    // List of steps to be executed as part of the workflow
    private List<Step> steps;

    /**
     * Constructor to initialize the workflow with a list of steps.
     *
     * @param steps the ordered list of steps that make up the workflow
     */
    public Workflow(List<Step> steps) {
        this.steps = steps;
    }

    /**
     * Getter for retrieving the list of steps in the workflow.
     *
     * @return the list of steps
     */
    public List<Step> getSteps() {
        return steps;
    }

    /**
     * Executes the workflow by iterating through the list of steps and invoking their execution logic.
     * Logs the status of each step and handles any exceptions encountered during execution.
     *
     * @throws WorkflowExecutionException if a critical error occurs in any step, halting the workflow
     */
    public void execute() throws WorkflowExecutionException {
        logger.info("Workflow execution started."); // Log the start of the workflow

        for (Step step : steps) {
            try {
                logger.info("Executing step: {}", step.getClass().getSimpleName()); // Log step execution start
                step.execute(); // Execute the current step
                logger.info("Completed step: {}", step.getClass().getSimpleName()); // Log successful completion
            } catch (StepExecutionException e) {
                logger.error("Error executing step: {}. Exception: {}", step.getClass().getSimpleName(), e.getMessage());
                // Halt the workflow execution if a step fails critically
                throw new WorkflowExecutionException("Workflow execution halted due to error in step: "
                        + step.getClass().getSimpleName(), e);
            }
        }

        logger.info("Workflow execution completed."); // Log the successful completion of the workflow
    }
}
