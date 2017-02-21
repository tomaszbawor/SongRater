package com.tbawor.songrater.controller;

import com.tbawor.songrater.domain.Ranking;
import com.tbawor.songrater.repository.RankingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RankingControllerTest {

    @Mock
    RankingRepository rankingRepository;

    @InjectMocks
    RankingController rankingController = new RankingController();

    @Test
    public void shouldSaveRankingByRankingRepository() {
        // given
        Ranking ranking = new Ranking();

        // when
        rankingController.saveRanking(ranking);

        // then
        verify(rankingRepository, times(1)).save(eq(ranking));
    }

}