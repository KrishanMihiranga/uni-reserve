package lk.ijse.UniReserve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
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
    private LocalDate dob;
    private String gender;

    @OneToMany(mappedBy = "student")
    private List<Reservation> reservations;

    public Student(String id, String name, String address, String contactNo, LocalDate dob, String gender) {
        this.student_id = id;
        this.name = name;
        this.address = address;
        this.contact = contactNo;
        this.dob = dob;
        this.gender = gender;
    }

    public Student() {}
}
