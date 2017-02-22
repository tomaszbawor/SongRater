package com.tbawor.songrater.controller;

import com.tbawor.songrater.domain.Ranking;
import com.tbawor.songrater.repository.RankingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RankingControllerTest {

    @Mock
    RankingRepository rankingRepository;

    @InjectMocks
    RankingController rankingController;

    @Test
    public void shouldSaveRankingByRankingRepository() {
        // given
        Ranking ranking = mockRanking();

        // when
        rankingController.saveRanking(ranking);

        // then
        verify(rankingRepository, times(1)).save(eq(ranking));
    }

    @Test
    public void shouldFindRankingByIdInRepository() {
        // given
        Long rankingId = 321L;
        Ranking ranking = mockRanking();

        doReturn(ranking).when(rankingRepository).findOne(eq(rankingId));

        // when
        Ranking returnedRanking = rankingController.getById(rankingId);

        // then
        verify(rankingRepository, times(1)).findOne(eq(rankingId));
        assertThat(returnedRanking).isEqualTo(ranking);
    }

    private Ranking mockRanking() {
        Ranking ranking = new Ranking();
        ranking.setName("SOME FAKE NAME");
        ranking.setId(123L);
        return ranking;
    }

}