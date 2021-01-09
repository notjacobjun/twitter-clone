package com.twitter.twitterback.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter.twitterback.entity.Tweet;
import com.twitter.twitterback.service.TweetService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tweets")
public class TweetController {
    @Autowired
    private TweetService tweetService;
    private static Logger logger= LoggerFactory.getLogger(TweetController.class);
    private static ObjectMapper objectMapper=new ObjectMapper();
    @GetMapping(value="/findById/{id}")
    public String findById( @PathVariable int id){

        try {
            return objectMapper.writeValueAsString(tweetService.findById(id));
        } catch (JsonProcessingException e) {
            logger.debug("Cannot JsonProcess tweet by id: {}", id);
        }
        return null;
    }
    @GetMapping(value="/findByUserId/{userId}")
    public String findByUserId(@PathVariable int userId){
        try {
            objectMapper.writeValueAsString(tweetService.findByUserId(userId));
        } catch (JsonProcessingException e) {
            logger.info("cannot find tweets of user whose id: {}",userId);
        }
        return null;
    }
}
