package org.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloPageFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton adminBtn;
    @FXML
    private JFXButton coordinatorBtn;

    @FXML
    public void navigate(MouseEvent event) throws IOException {
        if(event.getSource() instanceof JFXButton ) {
        JFXButton btn = (JFXButton) event.getSource();

        Parent root = null;

        switch (btn.getId()){
            case "adminBtn":
                root = FXMLLoader.load(this.getClass().getResource("/view/adminLogin.fxml"));
                break;
            case "coordinatorBtn":
                root = FXMLLoader.load(this.getClass().getResource("/view/coordinatorLogin.fxml"));
                break;
        }

        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);

            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();

        }

        }
    }

}
