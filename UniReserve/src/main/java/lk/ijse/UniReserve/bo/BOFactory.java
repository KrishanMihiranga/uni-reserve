package lk.ijse.UniReserve.bo;

import lk.ijse.UniReserve.bo.custom.impl.RoomBOImpl;
import lk.ijse.UniReserve.bo.custom.impl.StudentBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return boFactory == null ? (boFactory = new BOFactory()) : boFactory;
    }

    public enum BOTypes {
        STUDENT, ROOM;
    }

    public SuperBO getBO(BOTypes type){
        switch (type){
            case STUDENT: return new StudentBOImpl();
            case ROOM: return new RoomBOImpl();
            default: return null;
        }
    }
}
