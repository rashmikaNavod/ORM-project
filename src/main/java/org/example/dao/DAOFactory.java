package org.example.dao;

import org.example.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        PROGRAM,USER,STUDENT,PAYMENT,STUDENT_PROGRAM_DETAILS,REGISTERED,QUERY_DAO
    }

    public SuperDAO getDAO(DAOType types) {
        switch (types) {
            case PROGRAM:
                return new ProgramDAOImpl();
            case REGISTERED:
                return new RegisterDAOImpl();
            case USER:
                return new UserDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            default:
                return null;
        }
    }

}
