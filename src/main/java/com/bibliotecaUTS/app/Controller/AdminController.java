package com.bibliotecaUTS.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bibliotecaUTS.app.Repository.LibroRepository;
import com.bibliotecaUTS.app.Repository.UsuarioRepository;
import com.bibliotecaUTS.app.Tables.Usuario;
import com.bibliotecaUTS.app.Tables.Libro;

@Controller
@RequestMapping("/administrador")
public class AdminController {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String adminHome() {
        return "administrador";
    }

    @GetMapping("/verLibros")
    public String listarLibros(Model model) {
        List<Libro> listaLibros = libroRepository.findAll();
        model.addAttribute("listaLibros", listaLibros);
        return "verLibros";
    }

    @GetMapping("/verLibros/formLibros")
    public String mostrarLibro(Model model) {
        model.addAttribute("libro", new Libro());
        return "formLibros";
    }

    @PostMapping("/guardarLibro")
    public String guardarLibro(@ModelAttribute Libro libro) {
        libroRepository.save(libro);
        return "redirect:/administrador/verLibros";
    }

    @GetMapping("/verLibros/editar/{id}")
    public String modificarLibro(@PathVariable("id") Integer id, Model model) {
        Libro libro = libroRepository.findById(id).orElse(null);
        if (libro != null) {
            model.addAttribute("libro", libro);
            return "formLibros";
        } else {
            return "redirect:/administrador/verLibros";
        }
    }

    @GetMapping("/verLibros/eliminar/{id}")
    public String eliminarLibro(@PathVariable("id") Integer id) {
        libroRepository.deleteById(id);
        return "redirect:/administrador/verLibros";
    }

    @GetMapping("/verUsuarios")
    public String listarUsuarios(Model model) {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        model.addAttribute("listaUsuarios", listaUsuarios);
        return "verUsuarios";
    }

    @GetMapping("/verUsuarios/formUsuario")
    public String mostrarUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "formUsuario";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/administrador/verUsuarios";
    }

    @GetMapping("/verUsuarios/editar/{id}")
    public String modificarUsuario(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            return "formUsuario";
        } else {
            return "redirect:/administrador/verUsuarios";
        }
    }

    @GetMapping("/verUsuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Integer id) {
        usuarioRepository.deleteById(id);
        return "redirect:/administrador/verUsuarios";
    }
}
