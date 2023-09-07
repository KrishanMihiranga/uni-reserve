package lk.ijse.UniReserve.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.UniReserve.bo.BOFactory;
import lk.ijse.UniReserve.bo.custom.LoginBO;
import lk.ijse.UniReserve.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class login_form_controller implements Initializable {
    @FXML
    private JFXTextField tfUsername;

    @FXML
    private JFXTextField tfPassword;

    @FXML
    private JFXPasswordField pfPassword;

    @FXML
    private JFXCheckBox cbShowPassword;

    @FXML
    private JFXButton btnSignIn;
    private LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fieldsOnActions();
    }
    private void fieldsOnActions() {
        tfUsername.setOnAction((e)-> {
            pfPassword.requestFocus();
        });

        tfPassword.setOnAction((e) -> {
            btnSignIn.fire();
        });

        pfPassword.setOnAction((e) -> {
            btnSignIn.fire();
        });

        pfPassword.textProperty().bindBidirectional(tfPassword.textProperty());
    }

    public void btnSignInOnAction(ActionEvent actionEvent) {

            try {
                boolean isVerifiedUser = loginBO.userVerify(tfUsername.getText(), tfPassword.getText());
                if (isVerifiedUser){
                    loadMainWindow();
                }else {
                    new Alert(Alert.AlertType.WARNING, "Login informations are wrong...!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Opss.. Something went wrong!!!").show();
            }

        tfUsername.setText(null);
        tfPassword.setText(null);

    }

    private void loadMainWindow() throws IOException {
        Stage window = (Stage) btnSignIn.getScene().getWindow();
        window.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/dashboard_form.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));

        Dashboard_form_controller dashboard_form_controller =fxmlLoader.getController();
        dashboard_form_controller.setUser(new UserDTO(null, tfUsername.getText(), tfPassword.getText()));
        stage.setTitle("D24 Hostel System");
        stage.show();
    }

    public void cbShowPasswordOnAction(ActionEvent actionEvent) {
        if (cbShowPassword.isSelected()){
            pfPassword.setVisible(false);
        }else {
            pfPassword.setVisible(true);
        }
    }


    public void linkNewAccountOnAction(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(new FXMLLoader(getClass().getResource("/view/new_account_form.fxml")).load());
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("New Login");
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    public void btnNewLoginOnAction(ActionEvent actionEvent) {
        UserDTO userDTO = new UserDTO(null, tfUsername.getText(), tfPassword.getText());

        try {
            boolean isUserSaved = loginBO.addUser(userDTO);
            if (isUserSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Opss.. Something went wrong!!!").show();
        }
    }

}
