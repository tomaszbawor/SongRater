package com.tbawor.songrater.domain;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SongRankingTest {

    @Test
    public void shouldBeNotActiveByDefault() {
        // given
        Song song = new Song();
        Ranking ranking = new Ranking();

        // when
        SongRanking sr = new SongRanking(new SongRanking.SongRankingKey(ranking, song), 0L);

        // then
        assertThat(sr.getActive()).isFalse();
    }

}