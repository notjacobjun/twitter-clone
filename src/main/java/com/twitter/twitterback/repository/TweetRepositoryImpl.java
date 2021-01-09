package com.twitter.twitterback.repository;

import com.twitter.twitterback.entity.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.cassandra.core.query.Criteria.where;
import static org.springframework.data.cassandra.core.query.Query.query;

@Repository
public class TweetRepositoryImpl implements TweetRepository {

    private static Logger logger = LoggerFactory.getLogger(TweetRepositoryImpl.class);

    public static CassandraOperations cassandraTemplate;

    @Autowired
    public TweetRepositoryImpl(CassandraOperations cassandraTemplate) {
        TweetRepositoryImpl.cassandraTemplate = cassandraTemplate;
    }

    @Override
    public <S extends Tweet> S save(S s) {
        return null;
    }

    @Override
    public <S extends Tweet> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Tweet> findById(Integer integer) {
        return null;
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Tweet> findAll() {
        return null;
    }

    @Override
    public List<Tweet> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Tweet tweet) {

    }

    @Override
    public void deleteAll(Iterable<? extends Tweet> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Slice<Tweet> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Tweet> S insert(S s) {
        return null;
    }

    @Override
    public <S extends Tweet> List<S> insert(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Tweet findById(int id) {


       Tweet tweet = null;
        try {
            List<Tweet> tweetList = cassandraTemplate.select(query(where("id").is(id)), Tweet.class);
            if (tweetList.isEmpty() || tweetList.get(0).getStatus().equals("D")) {
                logger.info("Cannot find tweet by id: {}", id);
                return null;
            }
            tweet=tweetList.get(0);
        } catch (Exception e) {
            logger.info("Some problem with cassandraQuery : ",e);
        }


        return tweet;
    }

    @Override
    public List<Tweet> findByUserId(int user_Id) {


        try {
            List<Tweet> tweets = cassandraTemplate.select(query(where("user_id").is(user_Id)), Tweet.class);
            return tweets.stream().filter(tweet -> tweet.getStatus().equals("A")).collect(Collectors.toList());
        } catch (Exception e) {
            logger.info("Some problem with cassandraQuery : ",e);
        }
        return null;
    }
}
