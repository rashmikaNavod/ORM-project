package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    List<StudentDTO> getAll() throws Exception;
    boolean add(StudentDTO dto) throws Exception;
    boolean update(StudentDTO dto) throws Exception;
    boolean exist(String id) throws Exception;
    boolean delete(String id) throws Exception;
}
