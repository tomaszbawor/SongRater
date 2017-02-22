package com.tbawor.songrater.controller;

import com.tbawor.songrater.domain.Ranking;
import com.tbawor.songrater.repository.RankingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(RankingController.class)
public class RankingControllerTestIT {

    private static final String RANKINGS_URL = "/api/rankings/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RankingRepository repository;

    @Test
    public void shouldProperlyGetOneRanking() throws Exception {
        // given
        long searchedRankingId = 12L;
        doReturn(mockRanking()).when(repository).findOne(eq(searchedRankingId));

        // when
        mockMvc.perform(get(RANKINGS_URL + searchedRankingId)
                .accept(MediaType.APPLICATION_JSON_UTF8))

        // then
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{'id': 12, 'name':'Some Name'}".replace('\'', '"')));

        verify(repository, times(1)).findOne(eq(searchedRankingId));
    }

    private Ranking mockRanking() {
        Ranking ranking = new Ranking();
        ranking.setId(12L);
        ranking.setName("Some Name");
        return ranking;
    }

}