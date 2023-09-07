package lk.ijse.UniReserve.bo.custom;

import lk.ijse.UniReserve.bo.SuperBO;

public interface DashBoardBO extends SuperBO {
    int getAllReservationCount() throws Exception;

    int getTotOfAvailableRoomsCount() throws Exception;

    int getREGStuCount()throws Exception;
}
