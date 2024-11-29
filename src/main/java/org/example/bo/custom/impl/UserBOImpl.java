package org.example.bo.custom.impl;

import org.example.bo.BOFactory;
import org.example.bo.custom.UserBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.ProgramDAO;
import org.example.dao.custom.UserDAO;
import org.example.dto.UserDTO;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);

    @Override
    public User getUserByPhoneNumber(String number) throws Exception {
        return null;
    }

    @Override
    public List<UserDTO> getAll() throws Exception {
        return List.of();
    }

    @Override
    public boolean add(UserDTO dto) throws Exception {
        return userDAO.add(new User(dto.getUPhoneNumber(),dto.getUserName(),dto.getPassword(),dto.getAddress(),dto.getUserState(),new ArrayList<>()));
    }

    @Override
    public boolean update(UserDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean exist(String id) throws Exception {
        return userDAO.exist(id);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public List<UserDTO> getUserByRoleAndUsername(String role, String username) throws Exception {
        List<User> userList = userDAO.getUserByRoleAndUsername(role,username);
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            UserDTO userDTO = new UserDTO(
                    user.getUPhoneNumber(),
                    user.getUserName(),
                    user.getPassword(),
                    user.getAddress(),
                    user.getUserState()
            );
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

}
