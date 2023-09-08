package lk.ijse.UniReserve.dao.custom.impl;

import lk.ijse.UniReserve.dao.custom.ReservationDAO;
import lk.ijse.UniReserve.dto.ReservationDTO;
import lk.ijse.UniReserve.dto.RoomDTO;
import lk.ijse.UniReserve.dto.StudentDTO;
import lk.ijse.UniReserve.entity.Reservation;
import lk.ijse.UniReserve.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public List<Reservation> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Reservation");
        List<Reservation> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public boolean add(Reservation entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Reservation entity) throws Exception {
        Session session =FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean exist(String id) throws Exception {
        Session session =FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        boolean isExist = session.get(Reservation.class, id) !=null;
        transaction.commit();
        session.close();

        return isExist;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public Reservation search(String id) throws Exception {
        return null;
    }

    @Override
    public String getNextID() throws Exception {
        String res_id = "R001";
        if (exist(res_id)){
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction= session.beginTransaction();
            Query query = session.createQuery("from Reservation order by res_id desc limit 1");
            List<Reservation> list = query.list();
            res_id=list.get(0).getRes_id();

            transaction.commit();
            session.close();
            return getNewID(res_id);
        }
        return res_id;
    }

    @Override
    public String getNewID(String lastId) throws Exception {
        String prefix = lastId.replaceAll("[0-9]", "");
        int number = Integer.parseInt(lastId.replaceAll("\\D", ""));
        number++;
        String newNumber = String.format("%0" + (lastId.length() - prefix.length()) + "d", number);
        return prefix + newNumber;
    }

    @Override
    public boolean isExist(String studentID) throws Exception {
        Session session =FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT COUNT(*) FROM Reservation WHERE student.student_id = :studentID");
        query.setParameter("studentID", studentID);
        Long count = (Long) query.uniqueResult();

        transaction.commit();
        session.close();

        return count > 0;
    }

    @Override
    public boolean isExistRoom(String id) throws Exception {
        Session session =FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT COUNT(*) FROM Reservation WHERE room.room_type_id = :id");
        query.setParameter("id", id);
        Long count = (Long) query.uniqueResult();

        transaction.commit();
        session.close();

        return count > 0;
    }

    @Override
    public ReservationDTO getExistingReservation(String reservationID) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        Reservation reservation = session.get(Reservation.class, reservationID);

        transaction.commit();
        session.close();

        if (reservation != null) {
            // Create a ReservationDTO and set its properties
            ReservationDTO reservationDTO = new ReservationDTO();
            reservationDTO.setRes_id(reservation.getRes_id());
            reservationDTO.setDate(reservation.getDate());
            reservationDTO.setStatus(reservation.getStatus());

            // Create a StudentDTO and set its properties
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudent_id(reservation.getStudent().getStudent_id());
            studentDTO.setName(reservation.getStudent().getName());
            studentDTO.setAddress(reservation.getStudent().getAddress());
            studentDTO.setContact(reservation.getStudent().getContact());
            studentDTO.setDob(reservation.getStudent().getDob());
            studentDTO.setGender(reservation.getStudent().getGender());
            reservationDTO.setStudent(studentDTO);

            // Create a RoomDTO and set its properties
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setRoom_type_id(reservation.getRoom().getRoom_type_id());
            roomDTO.setType(reservation.getRoom().getType());
            roomDTO.setKey_money(reservation.getRoom().getKey_money());
            roomDTO.setQty(reservation.getRoom().getQty());
            reservationDTO.setRoom(roomDTO);

            return reservationDTO;
        } else {
            return null;
        }

    }
}
