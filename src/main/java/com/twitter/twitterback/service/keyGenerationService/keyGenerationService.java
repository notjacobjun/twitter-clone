package com.twitter.twitterback.service.keyGenerationService;

import com.datastax.oss.driver.api.core.CqlSession;
import com.twitter.twitterback.config.CassandraConfig;
import org.springframework.stereotype.Service;

// functional requirements
// Create database tables to hold generated keys one for storing unused keys and one for used keys
// pre generated the keys for the shortened URL's to save runtime
// once a key is used immediately transfer it to the used database, if the operation fails then dump the key and restart the method
// If the key isn't found in the used DB then return a HTTP 404 error, if the key is found then return HTTP 302 redirect

// non functional requirements
// Should be able to work with concurrency, since we use two tables for key storage

@Service
public class keyGenerationService {
    private boolean DBInitialized = false;

    public keyGenerationService() {
        if(DBInitialized == false) {
            createUsedKeyTable();
            createUnusedKeyTable();
            generateKeys();
        }
    }

    public static void createUsedKeyTable() {
        CqlSession session = CassandraConfig.session();
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append("URL-shortener-service used-key-table").append("(")
                // check if this should be type long instead
                .append("hash uuid PRIMARY KEY, ")
                .append("originalURL text,")
                .append("creationDate text,")
                .append("expirationDate date,")
                .append("userID, int);");
        String query = sb.toString();
        session.execute(query);
        System.out.println("Table created!");
    }

    // TODO implement Redis to cache x amount of keys for fast access
    public static void createUnusedKeyTable() {
        CqlSession session = CassandraConfig.session();
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append("URL-shortener-service unused-key-table").append("(")
                .append("ID int PRIMARY KEY, ")
                .append("generatedHash uuid PRIMARY KEY, ")
                .append("used boolean);");
        String query = sb.toString();
        session.execute(query);
    }

    public static void generateKeys() {
        CqlSession session = CassandraConfig.session();
        StringBuilder sb = new StringBuilder("USE URL-shortener-service ")
                .append("INSERT INTO ")
                .append("unused-key-table (ID, uuid, used) ")
                .append("VALUES (uuid(), false");
        String query = sb.toString();
        session.execute(query);
    }
    // TODO add functionality to generate more keys if ran out of pre generated keys
    public String getURL() {
        CqlSession session = CassandraConfig.session();
        StringBuilder sb = new StringBuilder("SELECT uuid FROM unused-key-table WHERE used = false LIMIT 1");
        String query = sb.toString();
        // return the uuid from the unused key table and then mark the used as true
        String currentUUID = session.execute(query).toString();
        setUsedStatus(Long.parseLong(currentUUID));
        return currentUUID;
    }

    public static void setUsedStatus(long currentUUID) {
        CqlSession session = CassandraConfig.session();
        StringBuilder sb = new StringBuilder("UPDATE uuid " +
                "SET used = true" +
                "WHERE uuid = currentUUID");
        String query = sb.toString();
        session.execute(query);
    }

    public void deleteKey(long hashUUID) {
        CqlSession session = CassandraConfig.session();
        String sb = "DELETE FROM used-key-table where hash = hashUUID IF EXISTS";
        session.execute(sb);
    }
}