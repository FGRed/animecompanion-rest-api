package com.animecompanion.model;

import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnimeRDTO {

    private int id;
    private int malId;
    @NotBlank
    private String name;
    private String japaneseName;
    private String summary;
    private String showType;
    private String broadcastType;
    private String cover;

    private int episodeGap;
    private int episodeAmount;
    private Date airDate;

    private List<Integer> episodeIDList = new ArrayList();

    public AnimeRDTO(int id, String name){
        this.id = id;
        this.name = name;
    }

    public AnimeRDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMalId() {
        return malId;
    }

    public void setMalId(int malId) {
        this.malId = malId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJapaneseName() {
        return japaneseName;
    }

    public void setJapaneseName(String japaneseName) {
        this.japaneseName = japaneseName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getBroadcastType() {
        return broadcastType;
    }

    public void setBroadcastType(String broadcastType) {
        this.broadcastType = broadcastType;
    }

    public int getEpisodeGap() {
        return episodeGap;
    }

    public void setEpisodeGap(int episodeGap) {
        this.episodeGap = episodeGap;
    }

    public Date getAirDate() {
        return airDate;
    }

    public void setAirDate(Date airDate) {
        this.airDate = airDate;
    }

    public List<Integer> getEpisodeIDList() {
        return episodeIDList;
    }

    public void setEpisodeIDList(List<Integer> episodeIDList) {
        this.episodeIDList = episodeIDList;
    }

    public int getEpisodeAmount() {
        return episodeAmount;
    }

    public void setEpisodeAmount(int episodeAmount) {
        this.episodeAmount = episodeAmount;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
