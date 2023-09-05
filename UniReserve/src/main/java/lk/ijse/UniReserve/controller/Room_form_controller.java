package lk.ijse.UniReserve.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.UniReserve.bo.BOFactory;
import lk.ijse.UniReserve.bo.custom.RoomBO;
import lk.ijse.UniReserve.bo.custom.StudentBO;
import lk.ijse.UniReserve.dto.RoomDTO;
import lk.ijse.UniReserve.dto.StudentDTO;

import java.util.ArrayList;

public class Room_form_controller {
    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtKeyMoney;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtType;

    private RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);


    public void btnAddOnAction(ActionEvent event) {

        try{
            RoomDTO room = new RoomDTO(
                   txtID.getText(),
                    txtType.getText(),
                    txtKeyMoney.getText(),
                    Integer.parseInt(txtQty.getText()),
                    new ArrayList<>()
            );
            try {
                boolean isAdded = roomBO.add(room);
                if (isAdded){
                    new Alert(Alert.AlertType.CONFIRMATION, "Room Added!").show();
                }else{
                    new Alert(Alert.AlertType.ERROR, "Error while Adding Room :(").show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent event) {
        try{
            boolean idDeleted= roomBO.delete(txtID.getText());
            if(idDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Room Removed!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Error while Deleting Room :(").show();
        }
    }


    public void btnUpdateOnAction(ActionEvent event) {
        try{
            RoomDTO room = new RoomDTO(
                    txtID.getText(),
                    txtType.getText(),
                    txtKeyMoney.getText(),
                    Integer.parseInt(txtQty.getText()),
                    new ArrayList<>()
            );
            try {
                boolean isUpdated = roomBO.update(room);
                if (isUpdated){
                    new Alert(Alert.AlertType.CONFIRMATION, "Room Updated!").show();
                }else{
                    new Alert(Alert.AlertType.ERROR, "Error while Updating Room :(").show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void txtIDOnAction(ActionEvent event) {
        try {
            RoomDTO room = roomBO.setFields(txtID.getText());
            if (room!=null){
                        txtID.setText(room.getRoom_type_id());
                        txtType.setText(room.getType());
                        txtKeyMoney.setText(room.getKey_money());
                        txtQty.setText(String.valueOf(room.getQty()));


            }else{
                new Alert(Alert.AlertType.WARNING, "No Matching Room found!").show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
