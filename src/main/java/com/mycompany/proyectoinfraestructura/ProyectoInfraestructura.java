/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectoinfraestructura;

import com.mycompany.proyectoinfraestructura.exception.NotFoundConnectionException;

/**
 *
 * @author Equipo 1
 */
public class ProyectoInfraestructura {

    public static void main(String[] args) {
        try {
            MongoClientProvider.INSTANCE.init();

            System.out.println("Conexión exitosa.");
        } catch (NotFoundConnectionException e) {
            System.err.println("¡CRÍTICO! No se pudo arrancar la infraestructura: " + e.getMessage());
        }
          

    }
}
