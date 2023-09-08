package lk.ijse.UniReserve.dao.custom;

import lk.ijse.UniReserve.dao.CrudDAO;
import lk.ijse.UniReserve.entity.Room;

public interface RoomDAO extends CrudDAO<Room> {
    Room setFields(String text)throws Exception;

    Room getRoom(String value) throws Exception;

    Integer getTotQtyOfRooms(String room_type_id)throws Exception;
}
