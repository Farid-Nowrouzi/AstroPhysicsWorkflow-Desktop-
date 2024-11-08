package com.astrophysics.workflow.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class WorkflowController {

    @FXML
    private VBox microserviceList;

    @FXML
    private Pane workflowPane;

    @FXML
    public void initialize() {
        // Ensure that drag and drop is enabled on the workflow pane
        workflowPane.setOnDragOver(event -> {
            if (event.getGestureSource() != workflowPane) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        workflowPane.setOnDragDropped(event -> {
            // Create a new button on drop at the specified coordinates
            Button draggedMicroservice = (Button) event.getGestureSource();
            Button newNode = new Button(draggedMicroservice.getText());

            newNode.setLayoutX(event.getX());
            newNode.setLayoutY(event.getY());
            workflowPane.getChildren().add(newNode);

            event.setDropCompleted(true);
            event.consume();
        });

        // Add drag-detection for each button in the microservice list
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
}
