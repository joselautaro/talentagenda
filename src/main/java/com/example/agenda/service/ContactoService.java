package com.example.agenda.service;

import com.example.agenda.model.Contacto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ContactoService {
    // simulacion de base de datos en memoria
    private Map<String, Contacto> agenda = new HashMap<>();

    // Devuelve todos los contactos
    public Map<String, Contacto> obtenerContactos() {
        return agenda;
    }

    // Eliminar un contacto, si no es favorito
    public boolean eliminarContacto(String nombre) {
        Contacto contacto = agenda.get(nombre);
        if (contacto != null && !contacto.isFavorito()) {
            agenda.remove(nombre);
            return true; // eliminacion sea exitosa
        }
        return false; // Si es favorito, NO SE PUEDE ELIMINAR
    }

    //Buscar un contacto por nombre
    public Contacto obtenerContactoPorNombre(String nombre){
        return agenda.get(nombre);
    }

    //Actualiza un contacto
    public void actualizarContacto(String nombreOriginal, Contacto contactoActualizado){
        if( !nombreOriginal.equals(contactoActualizado.getNombre()) ){
            agenda.remove(nombreOriginal); //Eliminar el contacto original
        }
        agenda.put(contactoActualizado.getNombre(), contactoActualizado); //guardar el contacto actualizado
    }

    //cambiar el estado favorito de un contacto
    public void cambiarFavorito(String nombre){
        Contacto contacto = agenda.get(nombre);
        if( contacto != null ){
            contacto.setFavorito(!contacto.isFavorito());
        }
    }
}
