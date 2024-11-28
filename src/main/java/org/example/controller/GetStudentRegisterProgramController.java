package org.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.dao.custom.QueryDAO;
import org.example.dao.custom.impl.QueryDAOImpl;
import org.example.dto.ProgramDTO;
import org.example.dto.tm.ProgramDetailsTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GetStudentRegisterProgramController {

    @FXML
    private AnchorPane root;
    @FXML
    private Label textAdminOrCoordinator;
    @FXML
    private TextField textStudentNumber;
    @FXML
    private TextField textStudentName;
    @FXML
    private TableView<ProgramDetailsTM> tblStProgramDetails;
    @FXML
    private JFXButton btnSearch;

    QueryDAO queryDAO = new QueryDAOImpl();

    public void setTextAdminOrCoordinator(String text){
        textAdminOrCoordinator.setText(text);
    }

    public void initialize(){
        tblStProgramDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("program_id"));
        tblStProgramDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("program_name"));
        tblStProgramDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("duration"));
        tblStProgramDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("fee"));
        tblStProgramDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("registerFee"));

        textStudentNumber.setOnAction(event -> {
            btnSearch.fire();
        });

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
    public void btnSearchOnAction(ActionEvent actionEvent) {
        String number = textStudentNumber.getText();
        try {
            List<Object[]> detailsList =  queryDAO.getStudentProgramDetail(number);
            tblStProgramDetails.getItems().clear();
            for (Object[] o : detailsList) {
                textStudentName.setText(o[0].toString());
                ProgramDetailsTM programDetailsTM = new ProgramDetailsTM(
                        o[1].toString(),
                        o[2].toString(),
                        o[4].toString(),
                        (double) o[3],
                        (double) o[5]
                );
                tblStProgramDetails.getItems().add(programDetailsTM);
                tblStProgramDetails.refresh();
                textStudentNumber.requestFocus();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

}
