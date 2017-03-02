package com.tbawor.songrater.controller;

import com.tbawor.songrater.domain.Ranking;
import com.tbawor.songrater.domain.Song;
import com.tbawor.songrater.repository.RankingRepository;
import com.tbawor.songrater.service.RankingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RankingControllerTest {

    @Mock
    RankingService rankingService;

    @InjectMocks
    RankingController rankingController;

    @Test
    public void shouldSaveRankingByRankingService() {
        // given
        Ranking ranking = mockRanking();

        // when
        rankingController.saveRanking(ranking);

        // then
        verify(rankingService, times(1)).saveRanking(eq(ranking));
    }

    @Test
    public void shouldFindRankingByIdInService() {
        // given
        Long rankingId = 321L;
        Ranking ranking = mockRanking();

        doReturn(ranking).when(rankingService).findRankingById(eq(rankingId));

        // when
        Ranking returnedRanking = rankingController.getById(rankingId);

        // then
        verify(rankingService, times(1)).findRankingById(eq(rankingId));
        assertThat(returnedRanking).isEqualTo(ranking);
    }

    @Test
    public void shouldGetTopFiveFromService() {
        // given
        List<Song> top5List = (List<Song>)mock(List.class);
        Long rankingId = 123L;

        doReturn(top5List).when(rankingService).getTopFiveForRankingWithId(eq(rankingId));

        // when
        List<Song> returnedTopFive = rankingController.getTopFive(rankingId);

        // then
        verify(rankingService).getTopFiveForRankingWithId(eq(rankingId));
        assertThat(returnedTopFive).isEqualTo(top5List);
    }

    private Ranking mockRanking() {
        Ranking ranking = new Ranking();
        ranking.setName("SOME FAKE NAME");
        ranking.setId(123L);
        return ranking;
    }

}