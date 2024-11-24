package com.astrophysics.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class AstrophysicsWorkflowApplication {

    // Logger instance to capture and record application events
    private static final Logger logger = LoggerFactory.getLogger(AstrophysicsWorkflowApplication.class);

    public static void main(String[] args) {
        // Start the Spring Boot application
        SpringApplication.run(AstrophysicsWorkflowApplication.class, args);

        // Log that the application is starting
        logger.info("Astrophysics Workflow Application is starting...");

        try {
            // Initialize all workflow steps (processing, validation, segmentation, detection)
            Step processingStep = new DataProcessingStep(); // Step for data processing
            Step validationStep = new DataValidationStep(); // Step for data validation
            Step segmentationStep = new SegmentationStep(); // Step for image segmentation
            Step detectionStep = new DetectionStep(); // Step for performing detection tasks

            // Combine all steps into a single workflow
            Workflow workflow = new Workflow(Arrays.asList(
                    processingStep,
                    validationStep,
                    segmentationStep,
                    detectionStep // Add detection step to the workflow
            ));
            logger.info("Workflow initialized with processing, validation, segmentation, and detection steps.");

            // Set up orchestration to manage the workflow steps
            Orchestration orchestration = new Orchestration(workflow.getSteps());
            logger.info("Orchestration initialized with the workflow steps.");

            // Start the orchestration and execute each step
            orchestration.start();

            // Log that the application has completed successfully
            logger.info("Astrophysics Workflow Application completed successfully!");

        } catch (OrchestrationException e) {
            // Capture and log any exceptions that occur during orchestration
            logger.error("An error occurred during orchestration: {}", e.getMessage());
            logger.error("Detailed error stack trace: ", e);
        }
    }
}
