package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.ProgramDAO;
import org.example.entity.Program;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {
    @Override
    public List<Program> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Program> list = session.createQuery("from Program",Program.class).list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public boolean add(Program entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            session.close();
            return true;
    }

    @Override
    public boolean update(Program entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("UPDATE Program SET programName = :programName, duration = :duration, fee = :fee WHERE id = :id")
        .setParameter("programName", entity.getProgramName())
        .setParameter("duration", entity.getDuration())
        .setParameter("fee", entity.getFee())
        .setParameter("id", entity.getProgramId())
        .executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean exist(String id) throws Exception {
        boolean exists = false;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        exists = session.createQuery("FROM Program WHERE id = :programID")
        .setParameter("programID", id)
        .uniqueResult()!= null;
        transaction.commit();
        session.close();
        return exists;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Program WHERE id = :programID")
        .setParameter("programID", id)
        .executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Program getEntityByPhoneNumber(String id) throws Exception {
        Program program = new Program();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query<Program> query = session.createQuery("from Program where programId = :programId", Program.class);
        query.setParameter("programId", id);
        List<Program> resultList = query.getResultList();
        program.setProgramId(resultList.get(0).getProgramId());
        program.setProgramName(resultList.get(0).getProgramName());
        program.setDuration(resultList.get(0).getDuration());
        program.setFee(resultList.get(0).getFee());
        transaction.commit();
        session.close();
        return program;
    }

}
