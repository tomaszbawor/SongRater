package com.tbawor.songrater.repository;

import com.tbawor.songrater.domain.Ranking;
import com.tbawor.songrater.domain.Song;
import com.tbawor.songrater.domain.SongRanking;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class SongRankingRepositoryTestIT {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private SongRankingRepository songRankingRepository;

    @Test
    public void shouldGetSongRankingByRankingId() {
        // given

        Ranking ranking = new Ranking();
        ranking.setName("Test Ranking Name");

        Song song = new Song();
        song.setTitle("Test Song Title");

        testEntityManager.persist(ranking);
        testEntityManager.persist(song);

        SongRanking songRanking = new SongRanking(new SongRanking.SongRankingKey(ranking, song), 0L);

        testEntityManager.persist(songRanking);

        Long rankingId = ranking.getId();

        // when

        List<SongRanking> searchResult = songRankingRepository.findBySongRankingKeyRankingId(rankingId);

        // then
        assertThat(searchResult).contains(songRanking);
        assertThat(searchResult).hasSize(1);
    }

}