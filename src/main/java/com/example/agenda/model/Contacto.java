package com.example.agenda.model;

public class Contacto {

    private String nombre;
    private String telefono;
    private String email;
    private boolean favorito;

    public Contacto(String nombre, String telefono, String email, boolean favorito){
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.favorito = favorito;
    }

    public Contacto(){}

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getTelefono(){
        return telefono;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public boolean isFavorito(){
        return favorito;
    }

    public void setFavorito(boolean favorito){
        this.favorito = favorito;
    }
    
}
