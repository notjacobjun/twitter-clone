package com.twitter.twitterback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.twitter.twitterback.utils.OptimizedTimestampDeserializer;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
@Data
@Table("tweets")
public class Tweet {
    @Id
    @PrimaryKey
    @Column("id")
    @GeneratedValue
    @JsonIgnore
    private int id;
    @Column("parent_tweet_id")
    private int parentTweetId;
    @Column("tweet_type")
    private String  tweetType;
    @Column("user_id")
    private int userId;
    @Column("content")
    private String content;
    @Column("path_to_media")
    private String pathToMedia;
    @Column("creation_date")
    @JsonDeserialize(using= OptimizedTimestampDeserializer.class)
    private java.sql.Timestamp creationDate;
    @Column("status")
    private String status;
    public Tweet() {

    }



    public Tweet(int id, int parentTweetId, String tweetType, int userId, String content, String pathToMedia, Timestamp creationDate, String status) {
        this.id = id;
        this.parentTweetId = parentTweetId;
        this.tweetType = tweetType;
        this.userId = userId;
        this.content = content;
        this.pathToMedia = pathToMedia;
        this.creationDate = creationDate;
        this.status = status;
    }

}
