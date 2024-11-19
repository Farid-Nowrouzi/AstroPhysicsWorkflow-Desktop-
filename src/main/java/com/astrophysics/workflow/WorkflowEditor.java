package com.astrophysics.workflow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * WorkflowEditor is a JavaFX-based application that provides a graphical interface
 * for managing and orchestrating astrophysical workflows. Users can add tasks,
 * view their progress, and clear workflows using an intuitive interface.
 */
public class WorkflowEditor extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Set the title of the application window
        primaryStage.setTitle("Astrophysics Workflow Editor");

        // Create the root layout using BorderPane
        BorderPane root = new BorderPane();

        // Create the left panel with buttons for microservices
        VBox microservicePanel = new VBox(10); // VBox with spacing between buttons

        // Define buttons for available tasks
        Button segmentationButton = new Button("Segmentation");
        Button detectionButton = new Button("Detection");
        Button dataProcessingButton = new Button("Data Processing");
        Button dataValidationButton = new Button("Data Validation");

        // Add tooltips to each button for guidance
        segmentationButton.setTooltip(new Tooltip("Start a new segmentation task"));
        detectionButton.setTooltip(new Tooltip("Start a new detection task"));
        dataProcessingButton.setTooltip(new Tooltip("Start a new data processing task"));
        dataValidationButton.setTooltip(new Tooltip("Start a new data validation task"));

        // Style the buttons for better user experience
        String buttonStyle = "-fx-font-size: 14px; -fx-background-color: #87CEFA;";
        segmentationButton.setStyle(buttonStyle);
        detectionButton.setStyle(buttonStyle);
        dataProcessingButton.setStyle(buttonStyle);
        dataValidationButton.setStyle(buttonStyle);

        // Create the center area for displaying workflow tasks
        ListView<String> workflowList = new ListView<>();

        // Create the bottom area for displaying execution logs
        TextArea statusArea = new TextArea();
        statusArea.setEditable(false); // Make the log area read-only
        statusArea.appendText("Execution Log:\n");

        // Add event handlers to buttons to simulate task execution
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

        // Add a clear button to reset the workflow and log areas
        Button clearButton = new Button("Clear Workflow");
        clearButton.setTooltip(new Tooltip("Clear all tasks and log"));
        clearButton.setStyle("-fx-font-size: 12px; -fx-background-color: #FFA07A;");

        clearButton.setOnAction(event -> {
            workflowList.getItems().clear(); // Clear the task list
            statusArea.clear(); // Clear the log area
            statusArea.appendText("Workflow cleared.\n");
        });

        // Add all buttons to the microservice panel
        microservicePanel.getChildren().addAll(
                segmentationButton,
                detectionButton,
                dataProcessingButton,
                dataValidationButton,
                clearButton
        );

        // Position elements within the BorderPane layout
        root.setLeft(microservicePanel); // Microservices on the left
        root.setRight(new VBox(new Label("Workflow Tasks:"), workflowList)); // Tasks on the right
        root.setBottom(new VBox(new Label("Execution Log:"), statusArea)); // Logs at the bottom

        // Set the main scene with defined dimensions and display the stage
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    /**
     * The main method launches the JavaFX application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        launch(args); // Launch the application
    }
}
