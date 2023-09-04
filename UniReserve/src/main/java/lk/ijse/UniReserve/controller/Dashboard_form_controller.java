package lk.ijse.UniReserve.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Dashboard_form_controller {

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

    public void btnReservationDetailsOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/reservation_details_form.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("Details");
    }
}
