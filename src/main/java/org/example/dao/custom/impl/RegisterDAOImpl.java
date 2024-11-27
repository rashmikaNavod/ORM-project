package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.RegisterDAO;
import org.example.entity.Student;
import org.example.entity.StudentProgramDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class RegisterDAOImpl implements RegisterDAO {

    @Override
    public boolean register(Student student, ArrayList<StudentProgramDetails> studentProgramDetailsList) throws Exception {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            boolean isSave = session.save(student) != null;
            if(isSave){
                boolean done = false;
                for(StudentProgramDetails studentProgramDetails : studentProgramDetailsList){
                    session.save(studentProgramDetails);
                    done = true;
                }
                if(done){
                    transaction.commit();
                    return true;
                }
            }
            transaction.rollback();
            return false;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }

    }

}
