package org.example.bo.custom.impl;

import org.example.bo.custom.StudentBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.ProgramDAO;
import org.example.dao.custom.StudentDAO;
import org.example.dto.ProgramDTO;
import org.example.dto.StudentDTO;
import org.example.entity.Program;
import org.example.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.STUDENT);

    @Override
    public List<StudentDTO> getAll() throws Exception {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        List<Student> studentList = studentDAO.getAll();

        for(Student student : studentList) {
            studentDTOList.add(new StudentDTO(student.getSPhoneNumber(),student.getName(),student.getDateOfBirth(),student.getAddress(),student.getRegistrationDate(),student.getUser().getUPhoneNumber()));
        }

        return studentDTOList;
    }

    @Override
    public boolean add(StudentDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(StudentDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean exist(String id) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return studentDAO.delete(id);
    }
}
