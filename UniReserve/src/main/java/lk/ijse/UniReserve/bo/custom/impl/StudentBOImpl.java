package lk.ijse.UniReserve.bo.custom.impl;

import lk.ijse.UniReserve.bo.custom.StudentBO;
import lk.ijse.UniReserve.dao.DAOFactory;
import lk.ijse.UniReserve.dao.custom.StudentDAO;
import lk.ijse.UniReserve.dto.RoomDTO;
import lk.ijse.UniReserve.dto.StudentDTO;
import lk.ijse.UniReserve.entity.Room;
import lk.ijse.UniReserve.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean add(StudentDTO entity) throws Exception {
        return studentDAO.add(new Student(entity.getStudent_id(), entity.getName(), entity.getAddress(), entity.getContact(), entity.getDob(), entity.getGender()));
    }

    @Override
    public boolean update(StudentDTO entity) throws Exception {
        return studentDAO.update(new Student(entity.getStudent_id(), entity.getName(), entity.getAddress(), entity.getContact(), entity.getDob(), entity.getGender()));
    }

    @Override
    public boolean delete(String sId) throws Exception {
        return studentDAO.delete(sId);
    }

    @Override
    public StudentDTO setFields(String text) throws Exception {
        Student student =studentDAO.setFields(text);
        return new StudentDTO(student.getStudent_id(), student.getName(), student.getAddress(), student.getContact(), student.getDob(), student.getGender());
    }

    @Override
    public List<StudentDTO> getAll() throws Exception {
        List<Student> Students =studentDAO.getAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student:Students) {
            studentDTOS.add(new StudentDTO(student.getStudent_id(), student.getName(), student.getAddress(),student.getContact(),student.getDob(),student.getGender()));
        }
        return studentDTOS;
    }
}
