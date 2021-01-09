package com.twitter.twitterback.service;

import com.twitter.twitterback.entity.Tweet;
import com.twitter.twitterback.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TweetServiceImpl implements TweetService {
    @Autowired
    private TweetRepository tweetRepository;



    @Override
    public Tweet findById(int id) {
        return this.tweetRepository.findById(id);
    }

    @Override
    public List<Tweet> findByUserId(int userId) {
        return this.tweetRepository.findByUserId(userId);
    }
}
