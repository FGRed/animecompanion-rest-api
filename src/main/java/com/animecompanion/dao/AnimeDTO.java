package com.animecompanion.dao;

import com.animecompanion.model.AnimeEntity;
import com.animecompanion.model.EpisodeEntity;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class AnimeDTO {

    @NonNull
    private AnimeEntity animeEntity;
    private List<EpisodeEntity> episodeEntities;

    public AnimeDTO(final @NonNull AnimeEntity animeEntity,
                    final List<EpisodeEntity> episodeEntities){
        setAnimeEntity(animeEntity);
        setEpisodeEntities(episodeEntities);

    }

}
