package com.animecompanion.controller;

import ch.qos.logback.classic.Logger;
import com.animecompanion.dao.EpisodeDAO;
import com.animecompanion.model.EpisodeEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@RestController
public class EpisodeRequestController {
    @Autowired
    EpisodeDAO episodeDAO;

    @PostMapping(path = "episode/update", consumes = "application/json")
    public String updateAnime(@RequestParam("entity") String jsonString, Error error){
        jsonString = jsonString.substring(2, jsonString.length()-1)+"}";
        try {
            episodeDAO.update(new ObjectMapper().readValue(jsonString, EpisodeEntity.class));
        } catch (IOException e) {
            return e.getStackTrace().toString();

        }
        return null;
    }

    @PostMapping(path = "episode/create", consumes = "application/json", produces = "text/plain")
    public String create(@RequestParam("entity") String jsonString, Error error) throws IOException {
        jsonString = jsonString.substring(2, jsonString.length()-1)+"}";
        try {
            EpisodeEntity episodeEntity = new ObjectMapper().readValue(jsonString, EpisodeEntity.class);
            episodeDAO.insert(episodeEntity);
        }catch (Exception ex){
            return "Error inserting entity!";
        }
        return "Success!";
    }

    @GetMapping(value = "episode/all", produces = "application/json")
    public List<EpisodeEntity> getAll(){
        List<EpisodeEntity> retVal = episodeDAO.getAll();
        return retVal;
    }

    @GetMapping("episode/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        EpisodeEntity episodeEntity = episodeDAO.get(id);
        return ResponseEntity.ok().body(episodeEntity);
    }

    @GetMapping("episode/parent/{id}")
    public ResponseEntity<?> getByParent(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        List<EpisodeEntity> episodeEntityList = episodeDAO.getByParent(id);
        return ResponseEntity.ok().body(episodeEntityList);
    }



    @RequestMapping(value = "/episode/search/{name}")
    public ResponseEntity<?> get(@PathVariable("name") String name){
        EpisodeEntity episodeEntity = episodeDAO.get(name);
        return ResponseEntity.ok().body(episodeEntity);
    }
    @RequestMapping("episode/delete/{id}")
    public void delete(@PathVariable(value = "id") int id){
        episodeDAO.delete(id);
    }

    @RequestMapping("episode/get-progress/{id}")
    public void getProgress(@PathVariable(value = "id") int id){
        episodeDAO.delete(id);
    }

    @RequestMapping("/episode/progress/")
    public void setProgress(HttpServletRequest request){

        String progress = request.getParameter("progress");
        long id = Long.valueOf(request.getParameter("id"));
        if(Integer.valueOf(progress) <= 100) {
            EpisodeEntity episodeEntity = episodeDAO.get(id);
            episodeEntity.setProgress(progress);
            episodeDAO.update(episodeEntity);
            System.out.println("Success!");
        }else{
            System.out.println("Cannot be over 100% percent!");
        }
    }
}
