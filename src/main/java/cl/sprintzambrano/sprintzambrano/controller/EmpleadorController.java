package cl.sprintzambrano.sprintzambrano.controller;

import cl.sprintzambrano.sprintzambrano.entity.Empleador;
import cl.sprintzambrano.sprintzambrano.entity.Usuario;
import cl.sprintzambrano.sprintzambrano.service.IEmpleadorService;
import cl.sprintzambrano.sprintzambrano.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/empleador")
public class EmpleadorController {
    @Autowired
    IEmpleadorService objEmpleadorService;

    @Autowired
    IUsuarioService objUsuarioService;

    @GetMapping
    public String listarEmpleadores(Model model){
        List<Empleador> listaEmpleadores = objEmpleadorService.listarEmpleadores();
        model.addAttribute("empleadores", listaEmpleadores);
        return "listarEmpleadores";
    }

    @GetMapping("/crearEmpleador")
    public String mostrarFormularioCrearEmpleador(Model model){
        List<Usuario> usuarios = objUsuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("empleador", new Empleador());
        return "crearEmpleador";
    }

    @PostMapping("/crearEmpleador")
    public String crearEmpleador(@ModelAttribute Empleador empleador, @RequestParam("idUsuario") int idUsuario){
        Usuario usuario = objUsuarioService.buscarUsuarioPorId(idUsuario);
        empleador.setUsuario(usuario);
        objEmpleadorService.crearEmpleador(empleador);
        return "redirect:/empleador";
    }

    @GetMapping("/{idEmpleador}")
    public String buscarEmpleadorPorId(@PathVariable int idEmpleador, Model model) {
        Empleador empleador = objEmpleadorService.buscarEmpleadorPorId(idEmpleador);
        model.addAttribute("empleador", empleador);
        return "redirect:/empleador";
    }

    @PostMapping("/editar/{idEmpleador}")
    public String mostrarFormEditarEmpleador(@PathVariable int idEmpleador, Model model) {
        model.addAttribute("empleador", objEmpleadorService.buscarEmpleadorPorId(idEmpleador));
        List<Usuario> usuarios = objUsuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "editarEmpleador";
    }
    @PostMapping("/actualizar/{idEmpleador}")
    public String actualizarEmpleador(@ModelAttribute Empleador empleador, @PathVariable int idEmpleador, @RequestParam("idUsuario") int idUsuario) {
        Usuario usuario = objUsuarioService.buscarUsuarioPorId(idUsuario);
        empleador.setUsuario(usuario);
        objEmpleadorService.actualizarEmpleador(empleador, idEmpleador);
        return "redirect:/empleador";
    }

    @GetMapping("/{idEmpleador}/eliminar")
    public String mostrarEliminarEmpleador(@PathVariable int idEmpleador, Model model){
        Empleador empleadorEliminar = objEmpleadorService.buscarEmpleadorPorId(idEmpleador);
        model.addAttribute("empleador", empleadorEliminar);
        return "eliminarEmpleador";
    }

    @PostMapping("/eliminar/{idEmpleador}")
    public String eliminarEmpleadorPorId(@PathVariable int idEmpleador) {
        objEmpleadorService.eliminarEmpleador2(idEmpleador);
        return "redirect:/empleador";
    }
}
