package com.animecompanion.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.animecompanion.model.AnimeEntity;
import com.animecompanion.model.SearchCriteria;
import com.animecompanion.dao.AnimeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.List;

@RestController
public class AnimeRequestController {

    @Autowired
    AnimeDAO animeDAO;


    @PostMapping(path = "anime/update", consumes = "application/json")
    public String updateAnime(@RequestParam("entity") String jsonString, Error error){
        jsonString = jsonString.substring(2, jsonString.length()-1)+"}";
        try {
            animeDAO.update(new ObjectMapper().readValue(jsonString, AnimeEntity.class));
        } catch (IOException e) {
            return e.getStackTrace().toString();

        }
        return null;
    }

    @PostMapping(path = "anime/create", consumes = "application/json", produces = "text/plain")
    public String create(@RequestParam("entity") String jsonString, Error error) throws IOException {
        jsonString = jsonString.substring(2, jsonString.length()-1)+"}";
        try {
            AnimeEntity animeEntity = new ObjectMapper().readValue(jsonString, AnimeEntity.class);
            animeDAO.insert(animeEntity);
        }catch (Exception ex){
            return "Error inserting entity!";
        }
        return "Success!";
    }

    @GetMapping(value = "anime/all", produces = "application/json")
    public List<AnimeEntity> getAll(){
        return animeDAO.getAll();
    }



    @GetMapping("anime/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        AnimeEntity animeEntity = animeDAO.get(id);
        return ResponseEntity.ok().body(animeEntity);
    }

    @RequestMapping(value = "/anime/search/{name}")
    public ResponseEntity<?> get(@PathVariable("name") String name){
        AnimeEntity animeEntity = animeDAO.get(name);
        return ResponseEntity.ok().body(animeEntity);
    }
    @RequestMapping("anime/delete/{id}")
    public void delete(@PathVariable(value = "id") int id){
        animeDAO.delete(id);
    }
}
