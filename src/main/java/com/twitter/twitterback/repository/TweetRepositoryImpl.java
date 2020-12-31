package com.twitter.twitterback.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.twitter.twitterback.config.CassandraConfig;
import com.twitter.twitterback.entity.Tweet;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class TweetRepositoryImpl implements TweetRepository {
    @Override
    public List<Tweet> findById(int id) {
        return null;
    }

    @Override
    public List<Tweet> findByUserId(int user_Id) {
        return null;
    }

    @Override
    public void saveToCassandra(Tweet tweet) {
        CqlSession cqlSession = CassandraConfig.session();
        CassandraOperations template = new CassandraTemplate(cqlSession);

        Tweet currentTweet = template.insert(tweet);

        cqlSession.close();
    }

    @Override
    public <S extends Tweet> List<S> saveAll(Iterable<S> iterable) {
        return null;
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
    public <S extends Tweet> S save(S s) {
        return null;
    }

    @Override
    public Optional<Tweet> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
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
}
