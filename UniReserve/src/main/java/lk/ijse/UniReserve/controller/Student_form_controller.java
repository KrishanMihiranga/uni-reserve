package lk.ijse.UniReserve.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Student_form_controller {
    @FXML
    private JFXButton btnFind;

    public void btnFindOnAction(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(new FXMLLoader(getClass().getResource("/view/find_student_form.fxml")).load());
        Stage  stage =new Stage();
        stage.setScene(scene);
        stage.setTitle("Find Student");
        stage.show();
    }
}
