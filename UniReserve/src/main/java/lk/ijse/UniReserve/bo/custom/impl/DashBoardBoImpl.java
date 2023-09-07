package lk.ijse.UniReserve.bo.custom.impl;

import lk.ijse.UniReserve.bo.custom.DashBoardBO;
import lk.ijse.UniReserve.dao.DAOFactory;
import lk.ijse.UniReserve.dao.custom.ReservationDAO;
import lk.ijse.UniReserve.dao.custom.RoomDAO;
import lk.ijse.UniReserve.dao.custom.StudentDAO;
import lk.ijse.UniReserve.entity.Room;
import lk.ijse.UniReserve.entity.Student;

import java.util.List;

public class DashBoardBoImpl implements DashBoardBO {
    private ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    private RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public int getAllReservationCount() throws Exception {
        return reservationDAO.getAll().size();
    }

    @Override
    public int getTotOfAvailableRoomsCount() throws Exception {
        List<Room> all = roomDAO.getAll();

        int totRoomsAvailable = 0;
        for (Room room : all) {
            totRoomsAvailable += room.getQty();
        }

        return totRoomsAvailable;
    }

    @Override
    public int getREGStuCount() throws Exception {
        return studentDAO.getAll().size();
    }
}
