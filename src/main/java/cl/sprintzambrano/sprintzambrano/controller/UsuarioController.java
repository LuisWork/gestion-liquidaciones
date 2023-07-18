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

    @GetMapping("/{idUsuario}")
    public String buscarUsuarioPorId(@PathVariable int idUsuario, Model model) { //se utiliza para capturar el valor del ID del usuario desde la URL y asignarlo a la variable idUsuario.
        Usuario usuario = objUsuarioService.buscarUsuarioPorId(idUsuario); //Este método busca el usuario en la base de datos utilizando el ID proporcionado y devuelve un objeto Usuario
        model.addAttribute("usuario", usuario); //se agrega este objeto Usuario al modelo con el nombre "usuario" usando el método addAttribute() del objeto model
        return "redirect:/usuario";
    }

    // Método para mostrar el formulario de edición de un usuario por su ID
    @PostMapping("/editar/{idUsuario}")
    public String mostrarFormularioEditarUsuario(@PathVariable int idUsuario, Model model) {
        model.addAttribute("usuario", objUsuarioService.buscarUsuarioPorId(idUsuario)); //llamo al método buscarUsuarioPorId(idUsuario) del servicio IUsuarioService para obtener el objeto Usuario que se desea editar.
        return "editarUsuario";
    }

    // Método para actualizar un usuario
    @PostMapping("/actualizar/{idUsuario}")
    public String actualizarUsuario(@ModelAttribute Usuario usuario, @PathVariable int idUsuario) { //@ModelAttribute Usuario usuario y @PathVariable int idUsuario se utilizan para capturar el objeto Usuario con los datos actualizados y el ID del usuario desde la URL, respectivamente.
        usuario.setFechaCreacion(LocalDateTime.now());
        objUsuarioService.actualizarUsuario(usuario, idUsuario); //llamo al método actualizarUsuario(usuario, idUsuario) del servicio IUsuarioService, pasando el objeto Usuario actualizado y el ID del usuario para actualizar los datos en la base de datos.
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
