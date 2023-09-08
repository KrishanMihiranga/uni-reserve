package lk.ijse.UniReserve.dao.custom;

import lk.ijse.UniReserve.dao.CrudDAO;
import lk.ijse.UniReserve.dto.ReservationDTO;
import lk.ijse.UniReserve.entity.Reservation;

public interface ReservationDAO extends CrudDAO<Reservation> {
    String getNextID()throws Exception;
    String getNewID(String lastId)throws Exception;

    boolean isExist(String text)throws Exception;

    boolean isExistRoom(String id)throws Exception;

    ReservationDTO getExistingReservation(String reservationID)throws Exception;
}
