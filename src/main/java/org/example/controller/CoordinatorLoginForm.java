package org.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.bo.BOFactory;
import org.example.bo.custom.UserBO;
import org.example.dto.UserDTO;
import org.example.util.PasswordEncryptor;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class CoordinatorLoginForm {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField textUsername;
    @FXML
    private PasswordField textPassword;
    @FXML
    private JFXButton btnLogIn;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize() {
        textPassword.setOnAction(event -> {
            btnLogIn.fire();
        });
    }

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

        try {
            List<UserDTO> userList = userBO.getUserByRoleAndUsername(role, username);
            boolean failMeg = true;
            for (UserDTO userDTO : userList) {
                boolean isChecked = PasswordEncryptor.checkPassword(password, userDTO.getPassword());
                if (isChecked) {
                    failMeg = false;
                }
            }

            if (failMeg) {
                new Alert(Alert.AlertType.ERROR,"Login failed").show();
                textUsername.requestFocus();
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Login successfully").show();
                logIn();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    public void logIn() throws IOException {
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

}
