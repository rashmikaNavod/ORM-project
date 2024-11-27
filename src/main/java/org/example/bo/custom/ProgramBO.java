package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.ProgramDTO;

import java.util.List;

public interface ProgramBO extends SuperBO {
    List<ProgramDTO> getAll() throws Exception;
    boolean add(ProgramDTO dto) throws Exception;
    boolean update(ProgramDTO dto) throws Exception;
    boolean exist(String id) throws Exception;
    boolean delete(String id) throws Exception;
}
