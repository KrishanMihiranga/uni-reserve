package lk.ijse.UniReserve.bo.custom.impl;

import lk.ijse.UniReserve.bo.custom.RoomBO;
import lk.ijse.UniReserve.dao.DAOFactory;
import lk.ijse.UniReserve.dao.custom.RoomDAO;
import lk.ijse.UniReserve.dto.RoomDTO;
import lk.ijse.UniReserve.dto.StudentDTO;
import lk.ijse.UniReserve.entity.Room;
import lk.ijse.UniReserve.entity.Student;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean add(RoomDTO room) throws Exception {
        return roomDAO.add(new Room(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty()));
    }

    @Override
    public boolean update(RoomDTO room) throws Exception {
        return roomDAO.update(new Room(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty()));
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
}