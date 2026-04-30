/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoinfraestructura.exceptions;

/**
 *
 * @author valeria
 */
public class ConnectionException extends Exception {
    public ConnectionException(String message) { 
        super(message); 
    }
    public ConnectionException(String message, Throwable cause) { 
        super(message, cause); 
    }
    
}
