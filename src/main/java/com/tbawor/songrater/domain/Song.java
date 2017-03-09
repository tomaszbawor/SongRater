package com.tbawor.songrater.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Song {

    private static final long MAX_ACTIVE_RANKINGS = 3;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String title;

    private String url;

    @OneToMany(mappedBy = "songRankingKey.song")
    private Set<SongRanking> songRankings = new HashSet<>();

    public boolean canBeAddedToRanking() {
        return songRankings.stream().filter(SongRanking::getActive).count() < MAX_ACTIVE_RANKINGS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<SongRanking> getSongRankings() {
        return songRankings;
    }

    public void setSongRankings(Set<SongRanking> songRankings) {
        this.songRankings = songRankings;
    }
}
