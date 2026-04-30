/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoinfraestructura.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.proyectoinfraestructura.exceptions.ConnectionException;

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
    public synchronized void init(){
        if(client == null){ // Si el cliente no existe (si la conexión no esta creada todavia) se crea una
            client = MongoClients.create(this.uri); // Cambiar ya que este listo el MongoConfig por la linea de abajo
            //client = MongoClients.create(MongoConfig.buildSettings(this.uri));
            
            Runtime.getRuntime().addShutdownHook(new Thread(() ->{ // registra algo que se ejecuta cuando la app se cierra
                try{
                    client.close(); // Cierra la conexión cuando el programa termina
                } catch(Exception e){
                   e.printStackTrace();
                }
            }));
        }
    }
    
    public MongoClient client(){
        if(client == null){
            throw new IllegalStateException("El cliente no ha sido inicializado");
        }
        return client;
    }
    
    public MongoDatabase database(){
        return client().getDatabase(this.dbName);
    }
    
    public <T> MongoCollection<T> getCollection(String collectionName, Class<T> clazz){
        if(client == null){
            throw new IllegalStateException("El cliente no ha sido inicializado");
        }
        MongoDatabase db = client().getDatabase(this.dbName);
        return db.getCollection(collectionName, clazz);
    }
}
