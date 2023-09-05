package lk.ijse.UniReserve.bo.custom;

import lk.ijse.UniReserve.bo.SuperBO;
import lk.ijse.UniReserve.dto.RoomDTO;

import java.util.List;

public interface RoomBO extends SuperBO {
    public boolean add(RoomDTO room)throws Exception;

    boolean update(RoomDTO room)throws Exception;

    boolean delete(String text)throws Exception;

    RoomDTO setFields(String text)throws Exception;

    List<RoomDTO> getAll() throws Exception;
}
