package lk.ijse.UniReserve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
public class Reservation {
    @Id
    private String res_id;
    private Date date;
    private String status;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Room room;

    public Reservation() {

    }
}
