package lk.ijse.UniReserve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class RoomDTO {
    private String room_type_id;
    private String type;
    private String key_money;
    private Integer qty;
    private List<ReservationDTO> reservations;

    public RoomDTO() {

    }
}
