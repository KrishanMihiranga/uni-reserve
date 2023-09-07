package lk.ijse.UniReserve.dao.custom;

import lk.ijse.UniReserve.dao.CrudDAO;
import lk.ijse.UniReserve.entity.User;

public interface UserDAO extends CrudDAO<User> {
    public boolean verifyUser(String username, String password) throws Exception;
    public User search(String username, String password) throws Exception;

    String generateNewID() throws Exception;
}
