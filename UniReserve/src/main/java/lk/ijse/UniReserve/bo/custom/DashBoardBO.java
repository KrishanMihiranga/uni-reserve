package lk.ijse.UniReserve.bo.custom;

import lk.ijse.UniReserve.bo.SuperBO;
import lk.ijse.UniReserve.dto.UserDTO;

public interface DashBoardBO extends SuperBO {
    int getAllReservationCount() throws Exception;

    int getTotOfAvailableRoomsCount() throws Exception;

    int getREGStuCount()throws Exception;

    public UserDTO getUser(UserDTO dto) throws Exception;
    public boolean updateUser(UserDTO dto) throws Exception;
}
