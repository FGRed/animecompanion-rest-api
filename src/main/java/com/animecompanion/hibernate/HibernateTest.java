package com.animecompanion.hibernate;

import com.animecompanion.model.AnimeEntity;
import com.animecompanion.dao.AnimeDAO;
import com.animecompanion.dao.AnimeDAOImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;

public class HibernateTest {
    @Autowired
    AnimeDAO animeDAO = new AnimeDAOImpl();


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        new HibernateTest().nonStaticText();
    }


    public void nonStaticText(){
        AnimeEntity animeEntity = new AnimeEntity("Dragonball");
        animeEntity.setJapanenseName("ドラゴンボール");
        animeEntity.setCover("https://images-na.ssl-images-amazon.com/images/I/81pUdMt2E6L._SL1500_.jpg");
        animeEntity.setBrand("Toei Animation");

        AnimeEntity animeEntity1 = new AnimeEntity("One Punch Man");
        animeEntity1.setJapanenseName("ワンパンマン");
        animeEntity1.setCover("https://cdn.europosters.eu/image/1300/julisteet/one-punch-man-destruction-i61133.jpg");
        animeEntity1.setBrand("Madhouse");

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(animeEntity);
            session.save(animeEntity1);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        animeDAO.getAll();
        for (AnimeEntity animeEntity0 : animeDAO.getAll()){
            System.out.println(animeEntity0.getName());
        }

    }


}