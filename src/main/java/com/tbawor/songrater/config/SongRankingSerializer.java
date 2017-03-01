package com.tbawor.songrater.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tbawor.songrater.domain.SongRanking;

import java.io.IOException;


public class SongRankingSerializer extends JsonSerializer<SongRanking> {

    @Override
    public void serialize(SongRanking songRanking, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("rankingId", songRanking.getSongRankingKey().getRanking().getId());
        jsonGenerator.writeNumberField("songId", songRanking.getSongRankingKey().getSong().getId());
        jsonGenerator.writeNumberField("value", songRanking.getValue());

        jsonGenerator.writeEndObject();
    }
}
