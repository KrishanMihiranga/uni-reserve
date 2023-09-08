package lk.ijse.UniReserve.bo.custom.impl;

import lk.ijse.UniReserve.bo.custom.RoomBO;
import lk.ijse.UniReserve.dao.DAOFactory;
import lk.ijse.UniReserve.dao.custom.RoomDAO;
import lk.ijse.UniReserve.dto.RoomDTO;
import lk.ijse.UniReserve.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean add(RoomDTO room) throws Exception {
        return roomDAO.add(new Room(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty(),new ArrayList<>()));
    }

    @Override
    public boolean update(RoomDTO room) throws Exception {
        return roomDAO.update(new Room(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty(), new ArrayList<>()));
    }

    @Override
    public boolean delete(String text) throws Exception {
        return roomDAO.delete(text);
    }

    @Override
    public RoomDTO setFields(String text) throws Exception {
        Room room =roomDAO.setFields(text);
        return new RoomDTO(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty());
    }

    @Override
    public List<RoomDTO> getAll() throws Exception {
        List<Room> rooms =roomDAO.getAll();
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room:rooms) {
            roomDTOS.add(new RoomDTO(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty(), null));
        }
        return roomDTOS;
    }

    @Override
    public Integer getTotQtyOfRoom(String room_type_id) throws Exception {
        return roomDAO.getTotQtyOfRooms(room_type_id);
    }
}
