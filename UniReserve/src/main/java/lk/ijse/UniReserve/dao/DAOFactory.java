package lk.ijse.UniReserve.dao;

import lk.ijse.UniReserve.dao.custom.impl.QueryDAOImpl;
import lk.ijse.UniReserve.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.UniReserve.dao.custom.impl.RoomDAOImpl;
import lk.ijse.UniReserve.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? (daoFactory = new DAOFactory()) : daoFactory;
    }

    public enum DAOTypes{
        STUDENT, ROOM, RESERVATION, QUERY
    }

    public SuperDAO getDAO(DAOTypes type){
        switch (type){
            case STUDENT: return new StudentDAOImpl();
            case ROOM: return new RoomDAOImpl();
            case RESERVATION: return new ReservationDAOImpl();
            case QUERY: return new QueryDAOImpl();
            default: return null;
        }
    }
}
