package lk.ijse.UniReserve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Room {
    @Id
    private String room_type_id;
    private String type;
    private String key_money;
    private Integer qty;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;

    public Room() {

    }
}
