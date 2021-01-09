package com.twitter.twitterback.service;

import com.twitter.twitterback.entity.Tweet;

import java.util.List;

public interface TweetService {


    public Tweet findById(int id);
    public List<Tweet> findByUserId(int userId);
}
