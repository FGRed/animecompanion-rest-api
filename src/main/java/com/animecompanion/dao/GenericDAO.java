package com.animecompanion.dao;

import java.util.List;

public interface GenericDAO {

    void insert(Object object);
    void update(Object object);

    Object get(Long id);
    Object get(Object object);
    Object get(String name);

    void delete(Long id);
    void delete(Object object);

    List<Object> getAll();

}
