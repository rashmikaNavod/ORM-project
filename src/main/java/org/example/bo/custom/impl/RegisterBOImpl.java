package org.example.bo.custom.impl;

import org.example.bo.custom.RegisterBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.ProgramDAO;
import org.example.dao.custom.RegisterDAO;
import org.example.dao.custom.UserDAO;
import org.example.dto.StudentDTO;
import org.example.dto.StudentProgramDetailsDTO;
import org.example.entity.Program;
import org.example.entity.Student;
import org.example.entity.StudentProgramDetails;
import org.example.entity.User;

import java.util.ArrayList;


public class RegisterBOImpl implements RegisterBO {

    @Override
    public boolean register(StudentDTO studentDTO, ArrayList<StudentProgramDetailsDTO> courseDetails ) throws Exception {

        RegisterDAO registerDAO = (RegisterDAO)DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.REGISTERED);
        UserDAO userDAO = (UserDAO)DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);
        ProgramDAO programDAO = (ProgramDAO)DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PROGRAM);

        ArrayList<StudentProgramDetails> studentProgramDetailsList = new ArrayList();
        User user = userDAO.getEntityByPhoneNumber(studentDTO.getUPhoneNumber());

        if (user == null) {
            throw new Exception("User not found");
        }

        Student student = new Student(
                studentDTO.getSPhoneNumber(),
                studentDTO.getName(),
                studentDTO.getDateOfBirth(),
                studentDTO.getAddress(),
                studentDTO.getRegistrationDate(),
                user,
                new ArrayList<>()
        );

        for (StudentProgramDetailsDTO detail : courseDetails) {
            Program program = programDAO.getEntityByPhoneNumber(detail.getProgramId());
            if (program == null) {
                throw new Exception("Program not found");
            }
            StudentProgramDetails studentProgramDetails = new StudentProgramDetails(0,detail.getRegistrationFee(),student,program);
            studentProgramDetailsList.add(studentProgramDetails);
        }

        return registerDAO.register(student,studentProgramDetailsList);
    }
}
