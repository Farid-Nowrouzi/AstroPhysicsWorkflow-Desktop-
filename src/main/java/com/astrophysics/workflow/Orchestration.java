package com.astrophysics.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Orchestration {
    // Logger to record orchestration progress and errors
    private static final Logger logger = LoggerFactory.getLogger(Orchestration.class);

    // List of steps that make up the workflow
    private List<Step> steps;

    // Constructor to initialize orchestration with a list of workflow steps
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
        logger.info("Orchestration started."); // Log the start of orchestration

        // Iterate over each step in the workflow
        for (Step step : steps) {
            try {
                // Log the start of the current step
                logger.info("Starting execution of step: {}", step.getClass().getSimpleName());

                // Execute the step logic
                step.execute();

                // Log the successful completion of the step
                logger.info("Finished execution of step: {}", step.getClass().getSimpleName());
            } catch (StepExecutionException e) {
                // Log the error and its context
                logger.error("Error in step: {}. Exception: {}", step.getClass().getSimpleName(), e.getMessage());

                // Halt orchestration and throw a custom exception
                throw new OrchestrationException("Orchestration halted due to error in step: "
                        + step.getClass().getSimpleName(), e);
            }
        }

        // Log the successful completion of the orchestration process
        logger.info("Orchestration completed.");
    }

    // Getter method to retrieve the list of workflow steps
    public List<Step> getSteps() {
        return steps;
    }
}
