package com.astrophysics.workflow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * A GUI-based workflow editor that allows users to dynamically add tasks
 * to a workflow, execute them, visualize steps, export logs, and generate summaries.
 */
public class WorkflowEditor extends Application {

    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private Workflow<Step> workflow = new Workflow<>();
    private boolean isDarkTheme = false; // To toggle between light and dark themes
    private Scene scene;

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
        Button executeButton = new Button("Execute Workflow");
        Button clearButton = new Button("Clear Workflow");
        Button themeToggleButton = new Button("Toggle Theme");
        Button exportLogsButton = new Button("Export Logs");
        Button visualizeButton = new Button("Visualize Workflow");
        Button generateSummaryButton = new Button("Generate Summary");

        // Style and tooltips for buttons
        applyButtonStyle(segmentationButton, "Add a new Segmentation task to the workflow");
        applyButtonStyle(detectionButton, "Add a new Detection task to the workflow");
        applyButtonStyle(dataProcessingButton, "Add a new Data Processing task to the workflow");
        applyButtonStyle(dataValidationButton, "Add a new Data Validation task to the workflow");
        applyButtonStyle(executeButton, "Execute all tasks in the workflow");
        applyButtonStyle(clearButton, "Clear all tasks and logs");
        applyButtonStyle(themeToggleButton, "Toggle between light and dark themes");
        applyButtonStyle(exportLogsButton, "Export the execution logs to a file");
        applyButtonStyle(visualizeButton, "Visualize the workflow sequence");
        applyButtonStyle(generateSummaryButton, "Generate a task summary report");

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
        executeButton.setOnAction(event -> executeWorkflow(workflowList, statusArea, progressBar));
        clearButton.setOnAction(event -> {
            if (confirmClear()) {
                workflowList.getItems().clear();
                statusArea.clear();
                statusArea.appendText("Workflow cleared.\n");
                workflow = new Workflow<>();
                progressBar.setProgress(0);
                progressBar.setVisible(false);
            }
        });
        themeToggleButton.setOnAction(event -> toggleTheme());
        exportLogsButton.setOnAction(event -> exportLogs(statusArea, primaryStage));
        visualizeButton.setOnAction(event -> visualizeWorkflow(workflowList));
        generateSummaryButton.setOnAction(event -> generateSummary(workflowList, statusArea));

        // Add buttons to the microservice panel
        microservicePanel.getChildren().addAll(
                segmentationButton,
                detectionButton,
                dataProcessingButton,
                dataValidationButton,
                executeButton,
                clearButton,
                themeToggleButton,
                exportLogsButton,
                visualizeButton,
                generateSummaryButton
        );

        // Layout configuration
        root.setLeft(microservicePanel);
        VBox taskBox = new VBox(new Label("Workflow Tasks:"), workflowList);
        root.setRight(taskBox);
        VBox logBox = new VBox(new Label("Execution Log:"), statusArea, progressBar);
        root.setBottom(logBox);

        // Set and show the scene
        scene = new Scene(root, 1000, 700);
        applyLightTheme(); // Set the initial theme
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addTaskToWorkflow(String taskName, ListView<String> workflowList, TextArea statusArea) {
        Step step = switch (taskName) {
            case "Segmentation" -> new SegmentationStep();
            case "Detection" -> new DetectionStep();
            case "Data Processing" -> new DataProcessingStep();
            case "Data Validation" -> new DataValidationStep();
            default -> throw new IllegalArgumentException("Unknown task type");
        };
        workflow.addStep(step);
        workflowList.getItems().add(taskName);
        statusArea.appendText("[" + getCurrentTime() + "] Added " + taskName + " to workflow.\n");
    }

    private void executeWorkflow(ListView<String> workflowList, TextArea statusArea, ProgressBar progressBar) {
        if (workflow.getSteps().isEmpty()) {
            statusArea.appendText("[" + getCurrentTime() + "] No tasks to execute.\n");
            return;
        }
        progressBar.setVisible(true);
        progressBar.setProgress(0);
        double stepProgress = 1.0 / workflow.getSteps().size();
        int currentStep = 0;

        for (Step step : workflow.getSteps()) {
            try {
                currentStep++;
                statusArea.appendText("[" + getCurrentTime() + "] Executing: " + step.getClass().getSimpleName() + "\n");
                step.execute();
                progressBar.setProgress(currentStep * stepProgress);
                statusArea.appendText("[" + getCurrentTime() + "] Completed: " + step.getClass().getSimpleName() + "\n");
            } catch (StepExecutionException e) {
                statusArea.appendText("[" + getCurrentTime() + "] Error in step: " + step.getClass().getSimpleName() + " - " + e.getMessage() + "\n");
                break;
            }
        }
        statusArea.appendText("[" + getCurrentTime() + "] Workflow execution finished.\n");
    }

    private void exportLogs(TextArea statusArea, Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Logs");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(statusArea.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Logs successfully saved!", ButtonType.OK);
                alert.showAndWait();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save logs: " + e.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    private void visualizeWorkflow(ListView<String> workflowList) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Workflow Visualization");
        alert.setHeaderText("Workflow Steps:");
        StringBuilder workflowRepresentation = new StringBuilder();
        for (String task : workflowList.getItems()) {
            workflowRepresentation.append("-> ").append(task).append("\n");
        }
        alert.setContentText(workflowRepresentation.toString());
        alert.showAndWait();
    }

    private void generateSummary(ListView<String> workflowList, TextArea statusArea) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Workflow Summary");
        alert.setHeaderText("Task Summary");
        int taskCount = workflowList.getItems().size();
        String summary = "Total Tasks: " + taskCount;
        alert.setContentText(summary);
        alert.showAndWait();
        statusArea.appendText("[" + getCurrentTime() + "] Generated Summary: " + summary + "\n");
    }

    private void applyButtonStyle(Button button, String tooltip) {
        button.setStyle("-fx-font-size: 14px; -fx-background-color: #4682B4; -fx-text-fill: white;");
        button.setTooltip(new Tooltip(tooltip));
    }

    private boolean confirmClear() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to clear all tasks and logs?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirm Clear");
        alert.setHeaderText(null);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }

    private void toggleTheme() {
        if (isDarkTheme) {
            applyLightTheme();
        } else {
            applyDarkTheme();
        }
        isDarkTheme = !isDarkTheme;
    }

    private void applyLightTheme() {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/light-theme.css")).toExternalForm());
    }

    private void applyDarkTheme() {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/dark-theme.css")).toExternalForm());
    }

    private String getCurrentTime() {
        return LocalTime.now().format(timeFormatter);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
