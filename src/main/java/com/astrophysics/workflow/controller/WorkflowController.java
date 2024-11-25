package com.astrophysics.workflow.controller;

import com.astrophysics.workflow.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for managing the user interface interaction for workflow creation and management.
 * Handles drag-and-drop functionality for adding microservices to the workflow pane and backend integration.
 */
public class WorkflowController {

    @FXML
    private VBox microserviceList; // A vertical box containing the available microservices as buttons.

    @FXML
    private Pane workflowPane; // The area where microservices can be dragged and dropped to form a workflow.

    @FXML
    private ListView<String> workflowStatusList; // A list to display the current workflow steps.

    private Workflow<Step> workflow = new Workflow<>(); // Backend workflow object.

    // Map to associate button labels with Step implementations
    private final Map<String, Class<? extends Step>> stepMappings = new HashMap<>();

    /**
     * Initializes the controller. Sets up drag-and-drop functionality and backend integration.
     */
    @FXML
    public void initialize() {
        initializeStepMappings();

        workflowPane.setOnDragOver(event -> {
            if (event.getGestureSource() != workflowPane) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        workflowPane.setOnDragDropped(event -> {
            Button draggedMicroservice = (Button) event.getGestureSource();
            String microserviceName = draggedMicroservice.getText();

            try {
                Step newStep = createStepFromName(microserviceName);
                workflow.addStep(newStep); // Add to backend workflow
                workflowStatusList.getItems().add(microserviceName); // Update UI workflow status
            } catch (Exception e) {
                showError("Error adding step: " + e.getMessage());
            }

            event.setDropCompleted(true);
            event.consume();
        });

        for (int i = 0; i < microserviceList.getChildren().size(); i++) {
            if (microserviceList.getChildren().get(i) instanceof Button) {
                Button button = (Button) microserviceList.getChildren().get(i);
                button.setOnDragDetected(event -> {
                    button.startDragAndDrop(TransferMode.COPY_OR_MOVE);
                    event.consume();
                });
            }
        }
    }

    /**
     * Initializes the mapping of button labels to Step subclasses.
     */
    private void initializeStepMappings() {
        stepMappings.put("Segmentation", SegmentationStep.class);
        stepMappings.put("Detection", DetectionStep.class);
        stepMappings.put("Data Processing", DataProcessingStep.class);
        stepMappings.put("Data Validation", DataValidationStep.class);
    }

    /**
     * Creates a Step instance based on the microservice name.
     *
     * @param name The name of the microservice.
     * @return A new Step instance corresponding to the microservice.
     * @throws Exception If the step cannot be instantiated.
     */
    private Step createStepFromName(String name) throws Exception {
        Class<? extends Step> stepClass = stepMappings.get(name);
        if (stepClass != null) {
            return stepClass.getDeclaredConstructor().newInstance();
        } else {
            throw new IllegalArgumentException("Unknown step: " + name);
        }
    }

    /**
     * Displays an error message in a dialog.
     *
     * @param message The error message to display.
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
