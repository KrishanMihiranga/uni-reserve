package lk.ijse.UniReserve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ReservationDTO {

    private String res_id;
    private Date date;
    private String status;
    private StudentDTO student;
    private RoomDTO room;

    public ReservationDTO() {}
}
