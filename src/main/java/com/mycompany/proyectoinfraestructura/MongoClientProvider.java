/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoinfraestructura;


import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.proyectoinfraestructura.exception.NotFoundConnectionException;

/**
 *
 * @author valeria
 */
public enum MongoClientProvider {
    INSTANCE; // Al ser enum hace que solo se cree una sola instancia
    
    private MongoClient client; // Guarda la conexión de MongoDB
    private String dbName = "AmazonDB"; // Nombre de la base de datos
    private String uri = "mongodb://localhost:27017"; // Dirección para conectarse a MongoDB
    
    // Abre la conexión a Mongo una vez y la cierra automáticamente al finalizar
    public synchronized void init() throws NotFoundConnectionException { 
        if (client == null) {
            try {
                MongoClientSettings settings = MongoConfig.buildSettings(this.uri);
                client = MongoClients.create(settings);

                // Verificación rápida: Intentamos pedir algo para ver si realmente conectó
                client.listDatabaseNames().first(); 

                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    if (client != null) client.close();
                }));

            } catch (Exception e) {
                // Si algo falla, lo lanzamos como tu excepción personalizada
                throw new NotFoundConnectionException("Error al iniciar la conexión con MongoDB", e);
            }
        }
    }

    
    public MongoClient client() throws NotFoundConnectionException{
        if(client == null){
            throw new NotFoundConnectionException("El cliente no ha sido inicializado");
        }
        return client;
    }
    
    public MongoDatabase database() throws NotFoundConnectionException{
        return client().getDatabase(this.dbName);
    }
    
    public <T> MongoCollection<T> getCollection(String collectionName, Class<T> clazz) throws NotFoundConnectionException {
        return database().getCollection(collectionName, clazz);
    }
}
