package com.animecompanion.dao;

import com.animecompanion.model.EpisodeEntity;

import java.util.List;

public interface EpisodeDAO {
    void insert(EpisodeEntity episodeEntity);
    void update(EpisodeEntity episodeEntity);

    EpisodeEntity get(long id);
    EpisodeEntity get(String name);
    EpisodeEntity get(EpisodeEntity episodeEntity);

    void delete(long id);
    void delete(EpisodeEntity episodeEntity);

    List<EpisodeEntity> getAll();

}
