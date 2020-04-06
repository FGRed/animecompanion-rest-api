package com.animecompanion.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class AnimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "cover")
    private String cover;
    @Column(name = "japanese_name")
    private String japanenseName;
    @Column(name = "summary")
    private String summary;
    @Column(name = "broadcast_Type")
    private String broadcastType;
    @Column(name = "show_type")
    private String showType;
    @Column(name = "new_show_location")
    private String newShowLocation;
    @Column(name = "website_url")
    private String websiteUrl;
    @Column(name = "brand")
    private String brand;
    @Column(name ="related_shows")
    private String relatedShows;
    @Column(name = "genres")
    private String genres;



    public AnimeEntity(String name) { this.name = name; }
    public AnimeEntity(){}
}
