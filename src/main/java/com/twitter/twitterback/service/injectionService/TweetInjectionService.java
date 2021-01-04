package com.twitter.twitterback.service.injectionService;

// functionality: Take the tweet from the user when they click submit and enter it into the Cassandra
// database
// Also enter the event into Kafka


import com.twitter.twitterback.entity.Tweet;

public interface TweetInjectionService {

    public void saveTweetToCassandra(Tweet tweet);

    public void enterTweetIntoKafka(Tweet tweet);


}
