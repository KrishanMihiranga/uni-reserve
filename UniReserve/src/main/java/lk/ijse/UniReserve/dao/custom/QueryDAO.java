package lk.ijse.UniReserve.dao.custom;

import lk.ijse.UniReserve.dao.CrudDAO;
import lk.ijse.UniReserve.dao.SuperDAO;
import lk.ijse.UniReserve.entity.Reservation;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Object[]> nonPayedKeyMoneyStd() throws Exception;
}
