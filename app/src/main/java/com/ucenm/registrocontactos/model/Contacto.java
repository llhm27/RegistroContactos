package com.ucenm.registrocontactos.model;

public class Contacto {
    private final String nombre;
    private final String correo;
    private final String telefono;
    private final String opcion;

    public Contacto(String nombre, String correo, String telefono, String opcion) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.opcion = opcion;
    }

    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }
    public String getOpcion() { return opcion; }
}
