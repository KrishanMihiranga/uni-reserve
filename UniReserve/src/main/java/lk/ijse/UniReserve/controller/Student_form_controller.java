package lk.ijse.UniReserve.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijse.UniReserve.bo.BOFactory;
import lk.ijse.UniReserve.bo.custom.ReservationBO;
import lk.ijse.UniReserve.bo.custom.StudentBO;
import lk.ijse.UniReserve.dto.ReservationDTO;
import lk.ijse.UniReserve.dto.StudentDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Student_form_controller implements Initializable {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnFind;

    @FXML
    private JFXButton btnupdate;

    @FXML
    private JFXComboBox<String> genderCombo;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private DatePicker txtDOB;

    @FXML
    private TextField txtName;

    @FXML
    private JFXComboBox<String> cmbStatus;

    @FXML
    private TextField txtStudentId;

    private StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    private ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadGender();
        loadStatus();
    }

    private void loadGender(){
      try {
          ObservableList<String> options = FXCollections.observableArrayList(
                  "Male",
                  "Female",
                  "Other"
          );
          genderCombo.setItems(options);
      }catch (Exception e){
          e.printStackTrace();
      }
    }
 private void loadStatus(){
      try {
          ObservableList<String> options = FXCollections.observableArrayList(
                  "Paid",
                  "Pending"
          );
          cmbStatus.setItems(options);
      }catch (Exception e){
          e.printStackTrace();
      }
    }

    public void btnFindOnAction(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(new FXMLLoader(getClass().getResource("/view/find_student_form.fxml")).load());
        Stage  stage =new Stage();
        stage.setScene(scene);
        stage.setTitle("Find Student");
        stage.show();
    }

    public void btnupdateOnAction(ActionEvent actionEvent) {
        boolean idValid = check();
        if (idValid){
            try{
                StudentDTO student = new StudentDTO(
                        txtStudentId.getText(),
                        txtName.getText(),
                        txtAddress.getText(),
                        txtContact.getText(),
                        txtDOB.getValue(),
                        genderCombo.getValue(),
                        new ArrayList<>()
                );
                try {
                    boolean isUpdated = studentBO.update(student);
                    boolean isStatusUpdated = reservationBO.updateStatus(txtStudentId.getText(), cmbStatus.getValue());
                    if (isUpdated){
                        new Alert(Alert.AlertType.CONFIRMATION, "Student Updated!").show();
                    }else{
                        new Alert(Alert.AlertType.ERROR, "Error while Updating Student :(").show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws Exception {
        boolean isExist = reservationBO.isExist(txtStudentId.getText());
        if (isExist){

        new Alert(Alert.AlertType.WARNING, "Couldn't Delete! Student is already in use.").show();
        }else{
            try{

                boolean idDeleted= studentBO.delete(txtStudentId.getText());
                if(idDeleted){
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Removed!").show();
                }
            }catch(Exception e){
                new Alert(Alert.AlertType.ERROR, "Error while Deleting Student :(").show();
            }


        }

    }

    public void txtStudentIdOnAction(ActionEvent actionEvent) {
        try {
            StudentDTO student = studentBO.setFields(txtStudentId.getText());

            if (student!=null){
                txtStudentId.setText(student.getStudent_id());
                txtName.setText(student.getName());
                txtAddress.setText(student.getAddress());
                txtContact.setText(student.getContact());
                txtDOB.setValue(student.getDob());
                genderCombo.setValue(student.getGender());
                cmbStatus.setValue(reservationBO.setFields(txtStudentId.getText()));
            }else{
                new Alert(Alert.AlertType.WARNING, "No Matching Student or Reservation found!").show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "No Matching Student found!").show();
        }
    }

    private boolean check() {

        if (!txtName.getText().matches("^[A-Za-zÀ-ÖØ-öø-ÿ-]+(?:\\s+[A-Za-zÀ-ÖØ-öø-ÿ-]+)+$")){
            new Alert(Alert.AlertType.WARNING , "Please enter a valid User Name").show();
            return false;
        }
        if (!txtContact.getText().matches("^\\+?\\d{8,}$")){
            new Alert(Alert.AlertType.WARNING, "Please enter a valid Contact number").show();
            return false;
        }
        if (!txtAddress.getText().matches("^.+$")){
            new Alert(Alert.AlertType.WARNING, "Please enter a valid Address").show();
            return false;
        }
        if (!txtStudentId.getText().matches("^STD\\d+$")){
            new Alert(Alert.AlertType.WARNING, "Please enter a valid ID").show();
            return false;
        }

        return true;
    }
}
