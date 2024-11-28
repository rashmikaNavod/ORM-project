package org.example.dao.custom;

import org.example.dao.SuperDAO;
import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Object[]> getStudentProgramDetail(String number) throws SQLException;
}
