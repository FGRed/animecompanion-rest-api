package com.animecompanion.dao;

import com.animecompanion.model.AnimeEntity;

import java.util.List;

public interface  AnimeDAO {

    List<AnimeEntity> getAll();
    AnimeEntity get(long id);
    AnimeEntity get(String name);

    void insert(AnimeEntity animeEntity);
    void update(AnimeEntity animeEntity);
    void delete(AnimeEntity animeEntity);
    void delete(long id);

}
