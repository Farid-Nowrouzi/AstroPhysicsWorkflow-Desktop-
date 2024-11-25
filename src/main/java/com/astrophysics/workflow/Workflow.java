package com.astrophysics.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a workflow comprising multiple sequential steps.
 * This class is responsible for orchestrating and executing the defined steps
 * while handling any errors encountered during the process.
 */
public class Workflow<T extends Step> {

    // Logger instance for logging workflow execution details
    private static final Logger logger = LoggerFactory.getLogger(Workflow.class);

    // List of steps to be executed as part of the workflow
    private List<T> steps;

    /**
     * Constructor to initialize the workflow with an empty list of steps.
     */
    public Workflow() {
        this.steps = new ArrayList<>(); // Initialize with an empty list
    }

    /**
     * Constructor to initialize the workflow with a predefined list of steps.
     *
     * @param steps the ordered list of steps that make up the workflow
     */
    public Workflow(List<T> steps) {
        this.steps = steps;
    }

    /**
     * Getter for retrieving the list of steps in the workflow.
     *
     * @return the list of steps
     */
    public List<T> getSteps() {
        return steps;
    }

    /**
     * Adds a step to the workflow.
     *
     * @param step the step to add
     */
    public void addStep(T step) {
        steps.add(step); // Subtyping: Accepts any object implementing the Step interface
        logger.info("Added step: {}", step.getClass().getSimpleName());
    }

    /**
     * Adds a step to the workflow with an optional description.
     * This demonstrates method overloading.
     *
     * @param step        the step to add
     * @param description an optional description of the step
     */
    public void addStep(T step, String description) {
        steps.add(step);
        logger.info("Added step: {} with description: {}", step.getClass().getSimpleName(), description);
    }

    /**
     * Executes the workflow by iterating through the list of steps and invoking their execution logic.
     * Logs the status of each step and handles any exceptions encountered during execution.
     *
     * @throws WorkflowExecutionException if a critical error occurs in any step, halting the workflow
     */
    public void execute() throws WorkflowExecutionException {
        logger.info("Workflow execution started."); // Log the start of the workflow

        for (T step : steps) { // Polymorphism: Execute each step through the Step interface
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

    /**
     * Executes a specific step in the workflow with coercion polymorphism.
     *
     * @param step the step to execute
     */
    public void executeSpecificStep(Step step) {
        if (step instanceof SegmentationStep) {
            SegmentationStep segmentationStep = (SegmentationStep) step; // Coercion
            logger.info("Executing specific SegmentationStep logic.");
            segmentationStep.performSegmentation();
        } else {
            try {
                logger.info("Executing generic step logic for: {}", step.getClass().getSimpleName());
                step.execute();
            } catch (StepExecutionException e) {
                logger.error("Error executing specific step: {}. Exception: {}", step.getClass().getSimpleName(), e.getMessage());
            }
        }
    }

    /**
     * Provides a summary of all steps in the workflow.
     *
     * @return A string summarizing the steps and their statuses.
     */
    public String summarizeWorkflow() {
        StringBuilder summary = new StringBuilder("Workflow Summary:\n");
        for (T step : steps) {
            summary.append(step.describeStep()).append("\n");
        }
        return summary.toString();
    }
}
