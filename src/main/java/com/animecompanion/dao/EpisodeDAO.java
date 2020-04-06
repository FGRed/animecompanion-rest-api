package com.animecompanion.dao;

import com.animecompanion.model.EpisodeEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EpisodeDAO {
    void insert(EpisodeEntity episodeEntity);
    void update(EpisodeEntity episodeEntity);

    EpisodeEntity get(long id);
    EpisodeEntity get(String name);
    EpisodeEntity get(EpisodeEntity episodeEntity);
    List<EpisodeEntity> getByParent(long id);

    void delete(long id);
    void delete(EpisodeEntity episodeEntity);

    List<EpisodeEntity> getAll();


}
