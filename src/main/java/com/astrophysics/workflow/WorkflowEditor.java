package com.astrophysics.workflow;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class WorkflowEditor extends Application {

    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

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
        String buttonStyle = "-fx-font-size: 14px; -fx-background-color: #87CEFA; -fx-text-fill: white;";
        segmentationButton.setStyle(buttonStyle);
        detectionButton.setStyle(buttonStyle);
        dataProcessingButton.setStyle(buttonStyle);
        dataValidationButton.setStyle(buttonStyle);

        // Create workflow task list and status log area
        ListView<String> workflowList = new ListView<>();
        TextArea statusArea = new TextArea();
        statusArea.setEditable(false);
        statusArea.appendText("Execution Log:\n");

        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setVisible(false);

        // Define action events for each button with enhanced task simulation
        segmentationButton.setOnAction(event -> startTask("Segmentation Task", workflowList, statusArea, progressBar));
        detectionButton.setOnAction(event -> startTask("Detection Task", workflowList, statusArea, progressBar));
        dataProcessingButton.setOnAction(event -> startTask("Data Processing Task", workflowList, statusArea, progressBar));
        dataValidationButton.setOnAction(event -> startTask("Data Validation Task", workflowList, statusArea, progressBar));

        // Create a Clear Workflow button to reset the list and log
        Button clearButton = new Button("Clear Workflow");
        clearButton.setTooltip(new Tooltip("Clear all tasks and log"));
        clearButton.setStyle("-fx-font-size: 12px; -fx-background-color: #FFA07A;");

        clearButton.setOnAction(event -> {
            if (confirmClear()) {
                workflowList.getItems().clear();
                statusArea.clear();
                statusArea.appendText("Workflow cleared.\n");
            }
        });

        // Add buttons to the panel
        microservicePanel.getChildren().addAll(segmentationButton, detectionButton, dataProcessingButton, dataValidationButton, clearButton);

        // Place the microservice panel on the left side of the BorderPane
        root.setLeft(microservicePanel);

        // Create the task list area on the right
        VBox taskBox = new VBox(new Label("Workflow Tasks:"), workflowList);
        root.setRight(taskBox);

        // Place the log area and progress bar at the bottom
        VBox logBox = new VBox(new Label("Execution Log:"), statusArea, progressBar);
        root.setBottom(logBox);

        // Set the scene and show the stage
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
    private void startTask(String taskName, ListView<String> workflowList, TextArea statusArea, ProgressBar progressBar) {
        workflowList.getItems().add(taskName);
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                updateProgress(0, 1);
                updateMessage("[" + getCurrentTime() + "] " + taskName + " started...\n");
                Thread.sleep(2000); // Simulate work with delay

                updateProgress(0.5, 1);
                Thread.sleep(1000);

                updateProgress(1, 1);
                updateMessage("[" + getCurrentTime() + "] " + taskName + " completed and added to workflow.\n");
                return null;
            }
        };

        // Show progress bar during task execution
        progressBar.setVisible(true);
        progressBar.progressProperty().bind(task.progressProperty());
        statusArea.textProperty().unbind();
        statusArea.textProperty().bind(task.messageProperty());

        task.setOnSucceeded(event -> {
            progressBar.setVisible(false); // Hide progress bar after task completes
            statusArea.textProperty().unbind();
        });

        new Thread(task).start();
    }

    private String getCurrentTime() {
        return LocalTime.now().format(timeFormatter);
    }

    private boolean confirmClear() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to clear all tasks and logs?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirm Clear");
        alert.setHeaderText(null);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }

    public static void main(String[] args) {
        launch(args);
    }
}