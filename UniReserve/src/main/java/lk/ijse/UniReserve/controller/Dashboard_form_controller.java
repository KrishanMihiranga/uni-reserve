package lk.ijse.UniReserve.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

public class Dashboard_form_controller implements Initializable {

    @FXML
    private AnchorPane MainPane;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnManageRoom;

    @FXML
    private JFXButton btnRegisterStudent;

    @FXML
    private JFXButton btnReservationDetails;

    @FXML
    private JFXButton btnReserveRoom;

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private Text lblTopic;

    @FXML
    private Text txtDate;

    @FXML
    private Text txtTime;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTxtDateTime();
    }
    private void setTxtDateTime() {

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        java.util.Date Date = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(Date);
        txtDate.setText(dateString);

    }
    private void updateTime() {
        LocalTime now = LocalTime.now();
        String formattedTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        txtTime.setText(formattedTime);
    }
    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Dashboard_form_controller.class.getResource("/view/dashboard_form.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);

    }

    public void btnRegisterStudentOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/student_form.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("Manage");
    }

    public void btnManageRoomOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/room_form.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("Manage");
    }

    public void btnReserveRoomOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/reservation_form.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("Manage");
    }


}
