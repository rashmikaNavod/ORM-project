package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    List<User> getUserByRoleAndUsername(String role,String username) throws Exception;
}
