package lk.ijse.UniReserve.dao.custom;

import lk.ijse.UniReserve.dao.CrudDAO;
import lk.ijse.UniReserve.entity.Student;

public interface StudentDAO extends CrudDAO<Student> {

    Student setFields(String text)throws Exception;
}
