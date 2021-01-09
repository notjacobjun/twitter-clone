package com.twitter.twitterback.cassandra.connector;


import org.apache.catalina.Cluster;
import org.apache.commons.lang3.builder.Builder;
import org.hibernate.Session;

public class CassandraConnector {

    private Cluster cluster;

    private Session session;

    public void connect(String node, Integer port) {
        // not able to define correct dependency for this Builder class to work
        Builder b = Cluster.builder().addContactPoint(node);
        if (port != null) {
            b.withPort(port);
        }
        cluster = b.build();

        session = cluster.connect();
    }

    public Session getSession() {
        return this.session;
    }

    public void close() {
        session.close();
        cluster.close();
    }
}
