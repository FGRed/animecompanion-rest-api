package com.animecompanion.dao;

import com.animecompanion.hibernate.HibernateUtil;
import com.animecompanion.model.AnimeEntity;
import com.animecompanion.model.EpisodeEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.util.List;
@Service
public class EpisodeDAOImpl implements EpisodeDAO {
    @Override
    public void insert(EpisodeEntity episodeEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(episodeEntity);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(EpisodeEntity episodeEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(episodeEntity);
        transaction.commit();
    }

    @Override
    public EpisodeEntity get(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            return session.get(EpisodeEntity.class, id);
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
    public EpisodeEntity get(String name) {
        return null;
    }

    @Override
    public EpisodeEntity get(EpisodeEntity episodeEntity) {
        return null;
    }

    @Override
    public List<EpisodeEntity> getByParent(@NotNull final long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<EpisodeEntity> query = session.createQuery("from EpisodeEntity epe where " +
                "epe.parentAnimeId=:pid", EpisodeEntity.class);
        query.setParameter("pid", id);
        return query.list();
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void delete(EpisodeEntity episodeEntity) {

    }

    @Override
    public List<EpisodeEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<EpisodeEntity> animeCriteriaQuery = cb.createQuery(EpisodeEntity.class);
        Root<EpisodeEntity> rootEntry = animeCriteriaQuery.from(EpisodeEntity.class);
        CriteriaQuery<EpisodeEntity> all = animeCriteriaQuery.select(rootEntry);

        TypedQuery<EpisodeEntity> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }
    public float getProgress(long id){
      //  Criteria criteria = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria();
    return 0f;
    }
}
