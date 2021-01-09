
package com.twitter.twitterback.config;
import com.datastax.oss.driver.api.core.CqlSession;
import com.twitter.twitterback.TwitterbackApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = "com.twitter.twitterback.repository")
public class CassandraConfig  {


    @Bean
    public static CassandraOperations cassandraTemplate(){

             return (CassandraOperations)new CassandraTemplate(cqlSession());

    }
    @Bean
    private static CqlSession cqlSession(){
        return CqlSession.builder().withCloudSecureConnectBundle(TwitterbackApplication.class.getClassLoader().getResource("secure-connect-twitterclone.zip")).withAuthCredentials("twitterclone","12341234aA?").withKeyspace("twt").build();

    }

}