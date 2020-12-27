package com.twitter.twitterback.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter.twitterback.entity.Tweet;
import com.twitter.twitterback.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/tweets")
public class TweetController {
    @Autowired
    private TweetRepository tweetRepository;

    @PostMapping(value="/save" ,produces = "application/json")
    public boolean saveANewTweetWithoutMedia( @RequestBody String tweet){
        ObjectMapper om=new ObjectMapper();
        Tweet tweet1= null;
        try {
            tweet1 = om.readValue(tweet, Tweet.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO tweedId should be fetched from url shortener service.
        assert tweet1 != null;
        tweetRepository.insert(tweet1);
        return true;
    }
}
