package com.tbawor.songrater.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class SongRankingSerializationTestIT {

    private static final Long RANKING_ID = 12L;
    private static final Long SONG_ID = 34L;
    private static final Long RANKING_VALUE = 23L;

    @Autowired
    private JacksonTester<SongRanking> json;

    @Test
    public void shouldProperlySerializeSongRankingObject() throws IOException {
        // given
        Ranking ranking = new Ranking();
        ranking.setId(RANKING_ID);
        Song song = new Song();
        song.setId(SONG_ID);

        SongRanking songRanking = new SongRanking(new SongRanking.SongRankingKey(ranking, song), RANKING_VALUE);

        // when
        JsonContent<SongRanking> serializedContent = json.write(songRanking);

        // then
        assertThat(serializedContent).extractingJsonPathNumberValue("@.rankingId").isEqualTo(RANKING_ID.intValue());
        assertThat(serializedContent).extractingJsonPathNumberValue("@.songId").isEqualTo(SONG_ID.intValue());
        assertThat(serializedContent).extractingJsonPathNumberValue("@.value").isEqualTo(RANKING_VALUE.intValue());
    }

}