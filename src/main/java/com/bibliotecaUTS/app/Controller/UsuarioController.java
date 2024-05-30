package com.bibliotecaUTS.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.bibliotecaUTS.app.Repository.LibroRepository;
import com.bibliotecaUTS.app.Repository.UsuarioRepository;
import com.bibliotecaUTS.app.Repository.SolicitudesRepository;
import com.bibliotecaUTS.app.Tables.Usuario;
import com.bibliotecaUTS.app.Tables.Libro;
import com.bibliotecaUTS.app.Tables.Solicitudes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SolicitudesRepository solicitudesRepository;

    @GetMapping
    public String usuarioHome(Model model) {
        return "usuario";
    }

    @GetMapping("/solicitarLibro")
    public String solicitarLibro(Model model) {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        List<Libro> listaLibros = libroRepository.findAll();
        model.addAttribute("listaUsuarios", listaUsuarios);
        model.addAttribute("listaLibros", listaLibros);
        return "solicitarLibro";
    }

    @PostMapping("/guardarSolicitud")
    public String guardarSolicitud(@RequestParam("usuario") Integer usuarioId, @RequestParam("libro") Integer libroId, @RequestParam("fechaRegreso") String fechaRegreso) {
        Libro libro = libroRepository.findById(libroId).orElse(null);
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if (libro != null && usuario != null) {
            Solicitudes solicitud = new Solicitudes();
            solicitud.setUsuario(usuario);
            solicitud.setLibro(libro);
            solicitud.setFechaRegreso(LocalDate.parse(fechaRegreso));
            solicitudesRepository.save(solicitud);
        }
        return "redirect:/usuario";
    }

    @GetMapping("/verSolicitudes")
    public String verSolicitudes(Model model) {
        List<Solicitudes> listaSolicitudes = solicitudesRepository.findAll();
        model.addAttribute("listaSolicitudes", listaSolicitudes);
        return "verSolicitudes";
    }

    @GetMapping("/editarSolicitud/{id}")
    public String editarSolicitud(@PathVariable("id") Integer id, Model model) {
        Solicitudes solicitud = solicitudesRepository.findById(id).orElse(null);
        if (solicitud != null) {
            List<Usuario> listaUsuarios = usuarioRepository.findAll();
            List<Libro> listaLibros = libroRepository.findAll();
            model.addAttribute("solicitud", solicitud);
            model.addAttribute("listaUsuarios", listaUsuarios);
            model.addAttribute("listaLibros", listaLibros);
            return "editarSolicitud";
        }
        return "redirect:/usuario/verSolicitudes";
    }

    @PostMapping("/actualizarSolicitud")
    public String actualizarSolicitud(@RequestParam("id") Integer id, @RequestParam("usuario") Integer usuarioId, @RequestParam("libro") Integer libroId, @RequestParam("fechaRegreso") String fechaRegreso) {
        Solicitudes solicitud = solicitudesRepository.findById(id).orElse(null);
        if (solicitud != null) {
            Libro libro = libroRepository.findById(libroId).orElse(null);
            Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
            if (libro != null && usuario != null) {
                solicitud.setUsuario(usuario);
                solicitud.setLibro(libro);
                solicitud.setFechaRegreso(LocalDate.parse(fechaRegreso));
                solicitudesRepository.save(solicitud);
            }
        }
        return "redirect:/usuario/verSolicitudes";
    }

    @GetMapping("/eliminarSolicitud/{id}")
    public String eliminarSolicitud(@PathVariable("id") Integer id) {
        solicitudesRepository.deleteById(id);
        return "redirect:/usuario/verSolicitudes";
    }
}
