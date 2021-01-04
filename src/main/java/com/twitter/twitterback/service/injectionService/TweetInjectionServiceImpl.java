package com.twitter.twitterback.service.injectionService;

import com.twitter.twitterback.entity.Tweet;
import com.twitter.twitterback.repository.TweetRepository;
import com.twitter.twitterback.service.injectionService.TweetInjectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TweetInjectionServiceImpl implements TweetInjectionService {

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    @Transactional
    public void saveTweetToCassandra(Tweet tweet) {
         tweetRepository.insert(tweet);
    }

    @Override
    public void enterTweetIntoKafka(Tweet tweet) {
        // implement later when Kafka is created
    }
}
