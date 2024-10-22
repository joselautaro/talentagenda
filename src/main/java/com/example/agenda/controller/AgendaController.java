package com.example.agenda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.agenda.model.Contacto;
import com.example.agenda.service.ContactoService;

@Controller
@RequestMapping("/agenda")
public class AgendaController {

    private final ContactoService contactoService;

    //inyectar el servicio a traves del constructor
    public AgendaController(ContactoService contactoService){
        this.contactoService = contactoService;
    }

    //muestra la lista de contactos
    @GetMapping("/")
    public String mostrarContactos(Model model, @ModelAttribute("message") String message){
        model.addAttribute("agenda", contactoService.obtenerContactos());
        model.addAttribute("message", message);
        return "agenda";
    }

    //mostrar el formulario para agregar un nuevo contacto
    @GetMapping("/nuevo")
    public String nuevoContactoForm(Model model){
        model.addAttribute("contacto", new Contacto());
        return "nuevoContacto";
    }

    //guardar un contacto en la agenda
    @PostMapping("/guardar")
    public String guardarContacto( @ModelAttribute Contacto contacto, RedirectAttributes redirectAttributes ){
        contactoService.guardarContacto(contacto);
        redirectAttributes.addFlashAttribute("message", "Contacto guardado con éxito");
        return "redirect:/agenda/";
    }

    //elimina el contacto en la agenda, solo si no es contacto favorito
    @PostMapping("/eliminar/{nombre}")
    public String eliminarContacto(@PathVariable("nombre") String nombre, RedirectAttributes redirectAttributes){
        boolean eliminado = contactoService.eliminarContacto(nombre);
        if( eliminado ){
            redirectAttributes.addFlashAttribute("message", "Contacto eliminado con éxito");
        }else{
            redirectAttributes.addFlashAttribute("message", "No se pudo eliminar un contacto");
        }
        return "redirect:/agenda/";
    }
    
    //muestra el formulario para editar un contacto
    @GetMapping("/editar/{nombre}")
    public String editarContacto(@PathVariable("nombre") String nombre, Model model){
        Contacto contacto = contactoService.obtenerContactoPorNombre(nombre);
        model.addAttribute("contacto", contacto);
        return "editarContacto";
    }
    //actualiza los datos del contacto editado
    @PostMapping("/actualizar/{nombre}")
    public String actualizarContacto(@PathVariable("nombre")String nombreOriginal, @ModelAttribute Contacto contactoActualizado, RedirectAttributes redirectAttributes){
        contactoService.actualizarContacto(nombreOriginal, contactoActualizado);
        redirectAttributes.addFlashAttribute("message", "Contacto actualizado con éxito");
        return "redirect:/agenda/";
    }

    //cambiar el estado de favorito de un contacto
    @PostMapping("/favorito/{nombre}")
    public String cambiarFavorito(@PathVariable("nombre")String nombre, RedirectAttributes redirectAttributes){
        contactoService.cambiarFavorito(nombre);
        redirectAttributes.addFlashAttribute("message", "Estado de favorito actualizado");
        return "redirect:/agenda/";
    }
}
