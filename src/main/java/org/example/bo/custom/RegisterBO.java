package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.ProgramDTO;
import org.example.dto.StudentDTO;
import org.example.dto.StudentProgramDetailsDTO;

import java.util.ArrayList;

public interface RegisterBO extends SuperBO {
    boolean register(StudentDTO studentDTO, ArrayList<StudentProgramDetailsDTO> courseDetails) throws Exception;
}
