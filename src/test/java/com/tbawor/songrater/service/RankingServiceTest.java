package com.tbawor.songrater.service;


import com.tbawor.songrater.domain.Ranking;
import com.tbawor.songrater.domain.Song;
import com.tbawor.songrater.domain.SongRanking;
import com.tbawor.songrater.repository.RankingRepository;
import com.tbawor.songrater.repository.SongRankingRepository;
import com.tbawor.songrater.repository.SongRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RankingServiceTest {

    @InjectMocks
    private RankingService service;

    @Mock
    private RankingRepository rankingRepository;

    @Mock
    private SongRankingRepository songRankingRepository;

    @Mock
    private SongRepository songRepository;

    @Test
    public void shouldFindInRepositoryWhileLookingForRankingById() {
        // given
        Long rankingId = 123L;

        // when
        service.findRankingById(rankingId);

        // then
        verify(rankingRepository, times(1)).findOne(eq(rankingId));
    }

    @Test
    public void shouldSaveRankingInRepository() {
        // given
        Ranking rankingToSave = new Ranking();

        // when
        service.saveRanking(rankingToSave);

        // then
        verify(rankingRepository, times(1)).save(eq(rankingToSave));
    }

    @Test
    public void shouldProperlyAddSongToRating() {
        // given
        Ranking mockRanking = new Ranking();
        Long rankingId = 123L;
        Song songToAdd = new Song();

        doReturn(mockRanking).when(rankingRepository).findOne(eq(rankingId));

        // when
        SongRanking songRanking = service.addSongToRanking(rankingId, songToAdd);

        // then
        verify(rankingRepository).findOne(eq(rankingId));

        assertThat(songRanking.getSongRankingKey().getRanking()).isEqualTo(mockRanking);
        assertThat(songRanking.getSongRankingKey().getSong()).isEqualTo(songToAdd);
        assertThat(songRanking.getValue()).isEqualTo(0L);
    }

    @Test
    public void shouldProperlyUpVoteSong() {
        // given
        ArgumentCaptor<SongRanking> songRankingCaptor = ArgumentCaptor.forClass(SongRanking.class);

        Long rankingId = 123L;
        Long songId = 234L;

        Song stubSong = new Song();
        Ranking stubRanking = new Ranking();

        SongRanking stubSongRanking = prepareStubSongRanking(stubRanking, stubSong);

        doReturn(stubSong).when(songRepository).findOne(eq(songId));
        doReturn(stubRanking).when(rankingRepository).findOne(eq(rankingId));
        doReturn(stubSongRanking).when(songRankingRepository).findOne(eq(new SongRanking.SongRankingKey(stubRanking, stubSong)));

        // when
        service.upvoteSongInRanking(rankingId, songId);

        // then
        verify(songRankingRepository).save(songRankingCaptor.capture());

        assertThat(songRankingCaptor.getValue().getValue()).isEqualTo(1L);
        assertThat(songRankingCaptor.getValue().getSongRankingKey().getRanking()).isEqualTo(stubRanking);
        assertThat(songRankingCaptor.getValue().getSongRankingKey().getSong()).isEqualTo(stubSong);
    }

    @Test
    public void shouldProperlyDownVoteSong() {
        // given
        ArgumentCaptor<SongRanking> songRankingCaptor = ArgumentCaptor.forClass(SongRanking.class);

        Long rankingId = 123L;
        Long songId = 234L;

        Song stubSong = new Song();
        Ranking stubRanking = new Ranking();

        SongRanking stubSongRanking = prepareStubSongRanking(stubRanking, stubSong);

        doReturn(stubSong).when(songRepository).findOne(eq(songId));
        doReturn(stubRanking).when(rankingRepository).findOne(eq(rankingId));
        doReturn(stubSongRanking).when(songRankingRepository).findOne(eq(new SongRanking.SongRankingKey(stubRanking, stubSong)));

        // when
        service.downvoteSongInRanking(rankingId, songId);

        // then
        verify(songRankingRepository).save(songRankingCaptor.capture());

        assertThat(songRankingCaptor.getValue().getValue()).isEqualTo(-1L);
        assertThat(songRankingCaptor.getValue().getSongRankingKey().getRanking()).isEqualTo(stubRanking);
        assertThat(songRankingCaptor.getValue().getSongRankingKey().getSong()).isEqualTo(stubSong);
    }

    private SongRanking prepareStubSongRanking(Ranking stubRanking, Song stubSong) {
        return new SongRanking(new SongRanking.SongRankingKey(stubRanking, stubSong), 0L);
    }

}