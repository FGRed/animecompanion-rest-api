package com.animecompanion.hibernate;

import com.animecompanion.model.AnimeEntity;
import com.animecompanion.dao.AnimeDAO;
import com.animecompanion.dao.AnimeDAOImpl;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;

public class HibernateTest {
    @Autowired
    AnimeDAO animeDAO = new AnimeDAOImpl();


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, UnirestException {
       // new HibernateTest().nonStaticText();
        HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/episode/progress/")
                .field("parameter", "value")
                .field("firstname", "Gary")
                .asJson();
        System.out.println(response.getBody().toString());
    }


    public void nonStaticText(){
        AnimeEntity animeEntity = new AnimeEntity("Dragonball");
        animeEntity.setJapanenseName("ドラゴンボール");
        animeEntity.setCover("https://images-na.ssl-images-amazon.com/images/I/81pUdMt2E6L._SL1500_.jpg");
        animeEntity.setGenres("Action");
        animeEntity.setBrand("Toei Animation");

        AnimeEntity animeEntity1 = new AnimeEntity("One Punch Man");
        animeEntity1.setJapanenseName("ワンパンマン");
        animeEntity1.setCover("https://cdn.europosters.eu/image/1300/julisteet/one-punch-man-destruction-i61133.jpg");
        animeEntity1.setBrand("Madhouse");
        animeEntity.setGenres("Action, Comedy, Shounen");
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
