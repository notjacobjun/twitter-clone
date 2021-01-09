package com.twitter.twitterback.URLShortener;

//functional requirements
//Generate shorter and unique aliases of the longer URL's
//When the users click the short link they will be redirected to the original link
//        Users should optionally be able to pick a custom short link for their URL
//        Links will expire after a standard default timespan. Users should be able to specify the expiration time.

//Optionally we should be able to add metrics for how many shortURL's are created and such


//Nonfunctional requirements
//        The system should be highly available. This is required because, if our service is down, all the URL redirections will start failing.
//        URL redirection should happen in real-time with minimal latency.
//        Shortened links should not be guessable (not predictable).


import com.datastax.oss.driver.api.core.CqlSession;
import com.twitter.twitterback.config.CassandraConfig;
import com.twitter.twitterback.service.keyGenerationService.keyGenerationService;


public class TweetURLShortener {
    private boolean databaseInitialized = false;
    private keyGenerationService keyGenerationService = new keyGenerationService();

    public TweetURLShortener() {
        if (!databaseInitialized) {
            // TODO make sure that these are the optimal replication settings
            createKeyspace("URL-shortener-service", "NetworkTopologyStrategy", 2);
            // remove the parameters from these methods, if you want to
            createURLCreatorTable("URL-creator");
            databaseInitialized = true;
        }
    }

    // customAlias, username, and expirationDate are optional parameters by allowing nulls for the method
    // TODO throw an error if the method was unsuccessful
    // TODO limit each user by their devKey to a certain amount of request per some time period
    public String createURL(String devKey, String originalURL, String customAlias, String username, String expirationDate) {
        customAlias = customAlias != null ? customAlias : "";
        username = username != null ? username : "";
        expirationDate = expirationDate != null ? expirationDate : "";
        String newURL = keyGenerationService.getURL();
        return newURL;
    }

    public void deleteURL(long hashUUID) {
        keyGenerationService.deleteKey(hashUUID);
    }

    public static void createKeyspace(
            String keyspaceName, String replicationStrategy, int replicationFactor) {
        CqlSession session = CassandraConfig.session();
        StringBuilder sb =
                new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                        .append(keyspaceName).append(" WITH replication = {")
                        .append("'class':'").append(replicationStrategy)
                        .append("','replication_factor':").append(replicationFactor)
                        .append("};");
        String query = sb.toString();
        session.execute(query);
    }

    public void createURLCreatorTable(String TABLE_NAME) {
        CqlSession session = CassandraConfig.session();
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME).append("(")
                .append("id uuid PRIMARY KEY, ")
                .append("name text,")
                .append("tweetID int);");
        String query = sb.toString();
        session.execute(query);
    }

    public void alterTable(String TABLE_NAME, String columnName, String columnType) {
        CqlSession session = CassandraConfig.session();
        StringBuilder sb = new StringBuilder("ALTER TABLE ")
                .append(TABLE_NAME).append(" ADD ")
                .append(columnName + " ")
                .append(columnType + ";");
        String query = sb.toString();
        session.execute(query);
    }
}