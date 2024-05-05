package com.example.demo.Connector;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ConnectionPoolSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.concurrent.TimeUnit;

@Configuration
public class MongoConnector {

    @Value("${User.username}")
    private String username;

    @Value("${User.password}")
    private String password;

    @Value("${User.database}")
    private String database;

    @Value("${Application.AppName}")
    private String applicationName;

    @Value("${Application.DatabaseName}")
    private String databaseName;

    @Bean
    public MongoClient mongoClient() throws Exception{
        MongoCredential cred = MongoCredential.createCredential(username, database, password.toCharArray());

        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .retryWrites(true)
                .applyConnectionString(new ConnectionString("mongodb+srv://" + username+ ":" + password + "@" + database + ".msfgcme.mongodb.net/"))
                .applyToConnectionPoolSettings((ConnectionPoolSettings.Builder builder) -> {
                    builder.maxSize(100) //connections count
                            .minSize(5)
                            .maxConnectionLifeTime(30, TimeUnit.MINUTES);
                })
                .applyToSocketSettings(builder -> {
                    builder.connectTimeout(2000, TimeUnit.MILLISECONDS);
                })
                .applicationName(applicationName)
                .build();

        return MongoClients.create(clientSettings);

    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), "Warhammer40k");
    }


}