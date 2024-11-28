package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.QueryDAO;
import org.example.entity.CustomEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<Object[]> getStudentProgramDetail(String number) throws SQLException {
        List<Object[]> list = new ArrayList<>();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        list = session.createQuery(
                "SELECT s.name, p.programId, p.programName, p.fee, p.duration, std.registrationFee " +
                        "FROM StudentProgramDetails std " +
                        "JOIN std.program p " +
                        "JOIN std.student s " +
                        "WHERE s.sPhoneNumber = :studentNumber"
        ).setParameter("studentNumber", number).list();
        transaction.commit();
        session.close();


        return list;
    }
}
