package com.snapci.microblog;

import com.yammer.dropwizard.db.DatabaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

public class MicroBlogDatabaseConfiguration {
    final static Logger logger = LoggerFactory.getLogger(MicroBlogDatabaseConfiguration.class);

    public static DatabaseConfiguration create(String databaseUrl) {
        if (databaseUrl == null) {
            throw new IllegalArgumentException("The DATABASE_URL environment variable must be set before running the app " +
                    "example: DATABASE_URL=\"postgres://sahilm:sahilm@localhost:5432/microblog\"");
        }
        DatabaseConfiguration databaseConfiguration = null;
        try {
            URI dbUri = new URI(databaseUrl);
            String user = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String db = dbUri.getScheme();
            databaseConfiguration = new DatabaseConfiguration();
            if (db.equals("postgres")) {
                db = "postgresql";
                databaseConfiguration.setDriverClass("org.postgresql.Driver");
            } else if(db.equals("mysql")) {
                databaseConfiguration.setDriverClass("com.mysql.jdbc.Driver");
            }
            String url = "jdbc:" + db + "://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            databaseConfiguration.setUser(user);
            databaseConfiguration.setPassword(password);
            databaseConfiguration.setUrl(url);
        } catch (URISyntaxException e) {
            logger.info(e.getMessage());
        }
        return databaseConfiguration;
    }
}