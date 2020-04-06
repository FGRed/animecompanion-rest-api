package com.animecompanion.dao;

import com.animecompanion.hibernate.HibernateUtil;
import com.animecompanion.model.AnimeEntity;
import com.animecompanion.model.EpisodeEntity;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Service
public class AnimeDAOImpl implements AnimeDAO {

    @Autowired
    EpisodeDAO episodeDAO;

    public AnimeDAOImpl(){

    }

    @Override
    public List<AnimeEntity> getAll() {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<AnimeEntity> animeCriteriaQuery = cb.createQuery(AnimeEntity.class);
            Root<AnimeEntity> rootEntry = animeCriteriaQuery.from(AnimeEntity.class);
            CriteriaQuery<AnimeEntity> all = animeCriteriaQuery.select(rootEntry);

            TypedQuery<AnimeEntity> allQuery = session.createQuery(all);
            return allQuery.getResultList();

    }

    @Override
    public AnimeEntity getWeps(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{

            AnimeEntity animeEntity = session.get(AnimeEntity.class, id);

            return animeEntity;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;


    }

    @Override
    public AnimeEntity get(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
        return session.get(AnimeEntity.class, id);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
    return null;
    }

    @Override
    public AnimeEntity get(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(AnimeEntity.class);
        return (AnimeEntity) criteria.add(Restrictions.eq("name", name)).uniqueResult();
    }

    @Override
    public void insert(AnimeEntity animeEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(animeEntity);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(AnimeEntity animeEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(animeEntity);
        transaction.commit();

    }

    @Override
    public void delete(AnimeEntity animeEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(animeEntity);
        transaction.commit();
    }

    @Override
    public void delete(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE Anime WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
    }
}
