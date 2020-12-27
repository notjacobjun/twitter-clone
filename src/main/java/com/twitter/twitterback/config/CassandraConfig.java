
package com.twitter.twitterback.config;
import com.datastax.oss.driver.api.core.CqlSession;
import com.twitter.twitterback.TwitterbackApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = "com.twitter.twitterback.repository")
public class CassandraConfig  {
    public static CqlSession cqlSession;
    public @Bean CqlSession session(){
         cqlSession=CqlSession.builder().withCloudSecureConnectBundle(TwitterbackApplication.class.getClassLoader().getResource("secure-connect-twitterclone.zip")).withAuthCredentials("twitterclone","12341234aA?").withKeyspace("twt").build();
      return cqlSession;
    }

}