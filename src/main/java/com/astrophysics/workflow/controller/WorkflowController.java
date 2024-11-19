package com.astrophysics.workflow.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Controller for managing the user interface interaction for workflow creation and management.
 * Handles drag-and-drop functionality for adding microservices to the workflow pane.
 */
public class WorkflowController {

    @FXML
    private VBox microserviceList; // A vertical box containing the available microservices as buttons.

    @FXML
    private Pane workflowPane; // The area where microservices can be dragged and dropped to form a workflow.

    /**
     * Initializes the controller. This method is automatically called after the FXML elements
     * are loaded. Sets up drag-and-drop functionality and other interactive behaviors.
     */
    @FXML
    public void initialize() {
        // Set up drag-over behavior for the workflow pane
        workflowPane.setOnDragOver(event -> {
            // Allow drag-and-drop only if the event source is not the workflow pane itself
            if (event.getGestureSource() != workflowPane) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE); // Accept copy or move operations
            }
            event.consume(); // Consume the event to prevent further propagation
        });

        // Set up drop behavior for the workflow pane
        workflowPane.setOnDragDropped(event -> {
            // Get the dragged button and create a new button at the drop location
            Button draggedMicroservice = (Button) event.getGestureSource();
            Button newNode = new Button(draggedMicroservice.getText());

            // Position the new button at the drop location
            newNode.setLayoutX(event.getX());
            newNode.setLayoutY(event.getY());
            workflowPane.getChildren().add(newNode); // Add the new button to the workflow pane

            // Mark the drop event as completed
            event.setDropCompleted(true);
            event.consume(); // Consume the event to prevent further propagation
        });

        // Enable drag detection for each button in the microservice list
        for (int i = 0; i < microserviceList.getChildren().size(); i++) {
            if (microserviceList.getChildren().get(i) instanceof Button) { // Ensure it's a Button
                Button button = (Button) microserviceList.getChildren().get(i);
                button.setOnDragDetected(event -> {
                    // Start a drag-and-drop gesture
                    button.startDragAndDrop(TransferMode.COPY_OR_MOVE);
                    event.consume(); // Consume the event to prevent further propagation
                });
            }
        }
    }
}
