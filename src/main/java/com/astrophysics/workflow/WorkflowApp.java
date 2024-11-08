package com.astrophysics.workflow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WorkflowApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Ensure the path to the FXML file is correct
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/WorkflowInterface.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Astrophysics Workflow Engine");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
