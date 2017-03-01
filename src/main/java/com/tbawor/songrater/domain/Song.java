package com.tbawor.songrater.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Song {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String title;

    private String url;

    @OneToMany(mappedBy = "songRankingKey.song")
    private Set<SongRanking> songRankings;

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
