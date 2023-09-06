package lk.ijse.UniReserve.dao.custom;

import lk.ijse.UniReserve.dao.CrudDAO;
import lk.ijse.UniReserve.entity.Reservation;

public interface ReservationDAO extends CrudDAO<Reservation> {
    String getNextID()throws Exception;
    String getNewID(String lastId)throws Exception;
}
