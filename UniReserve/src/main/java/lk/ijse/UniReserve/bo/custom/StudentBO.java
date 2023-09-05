package lk.ijse.UniReserve.bo.custom;

import lk.ijse.UniReserve.bo.SuperBO;
import lk.ijse.UniReserve.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    public boolean add(StudentDTO entity) throws Exception;

    boolean update(StudentDTO entity)throws Exception;

    boolean delete(String sId)throws Exception;

    StudentDTO setFields(String text)throws Exception;

    List<StudentDTO> getAll() throws Exception;
}
