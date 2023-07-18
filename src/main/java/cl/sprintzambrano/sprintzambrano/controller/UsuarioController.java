package cl.sprintzambrano.sprintzambrano.controller;


import cl.sprintzambrano.sprintzambrano.entity.Usuario;
import cl.sprintzambrano.sprintzambrano.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    IUsuarioService objUsuarioService;

    @GetMapping
    public String listarUsuarios(Model model){
        List<Usuario> listaUsuarios = objUsuarioService.listarUsuarios();
        model.addAttribute("usuarios", listaUsuarios);
        return "listarUsuarios";
    }

    @GetMapping("/crearUsuario")
    public String mostrarFormularioCrearUsuario(Model model){
        return "crearUsuario";
    }

    @PostMapping("/crearUsuario")
    public String crearUsuario(@ModelAttribute Usuario usuario){
        usuario.setFechaCreacion(LocalDateTime.now());
        objUsuarioService.crearUsuario(usuario);
        return "redirect:/usuario";
    }

    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) { return "registro"; }

    @PostMapping("/registrar")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuario.setFechaCreacion(LocalDateTime.now());
        objUsuarioService.registrarUsuario(usuario);
        return "redirect:/login";
    }

    @GetMapping("/{idUsuario}/editar")
    public String mostrarFormularioEditarUsuario(@PathVariable int idUsuario, Model model){
        Usuario usuarioParaEditar = objUsuarioService.buscarUsuarioPorId(idUsuario);
        model.addAttribute("usuario", usuarioParaEditar);
        return "editarUsuario";
    }

    @PostMapping("/{idUsuario}/editar")
    public String actualizarUsuario(@PathVariable int idUsuario, @ModelAttribute Usuario usuario){
        objUsuarioService.actualizarUsuario2(usuario);
        return "redirect:/usuario";
    }

    @GetMapping("/{idUsuario}/eliminar")
    public String mostrarEliminarUsuario(@PathVariable int idUsuario, Model model) {
        Usuario usuarioEliminar = objUsuarioService.buscarUsuarioPorId(idUsuario);
        model.addAttribute("usuario", usuarioEliminar);
        return "eliminarUsuario";
    }

    @PostMapping("/eliminar/{idUsuario}")
    public String eliminarUsuarioPorId(@PathVariable int idUsuario){
        objUsuarioService.eliminarUsuario2(idUsuario);
        return "redirect:/usuario";
    }

}
