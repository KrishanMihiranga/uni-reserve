package lk.ijse.UniReserve.bo;

import lk.ijse.UniReserve.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return boFactory == null ? (boFactory = new BOFactory()) : boFactory;
    }

    public enum BOTypes {
        STUDENT, ROOM, RESERVATION, FIND, DASHBOARD
    }

    public SuperBO getBO(BOTypes type){
        switch (type){
            case STUDENT: return new StudentBOImpl();
            case ROOM: return new RoomBOImpl();
            case RESERVATION: return new ReservationBOImpl();
            case FIND: return new FindBOImpl();
            case DASHBOARD: return new DashBoardBoImpl();
            default: return null;
        }
    }
}
