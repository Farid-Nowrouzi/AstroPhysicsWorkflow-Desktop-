package com.astrophysics.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Workflow {
    private static final Logger logger = LoggerFactory.getLogger(Workflow.class);
    private List<Step> steps;

    public Workflow(List<Step> steps) {
        this.steps = steps;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void execute() {
        logger.info("Workflow execution started.");

        for (Step step : steps) {
            try {
                logger.info("Executing step: {}", step.getClass().getSimpleName());
                step.execute();
                logger.info("Completed step: {}", step.getClass().getSimpleName());
            } catch (StepExecutionException e) {
                logger.error("Error executing step: {}", e.getMessage());
            }
        }

        logger.info("Workflow execution completed.");
    }
}
