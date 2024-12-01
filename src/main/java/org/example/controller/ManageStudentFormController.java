package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.RegisterBO;
import org.example.bo.custom.StudentBO;
import org.example.custom_exception.DataNotFoundException;
import org.example.dto.StudentDTO;
import org.example.dto.StudentProgramDetailsDTO;
import org.example.dto.tm.StudentTM;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ManageStudentFormController {
    @FXML
    private Label textAdminOrCoordinator;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField sPhoneNumber;
    @FXML
    private TextField sName;
    @FXML
    private TextField dateOfBirth;
    @FXML
    private TextField address;
    @FXML
    private TextField registrationDate;
    @FXML
    private TextField uPhoneNumber;
    @FXML
    private JFXButton registerBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private TableView<StudentTM> tblStudents;
    @FXML
    private JFXCheckBox CA1001;
    @FXML
    private JFXCheckBox CA1002;
    @FXML
    private JFXCheckBox CA1003;
    @FXML
    private JFXCheckBox CA1004;
    @FXML
    private JFXCheckBox CA1005;
    @FXML
    private TextField CA1001Fee;
    @FXML
    private TextField CA1002Fee;
    @FXML
    private TextField CA1003Fee;
    @FXML
    private TextField CA1004Fee;
    @FXML
    private TextField CA1005Fee;

    RegisterBO registerBO = (RegisterBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTERED);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void setTextAdminOrCoordinator(String text){
        textAdminOrCoordinator.setText(text);
    }

    public void setDate(){
        LocalDate today = LocalDate.now();
        registrationDate.setText(String.valueOf(today));
    }

    public void initialize(){
        setDate();
        registrationDate.setEditable(false);
        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("sPhoneNumber"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        tblStudents.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudents.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);

        tblStudents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                sPhoneNumber.setText(newValue.getSPhoneNumber());
                sName.setText(newValue.getName());
                dateOfBirth.setText(newValue.getDateOfBirth());
                address.setText(newValue.getAddress());
                registrationDate.setText(newValue.getRegistrationDate());
                registrationDate.setEditable(false);
                sPhoneNumber.setEditable(false);
                updateBtn.setDisable(false);
                deleteBtn.setDisable(false);
                uPhoneNumber.setEditable(false);
            }
        });

        loadAllStudent();
    }

    public void loadAllStudent() {
        tblStudents.getItems().clear();
        try{
            List<StudentDTO> allStudent = studentBO.getAll();
            for(StudentDTO student : allStudent){
                StudentTM studentTM = new StudentTM(student.getSPhoneNumber(),student.getName(),student.getDateOfBirth(),student.getAddress(),student.getRegistrationDate());
                tblStudents.getItems().add(studentTM);
                uPhoneNumber.setText(student.getUPhoneNumber());
            }

        }
        catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "errror").show();
        }

    }

    public void clearFields(){
        sPhoneNumber.clear();
        sName.clear();
        dateOfBirth.clear();
        address.clear();
        uPhoneNumber.clear();
        CA1001Fee.clear();
        CA1002Fee.clear();
        CA1003Fee.clear();
        CA1004Fee.clear();
        CA1005Fee.clear();
        sPhoneNumber.requestFocus();
        CA1001.setSelected(false);
        CA1002.setSelected(false);
        CA1003.setSelected(false);
        CA1004.setSelected(false);
        CA1005.setSelected(false);
        sPhoneNumber.setEditable(true);
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
        uPhoneNumber.setEditable(true);
    }

    @FXML
    public void navigateHome(MouseEvent event) throws IOException {
        root.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/dashBoard.fxml"));
        Parent root = loader.load();
        DashboardFormController dashboardFormController = loader.getController();
        dashboardFormController.setTextAdminOrCoordinator(textAdminOrCoordinator.getText());
        Scene scene = new Scene(root);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    public void registerOnAction(ActionEvent actionEvent) {

        LinkedList<String> programCode =  new LinkedList();
        LinkedList<Double> registerFee =  new LinkedList();

        String sNumber = sPhoneNumber.getText();
        String name = sName.getText();
        String birthday = dateOfBirth.getText();
        String sAddress = address.getText();
        String sRegistrationDate = registrationDate.getText();
        String uNumber = uPhoneNumber.getText();

        if (CA1001.isSelected()) {
            if (CA1001Fee.getText() == null || CA1001Fee.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Enter Register fee").show();
                CA1001Fee.requestFocus();
            } else {
                programCode.add("CA1001");
                registerFee.add(Double.parseDouble(CA1001Fee.getText()));
            }
        }if(CA1002.isSelected()) {
            if (CA1002Fee.getText() == null || CA1002Fee.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Enter Register fee").show();
                CA1002Fee.requestFocus();
            }else {
                programCode.add("CA1002");
                registerFee.add(Double.parseDouble(CA1002Fee.getText()));
            }
        }if(CA1003.isSelected()) {
            if (CA1003Fee.getText() == null || CA1003Fee.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Enter Register fee").show();
                CA1003Fee.requestFocus();
            }else {
                programCode.add("CA1003");
                registerFee.add(Double.parseDouble(CA1003Fee.getText()));
            }
        }if(CA1004.isSelected()) {
            if (CA1004Fee.getText() == null || CA1004Fee.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Enter Register fee").show();
                CA1004Fee.requestFocus();
            }else {
                programCode.add("CA1004");
                registerFee.add(Double.parseDouble(CA1004Fee.getText()));
            }
        }if(CA1005.isSelected()) {
            if (CA1005Fee.getText() == null || CA1005Fee.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Enter Register fee").show();
                CA1005Fee.requestFocus();
            }else {
                programCode.add("CA1005");
                registerFee.add(Double.parseDouble(CA1005Fee.getText()));
            }
        }


        if(!sNumber.matches( "^07\\d{8}$")){
            new Alert(Alert.AlertType.ERROR,"Invalid Mobile number").show();
            sPhoneNumber.requestFocus();
            return;
        } else if (name.length()==0) {
            new Alert(Alert.AlertType.ERROR,"Enter Student name").show();
            sName.requestFocus();
            return;
        } else if (!birthday.matches("(\\d{4})-(\\d{2})-(\\d{2})")) {
            new Alert(Alert.AlertType.ERROR,"Invalid birthday").show();
            dateOfBirth.requestFocus();
            return;
        } else if (sAddress.length()==0) {
            new Alert(Alert.AlertType.ERROR,"Enter Student address").show();
            address.requestFocus();
            return;
        } else if (!uNumber.matches( "^07\\d{8}$")) {
            new Alert(Alert.AlertType.ERROR,"Invalid Mobile number").show();
            uPhoneNumber.requestFocus();
            return;
        }else {
            try{

                if(studentBO.exist(sNumber)){
                    new Alert(Alert.AlertType.ERROR, sNumber + " already exists").show();
                    sPhoneNumber.requestFocus();
                }

                ArrayList<StudentProgramDetailsDTO> courseDetailsList = new ArrayList();
                StudentDTO studentDTO = new StudentDTO(sNumber,name,birthday,sAddress,sRegistrationDate,uNumber);

                for(int i = 0; i<programCode.size(); i++){
                    StudentProgramDetailsDTO studentProgramDetailsDTO
                            = new StudentProgramDetailsDTO(0,registerFee.get(i),sNumber,programCode.get(i));
                    courseDetailsList.add(studentProgramDetailsDTO);
                }

                if(registerBO.register(studentDTO,courseDetailsList)){
                    new Alert(Alert.AlertType.INFORMATION, "Register success").show();
                    tblStudents.getItems().add(new StudentTM(sNumber,name,birthday,sAddress,sRegistrationDate));
                    tblStudents.refresh();
                    clearFields();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Register failed").show();
                    sPhoneNumber.requestFocus();
                }

            }catch (DataNotFoundException e){
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                uPhoneNumber.requestFocus();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }

    @FXML
    public void updateOnAction(ActionEvent actionEvent) {
        String sNumber = sPhoneNumber.getText();
        String name = sName.getText();
        String birthday = dateOfBirth.getText();
        String sAddress = address.getText();
        String sRegistrationDate = registrationDate.getText();
        String uNumber = uPhoneNumber.getText();




    }

    @FXML
    public void deleteOnAction(ActionEvent actionEvent) {
        String number = sPhoneNumber.getText();
        try {
            studentBO.delete(number);
            new Alert(Alert.AlertType.INFORMATION, "Student Deleted").show();
            tblStudents.getItems().remove(tblStudents.getSelectionModel().getSelectedItem());
            tblStudents.getSelectionModel().clearSelection();
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

}
