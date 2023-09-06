package lk.ijse.UniReserve.bo;

import lk.ijse.UniReserve.bo.custom.impl.ReservationBOImpl;
import lk.ijse.UniReserve.bo.custom.impl.RoomBOImpl;
import lk.ijse.UniReserve.bo.custom.impl.StudentBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return boFactory == null ? (boFactory = new BOFactory()) : boFactory;
    }

    public enum BOTypes {
        STUDENT, ROOM, RESERVATION
    }

    public SuperBO getBO(BOTypes type){
        switch (type){
            case STUDENT: return new StudentBOImpl();
            case ROOM: return new RoomBOImpl();
            case RESERVATION: return new ReservationBOImpl();
            default: return null;
        }
    }
}
