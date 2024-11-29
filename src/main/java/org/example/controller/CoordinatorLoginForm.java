package org.example.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.bo.BOFactory;
import org.example.bo.custom.UserBO;

import java.io.IOException;
import java.net.URL;

public class CoordinatorLoginForm {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField textUsername;
    @FXML
    private PasswordField textPassword;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    public void navigateHome(MouseEvent event) throws IOException {
        root.getChildren().clear();
        URL resource = this.getClass().getResource("/view/helloPage.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
    }
    @FXML
    public void btnLogInOnAction() throws IOException {

        String username = textUsername.getText();
        String password = textPassword.getText();
        String role = "coordinator";



        root.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/dashBoard.fxml"));
        Parent root = loader.load();
        DashboardFormController dashboardFormController = loader.getController();
        dashboardFormController.setTextAdminOrCoordinator("Coordinator");
        Scene scene = new Scene(root);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);

        TranslateTransition tt = new TranslateTransition(Duration.millis(350), scene.getRoot());
        tt.setFromX(-scene.getWidth());
        tt.setToX(0);
        tt.play();
    }
    @FXML
    public void btnCreateOneOnAction(ActionEvent actionEvent) throws IOException {
        root.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/signUpForm.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);

        TranslateTransition tt = new TranslateTransition(Duration.millis(350), scene.getRoot());
        tt.setFromX(-scene.getWidth());
        tt.setToX(0);
        tt.play();
    }
}
