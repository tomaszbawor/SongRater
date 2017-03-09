package com.tbawor.songrater.domain;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SongTest {


    @Test
    public void shouldAllowToAddToRankingWhenGotLessThanThreeActiveRankings() {
        // given
        Song song = new Song();
        song.setSongRankings(createFourActiveSongRankings(song));

        // when
        boolean canBeAddedToRanking = song.canBeAddedToRanking();

        // then
        assertThat(canBeAddedToRanking).isFalse();
    }


    @Test
    public void shouldNotAllowToAddToRankingWhenGotMoreThanThreeActiveRankings() {
        // given
        Song song = new Song();
        song.setSongRankings(createFourInactiveSongRankings(song));

        // when
        boolean canBeAddedToRanking = song.canBeAddedToRanking();

        // then
        assertThat(canBeAddedToRanking).isTrue();
    }


    private Set<SongRanking> createFourActiveSongRankings(Song song) {
        Set<SongRanking> rankings = new HashSet<>();

        rankings.add(new SongRanking(new SongRanking.SongRankingKey(new Ranking(), song), 12L, true));
        rankings.add(new SongRanking(new SongRanking.SongRankingKey(new Ranking(), song), 12L, true));
        rankings.add(new SongRanking(new SongRanking.SongRankingKey(new Ranking(), song), 12L, true));
        rankings.add(new SongRanking(new SongRanking.SongRankingKey(new Ranking(), song), 12L, true));
        rankings.add(new SongRanking(new SongRanking.SongRankingKey(new Ranking(), song), 12L, true));

        return rankings;
    }

    private Set<SongRanking> createFourInactiveSongRankings(Song song) {
        Set<SongRanking> rankings = new HashSet<>();

        rankings.add(new SongRanking(new SongRanking.SongRankingKey(new Ranking(), song), 12L, false));
        rankings.add(new SongRanking(new SongRanking.SongRankingKey(new Ranking(), song), 12L, false));
        rankings.add(new SongRanking(new SongRanking.SongRankingKey(new Ranking(), song), 12L, false));
        rankings.add(new SongRanking(new SongRanking.SongRankingKey(new Ranking(), song), 12L, false));
        rankings.add(new SongRanking(new SongRanking.SongRankingKey(new Ranking(), song), 12L, false));

        return rankings;
    }

}