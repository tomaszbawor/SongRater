package com.tbawor.songrater.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tbawor.songrater.config.SongRankingSerializer;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonSerialize(using = SongRankingSerializer.class)
public class SongRanking {

    @EmbeddedId
    private SongRankingKey songRankingKey;
    private Long value;
    private Boolean active;

    public SongRanking() {
    }

    public SongRanking(SongRankingKey songRankingKey, Long value) {
        this.songRankingKey = songRankingKey;
        this.value = value;
        this.active = false;
    }

    public SongRanking(SongRankingKey songRankingKey, Long value, Boolean active) {
        this.songRankingKey = songRankingKey;
        this.value = value;
        this.active = active;
    }

    public SongRankingKey getSongRankingKey() {
        return songRankingKey;
    }

    public void setSongRankingKey(SongRankingKey songRankingKey) {
        this.songRankingKey = songRankingKey;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Embeddable
    public static class SongRankingKey implements Serializable {
        @ManyToOne
        private Ranking ranking;
        @ManyToOne
        private Song song;

        public SongRankingKey() {
        }

        public SongRankingKey(Ranking ranking, Song song) {
            this.ranking = ranking;
            this.song = song;
        }

        public Ranking getRanking() {
            return ranking;
        }

        public Song getSong() {
            return song;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SongRankingKey that = (SongRankingKey) o;
            return Objects.equals(ranking, that.ranking) &&
                    Objects.equals(song, that.song);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ranking, song);
        }
    }
}
