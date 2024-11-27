package org.example.bo.custom.impl;

import org.example.bo.custom.ProgramBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.ProgramDAO;
import org.example.dto.ProgramDTO;
import org.example.entity.Program;
import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {

    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PROGRAM);

    @Override
    public List<ProgramDTO> getAll() throws Exception {
        List<ProgramDTO> programDTOList = new ArrayList<>();
        List<Program> programList = programDAO.getAll();

        for(Program program : programList) {
            programDTOList.add(new ProgramDTO(program.getProgramId(),program.getProgramName(),program.getDuration(),program.getFee()));
        }

        return programDTOList;
    }

    @Override
    public boolean add(ProgramDTO dto) throws Exception {
        return  programDAO.add( new Program(dto.getProgramId(),dto.getProgramName(),dto.getDuration(), dto.getFee(),new ArrayList<>()));
    }

    @Override
    public boolean update(ProgramDTO dto) throws Exception {
        return  programDAO.update( new Program(dto.getProgramId(),dto.getProgramName(),dto.getDuration(), dto.getFee(),new ArrayList<>()));
    }

    @Override
    public boolean exist(String id) throws Exception {
        return programDAO.exist(id);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return programDAO.delete(id);
    }

}
