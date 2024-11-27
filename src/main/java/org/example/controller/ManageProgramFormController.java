package org.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.ProgramBO;
import org.example.dto.ProgramDTO;
import java.io.IOException;
import java.util.List;


public class ManageProgramFormController {
    @FXML
    private AnchorPane root;
    @FXML
    public TextField textProgramID;
    @FXML
    public TextField textProgramName;
    @FXML
    public TextField textProgramDuration;
    @FXML
    public TextField textProgramFee;
    @FXML
    public JFXButton addProgramBtn;
    @FXML
    public JFXButton deleteProgramBtn;
    @FXML
    public JFXButton updateProgramBtn;
    @FXML
    public TableView<ProgramDTO> tblPrograms;
    @FXML
    private Label textAdminOrCoordinator;

    ProgramBO programBO = (ProgramBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PROGRAM);

    public void setTextAdminOrCoordinator(String text){
        textAdminOrCoordinator.setText(text);
    }

    public void initialize() {
        tblPrograms.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("programId"));
        tblPrograms.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("programName"));
        tblPrograms.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("duration"));
        tblPrograms.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("fee"));

        updateProgramBtn.setDisable(true);
        deleteProgramBtn.setDisable(true);

        tblPrograms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                textProgramID.setText(newValue.getProgramId());
                textProgramName.setText(newValue.getProgramName());
                textProgramDuration.setText(newValue.getDuration());
                textProgramFee.setText(String.valueOf(newValue.getFee()));
                textProgramID.setEditable(false);
                updateProgramBtn.setDisable(false);
                deleteProgramBtn.setDisable(false);
            }
        });

        loadAllCustomer();
    }

    public void loadAllCustomer(){
        tblPrograms.getItems().clear();
        try{
            List<ProgramDTO> allPrograms = programBO.getAll();
            for(ProgramDTO program : allPrograms){
                tblPrograms.getItems().add(new ProgramDTO(program.getProgramId(), program.getProgramName(), program.getDuration(), program.getFee()));
            }
        }
        catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public boolean existProgram( String id) throws Exception {
        return programBO.exist(id);
    }

    public void clearFields() {
        textProgramID.clear();
        textProgramName.clear();
        textProgramDuration.clear();
        textProgramFee.clear();
        updateProgramBtn.setDisable(true);
        deleteProgramBtn.setDisable(true);
    }

    @FXML
    public void addProgramBtnOnAction(ActionEvent actionEvent) {
        String programID = textProgramID.getText();
        String programName = textProgramName.getText();
        String duration = textProgramDuration.getText();
        String fee = textProgramFee.getText();

        if(!programID.matches("CA\\d{4}")){
            new Alert(Alert.AlertType.ERROR,"Invalid ProgramID").show();
            textProgramID.requestFocus();
            return;
        } else if (programName.length()==0) {
            new Alert(Alert.AlertType.ERROR,"Enter Program Name").show();
            textProgramName.requestFocus();
            return;
        } else if (duration.length()==0) {
            new Alert(Alert.AlertType.ERROR,"Invalid Duration").show();
            textProgramDuration.requestFocus();
            return;
        } else if (!fee.matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR,"Invalid Fee").show();
            textProgramFee.requestFocus();
            return;
        }else {

            try{

                if (existProgram(programID)){
                    new Alert(Alert.AlertType.ERROR, programID + " already exists").show();
                    textProgramID.requestFocus();
                }

                programBO.add(new ProgramDTO(programID,programName,duration,Double.parseDouble(fee)));
                new Alert(Alert.AlertType.INFORMATION, "Program Add").show();
                tblPrograms.getItems().add(new ProgramDTO(programID,programName,duration,Double.parseDouble(fee)));
                tblPrograms.refresh();
                clearFields();
                textProgramID.requestFocus();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @FXML
    public void updateProgramBtnOnAction(ActionEvent actionEvent) {

        String programID = textProgramID.getText();
        String programName = textProgramName.getText();
        String duration = textProgramDuration.getText();
        String fee = textProgramFee.getText();

        if(!programID.matches("CA\\d{4}")){
            new Alert(Alert.AlertType.ERROR,"Invalid ProgramID").show();
            textProgramID.requestFocus();
            return;
        } else if (programName.length()==0) {
            new Alert(Alert.AlertType.ERROR,"Enter Program Name").show();
            textProgramName.requestFocus();
            return;
        } else if (duration.length()==0) {
            new Alert(Alert.AlertType.ERROR,"Invalid Duration").show();
            textProgramDuration.requestFocus();
            return;
        } else if (!fee.matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR,"Invalid Fee").show();
            textProgramFee.requestFocus();
            return;
        }else {
            try{
                programBO.update(new ProgramDTO(programID,programName,duration,Double.parseDouble(fee)));
                new Alert(Alert.AlertType.INFORMATION, "Program Updated").show();
                tblPrograms.getSelectionModel().getSelectedItem().setProgramName(programName);
                tblPrograms.getSelectionModel().getSelectedItem().setDuration(duration);
                tblPrograms.getSelectionModel().getSelectedItem().setFee(Double.parseDouble(fee));
                tblPrograms.getSelectionModel().clearSelection();
                tblPrograms.refresh();
                clearFields();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }

    @FXML
    public void deleteProgramBtnOnAction(ActionEvent actionEvent) {
        String programID = textProgramID.getText();

        try {
            programBO.delete(programID);
            new Alert(Alert.AlertType.INFORMATION, "Program Deleted").show();
            tblPrograms.getItems().remove(tblPrograms.getSelectionModel().getSelectedItem());
            tblPrograms.getSelectionModel().clearSelection();
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

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

}
