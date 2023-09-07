package lk.ijse.UniReserve.bo.custom.impl;

import lk.ijse.UniReserve.bo.custom.LoginBO;
import lk.ijse.UniReserve.dao.DAOFactory;
import lk.ijse.UniReserve.dao.custom.UserDAO;
import lk.ijse.UniReserve.dto.UserDTO;
import lk.ijse.UniReserve.entity.User;

public class LoginBOImpl implements LoginBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean addUser(UserDTO userDTO) throws Exception {
        return userDAO.add(new User(userDAO.generateNewID(), userDTO.getUsername(), userDTO.getPassword()));
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        return userDAO.update(new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword()));
    }

    @Override
    public boolean removeUser(String id) throws Exception {
        return userDAO.delete(id);
    }

    @Override
    public boolean isExist(String id) throws Exception {
        return userDAO.exist(id);
    }

    @Override
    public UserDTO search(String id) throws Exception {
        User user = userDAO.search(id);;
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword());
    }

    @Override
    public boolean userVerify(String username, String password) throws Exception {
        return userDAO.verifyUser(username, password);
    }
}
