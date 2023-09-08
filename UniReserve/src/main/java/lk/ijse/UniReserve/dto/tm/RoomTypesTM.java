package lk.ijse.UniReserve.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomTypesTM {
    private String roomTypeId;
    private String type;
    private String keyMoney;
    private Integer qty;

    public RoomTypesTM(){}
}
