/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoinfraestructura.exception;

/**
 *
 * @author valeria
 */
public class NotFoundConnectionException extends ConnectionException {
    public NotFoundConnectionException(String message) { 
        super(message); 
    }
    
    public NotFoundConnectionException (String message, Exception e) { 
        super(message); 
    }
    
}
