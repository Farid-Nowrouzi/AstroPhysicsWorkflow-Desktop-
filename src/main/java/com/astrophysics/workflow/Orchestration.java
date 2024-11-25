package com.astrophysics.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Responsible for orchestrating a sequence of workflow steps.
 * Executes each step in sequence while handling exceptions and logging progress.
 */
public class Orchestration {
    // Logger to record orchestration progress and errors
    private static final Logger logger = LoggerFactory.getLogger(Orchestration.class);

    // List of steps that make up the workflow
    private List<Step> steps;

    /**
     * Constructor to initialize orchestration with a list of workflow steps.
     *
     * @param steps the list of workflow steps to be orchestrated
     */
    public Orchestration(List<Step> steps) {
        this.steps = steps;
    }

    /**
     * Starts the orchestration process by executing each step in sequence.
     * Logs progress and handles errors at each step.
     *
     * @throws OrchestrationException if a step encounters a critical error
     */
    public void start() throws OrchestrationException {
        logger.info("Orchestration process started."); // Log the start of orchestration

        // Iterate over each step in the workflow
        for (Step step : steps) {
            try {
                // Log the start of the current step
                logger.info("Executing step: {}", step.getClass().getSimpleName());

                // Execute the step logic
                step.execute();

                // Log the successful completion of the step
                logger.info("Step completed: {}", step.getClass().getSimpleName());
            } catch (StepExecutionException e) {
                // Log the error and its context
                logger.error("Execution error in step: {}. Exception: {}", step.getClass().getSimpleName(), e.getMessage());

                // Halt orchestration and throw a custom exception
                throw new OrchestrationException("Orchestration halted due to failure in step: "
                        + step.getClass().getSimpleName(), e);
            }
        }

        // Log the successful completion of the orchestration process
        logger.info("Orchestration process completed successfully.");
    }

    /**
     * Retrieves the list of workflow steps for review or debugging.
     *
     * @return the list of steps in the orchestration
     */
    public List<Step> getSteps() {
        return steps;
    }
}
