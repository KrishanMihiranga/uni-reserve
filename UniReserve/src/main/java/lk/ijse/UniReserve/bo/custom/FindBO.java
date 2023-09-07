package lk.ijse.UniReserve.bo.custom;

import lk.ijse.UniReserve.bo.SuperBO;

import java.util.List;

public interface FindBO extends SuperBO {
    List<Object[]> getNonPayedStudents() throws Exception;
}
