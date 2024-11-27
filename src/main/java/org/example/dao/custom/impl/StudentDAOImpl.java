package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.StudentDAO;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public List<Student> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Student> list = session.createQuery("from Student ",Student.class).list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public boolean add(Student entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        return false;
    }

    @Override
    public boolean exist(String id) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM StudentProgramDetails WHERE student.sPhoneNumber = :number")
                .setParameter("number", id)
                .executeUpdate();
        session.createQuery("DELETE FROM Student WHERE sPhoneNumber = :programID")
                .setParameter("programID", id)
                .executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student getEntityByPhoneNumber(String phoneNumber) throws Exception {
        return null;
    }

}
