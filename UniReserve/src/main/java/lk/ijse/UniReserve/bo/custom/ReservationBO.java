package lk.ijse.UniReserve.bo.custom;

import lk.ijse.UniReserve.bo.SuperBO;
import lk.ijse.UniReserve.dto.ReservationDTO;
import lk.ijse.UniReserve.dto.RoomDTO;
import lk.ijse.UniReserve.dto.StudentDTO;

import java.util.List;

public interface ReservationBO extends SuperBO {
    String setReservationID()throws Exception;

    RoomDTO getRooms(String value)throws Exception;

    List<StudentDTO> getStudents()throws Exception;

    boolean registerStudent(ReservationDTO reservation)throws Exception;

    List<RoomDTO> getAllRooms()throws Exception;
}
