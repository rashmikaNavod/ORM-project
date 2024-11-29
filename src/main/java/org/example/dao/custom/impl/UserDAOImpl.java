package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.UserDAO;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> getAll() throws Exception {
        return List.of();
    }

    @Override
    public boolean add(User entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User entity) throws Exception {
        return false;
    }

    @Override
    public boolean exist(String id) throws Exception {
        boolean exist = false;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        exist = session.createQuery("from User where id=:id")
        .setParameter("id", id).uniqueResult()!=null;
        transaction.commit();
        session.close();
        return exist;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public User getEntityByPhoneNumber(String phoneNumber) throws Exception {
        User user = new User();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User where uPhoneNumber = :phoneNumber", User.class);
        query.setParameter("phoneNumber", phoneNumber);
        List<User> resultList = query.getResultList();
        user.setUPhoneNumber(resultList.get(0).getUPhoneNumber());
        user.setUserName(resultList.get(0).getUserName());
        user.setPassword(resultList.get(0).getPassword());
        user.setAddress(resultList.get(0).getAddress());
        user.setUserState(resultList.get(0).getUserState());
        transaction.commit();
        session.close();
        return user;

    }

    @Override
    public List<User> getUserByRoleAndUsername(String role,String username) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createQuery(
                "FROM User u WHERE u.userName=:username AND u.userState=:role",User.class
        ).setParameter("username", username).setParameter("role", role).list();
        transaction.commit();
        session.close();
        return userList;
    }
}
