package com.astrophysics.workflow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A GUI-based workflow editor that allows users to dynamically add tasks
 * to a workflow and execute them.
 */
public class WorkflowEditor extends Application {

    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    // Workflow instance to manage steps dynamically
    private Workflow<Step> workflow = new Workflow<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Astrophysics Workflow Editor");

        // Root layout
        BorderPane root = new BorderPane();

        // Microservice panel with task buttons
        VBox microservicePanel = new VBox(10);

        // Define buttons for each task
        Button segmentationButton = new Button("Add Segmentation Step");
        Button detectionButton = new Button("Add Detection Step");
        Button dataProcessingButton = new Button("Add Data Processing Step");
        Button dataValidationButton = new Button("Add Data Validation Step");

        // Style and tooltips for buttons
        applyButtonStyle(segmentationButton, "Add a new Segmentation task to the workflow");
        applyButtonStyle(detectionButton, "Add a new Detection task to the workflow");
        applyButtonStyle(dataProcessingButton, "Add a new Data Processing task to the workflow");
        applyButtonStyle(dataValidationButton, "Add a new Data Validation task to the workflow");

        // Workflow task list and log area
        ListView<String> workflowList = new ListView<>();
        TextArea statusArea = new TextArea();
        statusArea.setEditable(false);
        statusArea.appendText("Execution Log:\n");

        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setVisible(false);

        // Define button actions
        segmentationButton.setOnAction(event -> addTaskToWorkflow("Segmentation", workflowList, statusArea));
        detectionButton.setOnAction(event -> addTaskToWorkflow("Detection", workflowList, statusArea));
        dataProcessingButton.setOnAction(event -> addTaskToWorkflow("Data Processing", workflowList, statusArea));
        dataValidationButton.setOnAction(event -> addTaskToWorkflow("Data Validation", workflowList, statusArea));

        // Clear Workflow button
        Button clearButton = new Button("Clear Workflow");
        clearButton.setStyle("-fx-font-size: 14px; -fx-background-color: #FF6347; -fx-text-fill: white;");
        clearButton.setTooltip(new Tooltip("Clear all tasks and logs"));
        clearButton.setOnAction(event -> {
            if (confirmClear()) {
                workflowList.getItems().clear();
                statusArea.clear();
                statusArea.appendText("Workflow cleared.\n");
                workflow = new Workflow<>(); // Reset the workflow instance
            }
        });

        // Add buttons to the microservice panel
        microservicePanel.getChildren().addAll(segmentationButton, detectionButton, dataProcessingButton, dataValidationButton, clearButton);

        // Layout configuration
        root.setLeft(microservicePanel); // Place the panel on the left
        VBox taskBox = new VBox(new Label("Workflow Tasks:"), workflowList);
        root.setRight(taskBox); // Place the task list on the right
        VBox logBox = new VBox(new Label("Execution Log:"), statusArea, progressBar);
        root.setBottom(logBox); // Place the log and progress bar at the bottom

        // Set and show the scene
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    /**
     * Adds a task to the workflow dynamically based on the task type.
     * Demonstrates subtyping and polymorphism by treating all tasks as Step objects.
     *
     * @param taskName    The name of the task to add.
     * @param workflowList The list view to display added tasks.
     * @param statusArea   The text area to log operations.
     */
    private void addTaskToWorkflow(String taskName, ListView<String> workflowList, TextArea statusArea) {
        Step step = switch (taskName) {
            case "Segmentation" -> new SegmentationStep(); // Specific task
            case "Detection" -> new DetectionStep();
            case "Data Processing" -> new DataProcessingStep();
            case "Data Validation" -> new DataValidationStep();
            default -> throw new IllegalArgumentException("Unknown task type");
        };

        workflow.addStep(step); // Polymorphism: Adding task as a Step
        workflowList.getItems().add(taskName);
        statusArea.appendText("[" + getCurrentTime() + "] Added " + taskName + " to workflow.\n");
    }

    /**
     * Applies a consistent style and tooltip to buttons.
     *
     * @param button   The button to style.
     * @param tooltip  The tooltip text for the button.
     */
    private void applyButtonStyle(Button button, String tooltip) {
        button.setStyle("-fx-font-size: 14px; -fx-background-color: #4682B4; -fx-text-fill: white;");
        button.setTooltip(new Tooltip(tooltip));
    }

    /**
     * Displays a confirmation dialog for clearing the workflow.
     *
     * @return True if the user confirms, false otherwise.
     */
    private boolean confirmClear() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to clear all tasks and logs?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirm Clear");
        alert.setHeaderText(null);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }

    /**
     * Gets the current time in HH:mm:ss format.
     *
     * @return The formatted current time.
     */
    private String getCurrentTime() {
        return LocalTime.now().format(timeFormatter);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
