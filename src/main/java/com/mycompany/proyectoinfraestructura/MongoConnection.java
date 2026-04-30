package com.mycompany.proyectoinfraestructura;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

    private static MongoConnection instance;
    private final MongoClient client;

    private static final String URI = "mongodb://localhost:27017";

    // Constructor privado - Singleton
    private MongoConnection() {
        ConnectionString connString = new ConnectionString(URI);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .applyToConnectionPoolSettings(builder ->
                        builder.maxSize(10)          // máx conexiones simultáneas
                               .minSize(2))           // mín conexiones en pool
                .applyToSocketSettings(builder ->
                        builder.connectTimeout(5, java.util.concurrent.TimeUnit.SECONDS)
                               .readTimeout(10, java.util.concurrent.TimeUnit.SECONDS))
                .build();

        this.client = MongoClients.create(settings);
    }

    // Obtener instancia única
    public static MongoConnection getInstance() {
        if (instance == null) {
            synchronized (MongoConnection.class) {
                if (instance == null) {
                    instance = new MongoConnection();
                }
            }
        }
        return instance;
    }

    // Obtener una base de datos específica
    public MongoDatabase getDatabase(String dbName) {
        return client.getDatabase(dbName);
    }

    // Cerrar conexión
    public void close() {
        if (client != null) {
            client.close();
            instance = null;
        }
    }
}
