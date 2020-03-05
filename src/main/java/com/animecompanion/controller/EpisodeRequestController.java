package com.animecompanion.controller;

import com.animecompanion.dao.EpisodeDAO;
import com.animecompanion.model.EpisodeEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
        return episodeDAO.getAll();
    }



    @GetMapping("anime/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        EpisodeEntity episodeEntity = episodeDAO.get(id);
        return ResponseEntity.ok().body(episodeEntity);
    }

    @RequestMapping(value = "/anime/search/{name}")
    public ResponseEntity<?> get(@PathVariable("name") String name){
        EpisodeEntity episodeEntity = episodeDAO.get(name);
        return ResponseEntity.ok().body(episodeEntity);
    }
    @RequestMapping("anime/delete/{id}")
    public void delete(@PathVariable(value = "id") int id){
        episodeDAO.delete(id);
    }
}
