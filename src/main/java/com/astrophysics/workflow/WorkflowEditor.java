package com.astrophysics.workflow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WorkflowEditor extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Astrophysics Workflow Editor");

        // Create the root layout
        BorderPane root = new BorderPane();

        // Create the microservice panel (for segmentation, detection, data processing, and data validation buttons)
        VBox microservicePanel = new VBox(10);

        // Define buttons for each task
        Button segmentationButton = new Button("Segmentation");
        Button detectionButton = new Button("Detection");
        Button dataProcessingButton = new Button("Data Processing");
        Button dataValidationButton = new Button("Data Validation");

        // Set tooltips for guidance
        segmentationButton.setTooltip(new Tooltip("Start a new segmentation task"));
        detectionButton.setTooltip(new Tooltip("Start a new detection task"));
        dataProcessingButton.setTooltip(new Tooltip("Start a new data processing task"));
        dataValidationButton.setTooltip(new Tooltip("Start a new data validation task"));

        // Add basic styling
        String buttonStyle = "-fx-font-size: 14px; -fx-background-color: #87CEFA;";
        segmentationButton.setStyle(buttonStyle);
        detectionButton.setStyle(buttonStyle);
        dataProcessingButton.setStyle(buttonStyle);
        dataValidationButton.setStyle(buttonStyle);

        // Create workflow task list and status log area
        ListView<String> workflowList = new ListView<>();
        TextArea statusArea = new TextArea();
        statusArea.setEditable(false);
        statusArea.appendText("Execution Log:\n");

        // Define action events for each button
        segmentationButton.setOnAction(event -> {
            workflowList.getItems().add("Segmentation Task");
            statusArea.appendText("Segmentation started...\n");
            statusArea.appendText("Segmentation completed and added to workflow.\n");
        });

        detectionButton.setOnAction(event -> {
            workflowList.getItems().add("Detection Task");
            statusArea.appendText("Detection started...\n");
            statusArea.appendText("Detection completed and added to workflow.\n");
        });

        dataProcessingButton.setOnAction(event -> {
            workflowList.getItems().add("Data Processing Task");
            statusArea.appendText("Data Processing started...\n");
            statusArea.appendText("Data Processing completed and added to workflow.\n");
        });

        dataValidationButton.setOnAction(event -> {
            workflowList.getItems().add("Data Validation Task");
            statusArea.appendText("Data Validation started...\n");
            statusArea.appendText("Data Validation completed and added to workflow.\n");
        });

        // Create a Clear Workflow button to reset the list and log
        Button clearButton = new Button("Clear Workflow");
        clearButton.setTooltip(new Tooltip("Clear all tasks and log"));
        clearButton.setStyle("-fx-font-size: 12px; -fx-background-color: #FFA07A;");

        clearButton.setOnAction(event -> {
            workflowList.getItems().clear();
            statusArea.clear();
            statusArea.appendText("Workflow cleared.\n");
        });

        // Add buttons to the panel
        microservicePanel.getChildren().addAll(segmentationButton, detectionButton, dataProcessingButton, dataValidationButton, clearButton);

        // Place the microservice panel on the left side of the BorderPane
        root.setLeft(microservicePanel);

        // Create the task list area on the right
        VBox taskBox = new VBox(new Label("Workflow Tasks:"), workflowList);
        root.setRight(taskBox);

        // Place the log area at the bottom
        VBox logBox = new VBox(new Label("Execution Log:"), statusArea);
        root.setBottom(logBox);

        // Set the scene and show the stage
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
