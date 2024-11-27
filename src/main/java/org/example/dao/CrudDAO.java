package org.example.dao;

import org.example.entity.User;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    List<T> getAll() throws Exception;
    boolean add(T entity) throws Exception;
    boolean update(T entity) throws Exception;
    boolean exist(String id) throws Exception;
    boolean delete(String id) throws Exception;
    T getEntityByPhoneNumber(String phoneNumber) throws Exception;
}
