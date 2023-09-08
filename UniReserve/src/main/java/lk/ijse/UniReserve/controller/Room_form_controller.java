package lk.ijse.UniReserve.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.UniReserve.bo.BOFactory;
import lk.ijse.UniReserve.bo.custom.ReservationBO;
import lk.ijse.UniReserve.bo.custom.RoomBO;
import lk.ijse.UniReserve.dto.RoomDTO;
import lk.ijse.UniReserve.dto.tm.RoomTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Room_form_controller implements Initializable {
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
    @FXML
    private TableView<RoomTM> tblRooms;
    @FXML
    private TableColumn<?, ?> colAvailableRooms;

    @FXML
    private TableColumn<?, ?> colKeyPrice;

    @FXML
    private TableColumn<?, ?> colRoomType;

    @FXML
    private TableColumn<?, ?> colRoomTypeId;

    private RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    private ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);

    private ObservableList<RoomTM>roomTMS = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setCellValueFactories();
            loadTableRooms();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactories(){
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyPrice.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colAvailableRooms.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void loadTableRooms() throws Exception {
        roomTMS= FXCollections.observableArrayList();
        List<RoomDTO> rooms = roomBO.getAll();

        for (RoomDTO room: rooms) {
            RoomTM roomTM = new RoomTM(
                    room.getRoom_type_id(),
                    room.getType(),
                    room.getKey_money(),
                    room.getQty()
            );
            roomTMS.add(roomTM);

        }
        tblRooms.setItems(roomTMS);
    }

    public void btnAddOnAction(ActionEvent event) throws Exception {
        boolean isValid = check();
        if (isValid){
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
            loadTableRooms();
        }

    }

    public void btnDeleteOnAction(ActionEvent event) throws Exception {
        boolean isExist = reservationBO.isExistRoom(txtID.getText());
        if (isExist){
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "This Type of Rooms are already reserved.continue?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                try{
                    boolean idDeleted= roomBO.delete(txtID.getText());
                    if(idDeleted){
                        new Alert(Alert.AlertType.CONFIRMATION, "Room Removed!").show();
                    }
                }catch(Exception e){
                    new Alert(Alert.AlertType.ERROR, "Error while Deleting Room :(").show();
                }
                loadTableRooms();

            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Room Type not found!").show();
        }

    }

    public void btnUpdateOnAction(ActionEvent event) throws Exception {
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
        loadTableRooms();
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
            new Alert(Alert.AlertType.WARNING, "No Matching Room found!").show();
        }
    }

    private boolean check() {

        if (!txtKeyMoney.getText().matches("^-?\\d+(\\.\\d+)?$")){
            new Alert(Alert.AlertType.WARNING , "Please enter a valid KeyMoney value").show();
            return false;
        }
        if (!txtQty.getText().matches("^\\d+$")){
            new Alert(Alert.AlertType.WARNING, "Please enter a valid QTY").show();
            return false;
        }
        if (!txtID.getText().matches("^RM-\\d+$")){
                    new Alert(Alert.AlertType.WARNING, "Please enter a valid ID").show();
                    return false;
                }

        return true;
    }
}
