/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectoinfraestructura;

/**
 *
 * @author Equipo 1
 */
public class ProyectoInfraestructura {

    public static void main(String[] args) {
        db.usuarios.insertMany([
  { nombre: "Ana García", email: "ana@email.com", edad: 28, activo: true, creado: new Date("2024-01-15") },
  { nombre: "Carlos López", email: "carlos@email.com", edad: 34, activo: true, creado: new Date("2024-02-20") },
  { nombre: "María Pérez", email: "maria@email.com", edad: 22, activo: false, creado: new Date("2024-03-10") }
])

    }
}
