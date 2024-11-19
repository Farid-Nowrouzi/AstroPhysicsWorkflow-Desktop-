package com.astrophysics.workflow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point for the Astrophysics Workflow application.
 * This class extends the JavaFX Application class to initialize and display the graphical user interface (GUI).
 */
public class WorkflowApp extends Application {

    /**
     * Starts the JavaFX application by setting up the primary stage and loading the FXML layout.
     *
     * @param primaryStage the main stage (window) for the application
     * @throws Exception if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML layout file for the application's interface
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/WorkflowInterface.fxml"));
        Parent root = loader.load(); // Load the UI layout

        // Set the title of the main application window
        primaryStage.setTitle("Astrophysics Workflow Engine");

        // Set the scene with the root layout and define its size
        primaryStage.setScene(new Scene(root, 800, 600));

        // Display the main application window
        primaryStage.show();
    }

    /**
     * The main method to launch the JavaFX application.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}
