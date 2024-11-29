package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.UserBO;
import org.example.dto.UserDTO;
import org.example.util.PasswordEncryptor;
import java.io.IOException;
import java.net.URL;

public class SignUpFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnCreateAcc;
    @FXML
    private TextField textPhoneNumber;
    @FXML
    private TextField textUsername;
    @FXML
    private TextField textPassword;
    @FXML
    private TextField textAddress;
    @FXML
    private JFXCheckBox checkboxAdmin;
    @FXML
    private JFXCheckBox checkboxCoordinator;
    @FXML
    private JFXButton btnBack;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize() {
        textAddress.setOnAction(event -> {
            btnCreateAcc.fire();
        });
    }

    private void clearFields() {
        textPhoneNumber.clear();
        textUsername.clear();
        textPassword.clear();
        textAddress.clear();
        checkboxAdmin.setSelected(false);
        checkboxCoordinator.setSelected(false);
    }

    public boolean existUser(String id) throws Exception {
        return userBO.exist(id);
    }

    @FXML
    public void btnCreateAccOnAction(ActionEvent actionEvent) {

        String phoneNumber = textPhoneNumber.getText();
        String username = textUsername.getText();
        String password = textPassword.getText();
        String address = textAddress.getText();
        String role =  null;

        if (checkboxAdmin.isSelected() && checkboxCoordinator.isSelected()) {
            new Alert(Alert.AlertType.ERROR, "Please select only one role").show();
            checkboxAdmin.setSelected(false);
            checkboxCoordinator.setSelected(false);
            return;
        } else if (checkboxCoordinator.isSelected()) {
            role = "Coordinator";
        }else if (checkboxAdmin.isSelected()) {
            role = "Admin";
        }

        if(!phoneNumber.matches( "^07\\d{8}$")){
            new Alert(Alert.AlertType.ERROR,"Invalid Mobile number").show();
            textPhoneNumber.requestFocus();
            return;
        } else if (!username.matches( "^[a-z]{4,}$")) {
            new Alert(Alert.AlertType.ERROR,"Invalid Username").show();
            textUsername.requestFocus();
            return;
        } else if (!password.matches(  "^(?=.*[0-9])(?=.*[a-z]).{6,}$")) {
            new Alert(Alert.AlertType.ERROR,"Invalid Password : required -> \"123abc\" ").show();
            textPassword.requestFocus();
            return;
        } else if (address.length()==0) {
            new Alert(Alert.AlertType.ERROR,"Enter your Address").show();
            textAddress.requestFocus();
            return;
        }else if (role == null) {
            new Alert(Alert.AlertType.ERROR, "Please select your role").show();
            return;
        }else{
            String securePassword = PasswordEncryptor.encryptPassword(password);
            try {
                if(existUser(phoneNumber)) {
                    new Alert(Alert.AlertType.ERROR, phoneNumber + " already exists").show();
                    textPhoneNumber.requestFocus();
                }
                userBO.add(new UserDTO(phoneNumber,username,securePassword,address,role));
                new Alert(Alert.AlertType.INFORMATION, "Created successfully and Login").show();
                clearFields();
                btnBack.fire();
            } catch (Exception e) {
//                throw new RuntimeException(e);
                new Alert(Alert.AlertType.ERROR, "Created unsuccessfully").show();
            }
        }

    }

    @FXML
    public void navigateHome(ActionEvent actionEvent) throws IOException {
        root.getChildren().clear();
        URL resource = this.getClass().getResource("/view/helloPage.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
    }

}
