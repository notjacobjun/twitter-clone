package com.twitter.twitterback.repository;

import com.twitter.twitterback.entity.Tweet;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TweetRepository extends CassandraRepository<Tweet,Integer> {
    List<Tweet>  findById(final int id);

    List<Tweet> findByUserId(final int user_Id);

    public void saveToCassandra(Tweet tweet);
}
