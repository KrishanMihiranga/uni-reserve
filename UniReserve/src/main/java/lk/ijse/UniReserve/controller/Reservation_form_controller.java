package lk.ijse.UniReserve.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Reservation_form_controller {
    @FXML
    private JFXButton btnDetails;

    @FXML
    void btnDetailsOnAction(ActionEvent event) throws IOException {
        Scene scene = new Scene(new FXMLLoader(getClass().getResource("/view/reservation_details_form.fxml")).load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Reservation Details");
        stage.show();

    }

}
