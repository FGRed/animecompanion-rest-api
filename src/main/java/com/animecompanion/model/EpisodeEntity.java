package com.animecompanion.model;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Data
public class EpisodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "episode_name")
    private String episodeName;
    @Column(name = "air_date")
    private String airDate;
    @Column(name = "episode_thumnbail")
    private String episodeThumbnail;
    @Column(name = "episode_location")
    private String episodeLocation;
    @Column(name = "is_seen")
    private boolean isSeen;
    @Column(name = "broacast_time")
    private String broadcastTime;
    @Column(name = "creation_time")
    private String creationTime;
    @Column(name = "rating")
    private String rating;
    @Column(name = "time_pos")
    private String timePos;
    @Column(name = "progress")
    private String progress;
    @Column(name = "link")
    private String link;
    @Column(name = "duration")
    private String duration;
    @Column(name = "parent_anime_id")
    private long parentAnimeId;

}
