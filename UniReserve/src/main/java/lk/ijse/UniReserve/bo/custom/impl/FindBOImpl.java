package lk.ijse.UniReserve.bo.custom.impl;

import lk.ijse.UniReserve.bo.custom.FindBO;
import lk.ijse.UniReserve.dao.DAOFactory;
import lk.ijse.UniReserve.dao.custom.QueryDAO;

import java.util.List;

public class FindBOImpl implements FindBO {
    private QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    @Override
    public List<Object[]> getNonPayedStudents() throws Exception {
        return queryDAO.nonPayedKeyMoneyStd();
    }
}
