package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.UserDTO;
import org.example.entity.User;

import java.util.List;

public interface UserBO extends SuperBO {
    User getUserByPhoneNumber(String number)throws Exception;
    List<UserDTO> getAll() throws Exception;
    boolean add(UserDTO dto) throws Exception;
    boolean update(UserDTO dto) throws Exception;
    boolean exist(String id) throws Exception;
    boolean delete(String id) throws Exception;
    List<UserDTO> getUserByRoleAndUsername(String role,String username) throws Exception;
}
