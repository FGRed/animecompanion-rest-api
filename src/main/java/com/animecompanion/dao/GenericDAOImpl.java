package com.animecompanion.dao;

import com.animecompanion.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericDAOImpl implements GenericDAO {

    @Override
    public void insert(Object object) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
    }

    @Override
    public Object get(Long id) {
        return null;
    }

    @Override
    public Object get(String name) {
        return null;
    }

    @Override
    public Object get(Object object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Object object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
    }

    @Override
    public List<Object> getAll() {
        return null;
    }
}
