package com.tbawor.songrater.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Ranking {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "songRankingKey.ranking")
    private Set<SongRanking> songRankings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SongRanking> getSongRankings() {
        return songRankings;
    }

    public void setSongRankings(Set<SongRanking> songRankings) {
        this.songRankings = songRankings;
    }
}
