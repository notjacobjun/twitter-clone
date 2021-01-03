package com.twitter.twitterback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.twitter.twitterback.utils.OptimizedBooleanDeserializer;
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
    private String id;
    @Column("parent_tweet_id")
    private String parentTweetId;
    @Column("has_parent")
    @JsonDeserialize(using= OptimizedBooleanDeserializer.class)
    private boolean hasParent;
    @Column("user_id")
    private int userId;
    @Column("content")
    private String content;
    @Column("path_to_media")
    private String pathToMedia;
    @Column("creation_date")
    @JsonDeserialize(using= OptimizedTimestampDeserializer.class)
    private java.sql.Timestamp creationDate;

    public Tweet() {

    }

    @Column("status")
    private String status;

    public Tweet(String id, String parentTweetId, boolean hasParent, int userId, String content, String pathToMedia, Timestamp creationDate, String status) {
        this.id = id;
        this.parentTweetId = parentTweetId;
        this.hasParent = hasParent;
        this.userId = userId;
        this.content = content;
        this.pathToMedia = pathToMedia;
        this.creationDate = creationDate;
        this.status = status;
    }

}
