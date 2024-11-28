package org.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;

public class DashboardFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private Label textAdminOrCoordinator;
    @FXML
    private JFXButton btnManageStudent;
    @FXML
    private JFXButton btnManageCourse;
    @FXML
    private JFXButton btnManageCoordinator;
    @FXML
    private JFXButton btnManagePayment;
    @FXML
    private JFXButton btnGetStudentRegisterInProgram;

    public void setTextAdminOrCoordinator(String text){
        textAdminOrCoordinator.setText(text);
        initUI(text);
    }

    private void initUI(String text) {

        if(text.equalsIgnoreCase("Coordinator")){
            btnManageStudent.setDisable(false);
            btnManageCourse.setDisable(false);
            btnManageCoordinator.setDisable(true);
            btnManagePayment.setDisable(true);
            btnGetStudentRegisterInProgram.setDisable(false);
        }
    }

    public void signOutOnAction() throws IOException {
        root.getChildren().clear();
        URL resource = this.getClass().getResource("/view/helloPage.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
    }

    public void navigate(MouseEvent event) throws IOException {
        if(event.getSource() instanceof JFXButton){
            JFXButton btn = (JFXButton) event.getSource();

            Parent root = null;

            switch (btn.getId()){
                case "btnManageStudent":
                    FXMLLoader loader0 = new FXMLLoader(this.getClass().getResource("/view/manage_files/manageStudent.fxml"));
                    root = loader0.load();
                    ManageStudentFormController manageStudentFormController = loader0.getController();
                    manageStudentFormController.setTextAdminOrCoordinator(textAdminOrCoordinator.getText());
                    break;
                case "btnGetStudentRegisterInProgram":
                    FXMLLoader loader3 = new FXMLLoader(this.getClass().getResource("/view/manage_files/getStudentRegisterProgram.fxml"));
                    root = loader3.load();
                    GetStudentRegisterProgramController gsrpc = loader3.getController();
                    gsrpc.setTextAdminOrCoordinator(textAdminOrCoordinator.getText());
                    break;
                case "btnManageCourse":
                    FXMLLoader loader2 = new FXMLLoader(this.getClass().getResource("/view/manage_files/manageProgram.fxml"));
                    root = loader2.load();
                    ManageProgramFormController mpfc = loader2.getController();
                    mpfc.setTextAdminOrCoordinator(textAdminOrCoordinator.getText());
                    break;
            }

            if(root != null){
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
