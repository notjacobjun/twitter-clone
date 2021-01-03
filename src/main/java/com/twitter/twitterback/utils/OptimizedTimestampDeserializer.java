package com.twitter.twitterback.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.sql.Timestamp;

public class OptimizedTimestampDeserializer extends JsonDeserializer<Timestamp> {
    @Override
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)  {
        return new Timestamp(System.currentTimeMillis());
    }
}
