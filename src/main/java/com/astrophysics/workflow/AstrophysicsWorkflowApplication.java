package com.astrophysics.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class AstrophysicsWorkflowApplication {

    // Initialize SLF4J Logger
    private static final Logger logger = LoggerFactory.getLogger(AstrophysicsWorkflowApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AstrophysicsWorkflowApplication.class, args);

        // Log application startup
        logger.info("Astrophysics Workflow Application is starting...");

        // Initialize the steps
        Step processingStep = new DataProcessingStep();
        Step validationStep = new DataValidationStep();
        Step segmentationStep = new SegmentationStep();

        // Initialize the Workflow with the steps
        Workflow workflow = new Workflow(Arrays.asList(processingStep, validationStep, segmentationStep));
        logger.info("Workflow initialized with processing, validation, and segmentation steps.");

        // Initialize Orchestration with the Workflow
        Orchestration orchestration = new Orchestration(workflow.getSteps());
        logger.info("Orchestration initialized with the workflow steps.");

        // Start the orchestration and log each step
        orchestration.start();

        // Log application running state
        logger.info("Astrophysics Workflow Application is running!");
    }
}
