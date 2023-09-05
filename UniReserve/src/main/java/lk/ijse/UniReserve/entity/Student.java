package lk.ijse.UniReserve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Student {
    @Id
    private String student_id;
    private String name;
    private String address;
    private String contact;
    private Date dob;
    private String gender;

    @OneToMany(mappedBy = "student")
    private List<Reservation> reservations;

    public Student() {}
}
