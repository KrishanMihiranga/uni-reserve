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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class Reservation_form_controller implements Initializable {
    @FXML
    private JFXButton btnDetails;

    @FXML
    private JFXComboBox<String> cmbRType;

    @FXML
    private JFXComboBox<String> cmbstatus;

    @FXML
    private TextField txtRDate;

    @FXML
    private TextField txtRID;

    @FXML
    private TextField txtSID;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadType();
        loadStatus();
        setDate();
    }

    private void setDate() {
        java.util.Date Date = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(Date);
        txtRDate.setText(dateString);
    }

    private void loadStatus() {
        try {
            ObservableList<String> options = FXCollections.observableArrayList(
                    "Paid",
                    "Pending"
            );
            cmbstatus.setItems(options);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadType() {
        try {
            ObservableList<String> options = FXCollections.observableArrayList(
                    "Non-AC",
                    "Non-AC / Food",
                    "AC",
                    "AC / Food"
            );
            cmbRType.setItems(options);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void btnDetailsOnAction(ActionEvent event) throws IOException {
        Scene scene = new Scene(new FXMLLoader(getClass().getResource("/view/reservation_details_form.fxml")).load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Reservation Details");
        stage.show();

    }


}
