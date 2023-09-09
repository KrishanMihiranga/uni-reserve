package lk.ijse.UniReserve.bo.custom.impl;

import lk.ijse.UniReserve.bo.custom.ReservationBO;
import lk.ijse.UniReserve.dao.DAOFactory;
import lk.ijse.UniReserve.dao.custom.ReservationDAO;
import lk.ijse.UniReserve.dao.custom.RoomDAO;
import lk.ijse.UniReserve.dao.custom.StudentDAO;
import lk.ijse.UniReserve.dto.ReservationDTO;
import lk.ijse.UniReserve.dto.RoomDTO;
import lk.ijse.UniReserve.dto.StudentDTO;
import lk.ijse.UniReserve.entity.Reservation;
import lk.ijse.UniReserve.entity.Room;
import lk.ijse.UniReserve.entity.Student;
import lk.ijse.UniReserve.utill.FactoryConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    private RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    private StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public String setReservationID() throws Exception {
        return reservationDAO.getNextID();
    }

    @Override
    public RoomDTO getRooms(String value) throws Exception {
        Room room = roomDAO.getRoom(value);

        return new RoomDTO(room.getRoom_type_id(),room.getType(),room.getKey_money(),room.getQty());
    }

    @Override
    public List<StudentDTO> getStudents() throws Exception {
        List<Student> all = studentDAO.getAll();
        List<StudentDTO> studentList = new ArrayList<>();
        for (Student student : all){
            studentList.add(new StudentDTO(student.getStudent_id(), student.getName(), student.getAddress(),student.getContact(),student.getDob(),student.getGender()));

        }
        return studentList;
    }

    @Override
    public boolean registerStudent(ReservationDTO reservation) throws Exception {
        StudentDTO studentDTO = reservation.getStudent();
        Student student = new Student(
                studentDTO.getStudent_id(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContact(),
                studentDTO.getDob(),
                studentDTO.getGender(),
                new ArrayList<>()
        );

        RoomDTO roomDTO = reservation.getRoom();
        Room room = roomDAO.search(roomDTO.getRoom_type_id());

        if (room != null) {
            Reservation reservations = new Reservation(
                    reservation.getRes_id(),
                    reservation.getDate(),
                    reservation.getStatus(),
                    student,
                    room
            );

            student.getReservations().add(reservations);
            room.getReservations().add(reservations);
            room.setQty(room.getQty() - 1);

            boolean isRegistered = studentDAO.add(student);
            roomDAO.update(room);

            return isRegistered;
        } else {
            throw new Exception("Room with room_type_id " + roomDTO.getRoom_type_id() + " not found.");
        }
    }


    @Override
    public List<RoomDTO> getAllRooms() throws Exception {
        List<Room> all = roomDAO.getAll();
        List<RoomDTO> roomList = new ArrayList<>();
        for (Room room : all) {

            roomList.add(new RoomDTO(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty(), null));
        }
        return roomList;
    }

    @Override
    public List<ReservationDTO> getAllDetails() throws Exception {
        List<Reservation> all = reservationDAO.getAll();
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservation reservation : all) {
            ReservationDTO reservationDTO = new ReservationDTO(
                    reservation.getRes_id(),
                    reservation.getDate(),
                    reservation.getStatus(),
                    null,
                    null
            );

            Student student = reservation.getStudent();
            reservationDTO.setStudent(new StudentDTO(
                    student.getStudent_id(),
                    student.getName(),
                    student.getAddress(),
                    student.getContact(),
                    student.getDob(),
                    student.getGender(),
                    null
            ));

            Room room = reservation.getRoom();
            reservationDTO.setRoom(new RoomDTO(
                    room.getRoom_type_id(),
                    room.getType(),
                    room.getKey_money(),
                    room.getQty(),
                    null
            ));

            reservationDTOS.add(reservationDTO);
        }

        return reservationDTOS;
    }

    @Override
    public String setFields(String text) throws Exception {
        Reservation reservation =reservationDAO.setFields(text);
        return reservation.getStatus();

        /*Student student = reservation.getStudent();
        StudentDTO studentDTO = new StudentDTO(student.getStudent_id(), student.getName(), student.getAddress(), student.getContact(), student.getDob(), student.getGender());

        Room room = reservation.getRoom();
        RoomDTO roomDTO = new RoomDTO(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty());

        return new ReservationDTO(reservation.getRes_id(), reservation.getDate(), reservation.getStatus(),studentDTO,roomDTO );*/
    }

    @Override
    public boolean UpdateStudent(ReservationDTO reservation) throws Exception {
        StudentDTO studentDTO = reservation.getStudent();
        Student student = new Student(
                studentDTO.getStudent_id(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContact(),
                studentDTO.getDob(),
                studentDTO.getGender(),
                new ArrayList<>()
        );

        RoomDTO roomDTO = reservation.getRoom();
        Room room = roomDAO.search(roomDTO.getRoom_type_id());

        if (room != null) {
            Reservation reservations = new Reservation(
                    reservation.getRes_id(),
                    reservation.getDate(),
                    reservation.getStatus(),
                    student,
                    room
            );

            student.getReservations().add(reservations);
            room.getReservations().add(reservations);
            room.setQty(room.getQty() - 1);

            boolean isRegistered = studentDAO.update(student);
            roomDAO.update(room);
            reservationDAO.update(reservations);

            return isRegistered;
        } else {

            throw new Exception("Room with room_type_id " + roomDTO.getRoom_type_id() + " not found.");
        }
    }

    @Override
    public boolean isExist(String text) throws Exception {
        return reservationDAO.isExist(text);
    }

    @Override
    public boolean isExistRoom(String id) throws Exception {
        return reservationDAO.isExistRoom(id);
    }

    @Override
    public ReservationDTO getExistingReservation(String reservationID) throws Exception {
        return reservationDAO.getExistingReservation(reservationID);
    }

    @Override
    public boolean updateStatus(String sId, String value) throws Exception {
        return reservationDAO.updateStatus(sId, value);
    }


}
