package com.tbawor.songrater.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class RankingSerializationTestIT {

    @Autowired
    private JacksonTester<Ranking> json;

    @Test
    public void shouldProperlySerializeRanking() throws IOException {
        // given
        Ranking ranking = new Ranking();
        ranking.setId(1L);
        ranking.setName("Test Name");

        // when
        JsonContent<Ranking> serializedContent = json.write(ranking);

        // then
        assertThat(serializedContent)
                .extractingJsonPathStringValue("@.name")
                .isEqualTo("Test Name");

        assertThat(serializedContent)
                .extractingJsonPathNumberValue("@.id")
                .isEqualTo(1);
    }

    @Test
    public void shouldProperlyDeserializeRanking() throws IOException {
        // given
        String content = "{'id': 3 , 'name': 'Best Ranking'}".replace('\'', '"');

        // when
        ObjectContent<Ranking> parsedRanking = json.parse(content);

        // then
        assertThat(parsedRanking.getObject().getId()).isEqualTo(3L);
        assertThat(parsedRanking.getObject().getName()).isEqualTo("Best Ranking");
    }

}