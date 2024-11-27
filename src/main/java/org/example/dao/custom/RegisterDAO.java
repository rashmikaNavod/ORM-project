package org.example.dao.custom;

import org.example.dao.SuperDAO;
import org.example.entity.Student;
import org.example.entity.StudentProgramDetails;

import java.util.ArrayList;

public interface RegisterDAO extends SuperDAO {

    boolean register(Student student, ArrayList<StudentProgramDetails> studentProgramDetails) throws Exception;
}
