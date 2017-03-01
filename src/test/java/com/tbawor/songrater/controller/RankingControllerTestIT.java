package com.tbawor.songrater.controller;

import com.tbawor.songrater.domain.Ranking;
import com.tbawor.songrater.domain.Song;
import com.tbawor.songrater.service.RankingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
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
    private RankingService rankingService;

    @Test
    public void shouldProperlyGetOneRanking() throws Exception {
        // given
        long searchedRankingId = 12L;
        doReturn(mockRanking()).when(rankingService).findRankingById(eq(searchedRankingId));

        // when
        mockMvc.perform(get(RANKINGS_URL + searchedRankingId)
                .accept(MediaType.APPLICATION_JSON_UTF8))

        // then
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{'id': 12, 'name':'Some Name'}".replace('\'', '"')));

        verify(rankingService, times(1)).findRankingById(eq(searchedRankingId));
    }

    @Test
    public void shouldProperlySaveRanking() throws Exception {
        // given
        ArgumentCaptor<Ranking> rankingCaptor = ArgumentCaptor.forClass(Ranking.class);

        // when
        mockMvc.perform(post(RANKINGS_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{'name': 'Some Interesting Ranking'}".replace('\'', '"')));

        // then
        verify(rankingService).saveRanking(rankingCaptor.capture());
        assertThat(rankingCaptor.getValue().getName()).isEqualTo("Some Interesting Ranking");
        assertThat(rankingCaptor.getValue().getId()).isNull();
    }

    @Test
    public void shouldProperlyHandleAddingSongToRanking() throws Exception {
        // given
        ArgumentCaptor<Long> rankingIdCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Song> songToAddCaptor = ArgumentCaptor.forClass(Song.class);

        // when
        mockMvc.perform(post(RANKINGS_URL + "124/songs")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{'id': 432, 'title': 'Cebula', 'url':'fakeUrl'}".replace('\'', '"')));

        // then
        verify(rankingService).addSongToRanking(rankingIdCaptor.capture(), songToAddCaptor.capture());

        assertThat(rankingIdCaptor.getValue()).isEqualTo(124L);
        assertThat(songToAddCaptor.getValue().getId()).isEqualTo(432L);
        assertThat(songToAddCaptor.getValue().getTitle()).isEqualTo("Cebula");
        assertThat(songToAddCaptor.getValue().getUrl()).isEqualTo("fakeUrl");
    }

    @Test
    public void shouldProperlyHandleUpVotingSongs() throws Exception {
        // given
        ArgumentCaptor<Long> rankingIdCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Long> songIdCaptor = ArgumentCaptor.forClass(Long.class);

        // when
        mockMvc.perform(post(RANKINGS_URL + "123/songs/456/upvote"));

        // then
        verify(rankingService).upvoteSongInRanking(rankingIdCaptor.capture(), songIdCaptor.capture());

        assertThat(rankingIdCaptor.getValue()).isEqualTo(123L);
        assertThat(songIdCaptor.getValue()).isEqualTo(456L);
    }

    @Test
    public void shouldProperlyHandleDownVotingSongs() throws Exception {
        // given
        ArgumentCaptor<Long> rankingIdCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Long> songIdCaptor = ArgumentCaptor.forClass(Long.class);

        // when
        mockMvc.perform(post(RANKINGS_URL + "123/songs/456/downvote"));

        // then
        verify(rankingService).downvoteSongInRanking(rankingIdCaptor.capture(), songIdCaptor.capture());

        assertThat(rankingIdCaptor.getValue()).isEqualTo(123L);
        assertThat(songIdCaptor.getValue()).isEqualTo(456L);
    }

    private Ranking mockRanking() {
        Ranking ranking = new Ranking();
        ranking.setId(12L);
        ranking.setName("Some Name");
        return ranking;
    }

}