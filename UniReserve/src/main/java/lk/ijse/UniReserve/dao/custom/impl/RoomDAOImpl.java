package lk.ijse.UniReserve.dao.custom.impl;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import lk.ijse.UniReserve.dao.custom.RoomDAO;
import lk.ijse.UniReserve.entity.Room;
import lk.ijse.UniReserve.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    @Override
    public List<Room> getAll() throws Exception {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query =session.createQuery("from Room");
        List<Room> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public boolean add(Room entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean exist(String id) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(session.get(Room.class, id));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Room search(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.get(Room.class, id);

        transaction.commit();
        session.close();

        return room;
    }

    @Override
    public Room setFields(String text) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.get(Room.class, text);

        transaction.commit();
        session.close();

        return room;
    }

    @Override
    public Room getRoom(String value) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Room where id = :value");
        query.setParameter("value", value);

        Room room = (Room) query.uniqueResult();

        transaction.commit();
        session.close();

        return room;
    }

    @Override
    public Integer getTotQtyOfRooms(String room_type_id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query hqlQuery = session.createQuery("SELECT COUNT(*) FROM Reservation WHERE roomType.id = :roomTypeId");
        hqlQuery.setParameter("roomTypeId", room_type_id);
        Long result = (Long) hqlQuery.uniqueResult();

        transaction.commit();
        session.close();

        return Math.toIntExact(result);

    }
}
