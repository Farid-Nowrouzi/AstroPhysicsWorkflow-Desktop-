package com.astrophysics.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Orchestration {
    private static final Logger logger = LoggerFactory.getLogger(Orchestration.class);
    private List<Step> steps;

    public Orchestration(List<Step> steps) {
        this.steps = steps;
    }

    public void start() {
        logger.info("Orchestration started.");

        for (Step step : steps) {
            try {
                logger.info("Starting execution of step: {}", step.getClass().getSimpleName());
                step.execute();
                logger.info("Finished execution of step: {}", step.getClass().getSimpleName());
            } catch (StepExecutionException e) {
                logger.error("Error in step: {}", e.getMessage());
            }
        }

        logger.info("Orchestration completed.");
    }

    public List<Step> getSteps() {
        return steps;
    }
}
