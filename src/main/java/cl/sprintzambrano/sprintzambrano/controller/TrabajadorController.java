package cl.sprintzambrano.sprintzambrano.controller;

import cl.sprintzambrano.sprintzambrano.entity.Empleador;
import cl.sprintzambrano.sprintzambrano.entity.InstitucionPrevision;
import cl.sprintzambrano.sprintzambrano.entity.InstitucionSalud;
import cl.sprintzambrano.sprintzambrano.entity.Trabajador;
import cl.sprintzambrano.sprintzambrano.service.IEmpleadorService;
import cl.sprintzambrano.sprintzambrano.service.IPrevisionService;
import cl.sprintzambrano.sprintzambrano.service.ISaludService;
import cl.sprintzambrano.sprintzambrano.service.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/trabajador")
public class TrabajadorController {
    @Autowired
    ITrabajadorService objTrabajadorService;

    @Autowired
    IPrevisionService objPrevisionService;

    @Autowired
    ISaludService objSaludService;
    @Autowired
    IEmpleadorService objEmpleadorService;

    @GetMapping
    public String listarTrabajadores(Model model){
        List<Trabajador> listaTrabajadores = objTrabajadorService.listarTrabajadores();
        model.addAttribute("trabajadores", listaTrabajadores);
        return "listarTrabajadores";
    }

    @GetMapping("/crearTrabajador")
    public String mostrarFormCrearTrabajador(Model model){
        List<InstitucionPrevision> prevision = objPrevisionService.listarPrevision();
        List<InstitucionSalud> salud = objSaludService.listarSalud();
        List<Empleador> empleador = objEmpleadorService.listarEmpleadores();
        model.addAttribute("prevision", prevision);
        model.addAttribute("salud", salud);
        model.addAttribute("empleador", empleador);
        model.addAttribute("trabajador", new Trabajador());
        return "crearTrabajador";
    }

    @PostMapping("/crearTrabajador")
    public String crearTrabajador(@ModelAttribute Trabajador trabajador,
                                  @RequestParam("idPrevision") int idPrevision,
                                  @RequestParam("idSalud") int idSalud,
                                  @RequestParam("idEmpleador") int idEmpleador){
        InstitucionPrevision prevision = objPrevisionService.buscarPrevisionPorId(idPrevision);
        InstitucionSalud salud = objSaludService.buscarSaludPorId(idSalud);
        Empleador empleador = objEmpleadorService.buscarEmpleadorPorId(idEmpleador);
        trabajador.setInstPrevision(prevision);
        trabajador.setInstSalud(salud);
        List<Empleador> listaEmpleadores = new ArrayList<>();
        listaEmpleadores.add(empleador);
        trabajador.setListaEmpleadores(listaEmpleadores);
        objTrabajadorService.crearTrabajador(trabajador);
        return "redirect:/trabajador";
    }

    @GetMapping("/{idTrabajador}")
    public String buscarTrabajadorPorId(@PathVariable int idTrabajador, Model model){
        Trabajador trabajador = objTrabajadorService.buscarTrabajadorId(idTrabajador);
        model.addAttribute("trabajador", trabajador);
        return "redirect:/trabajador";
    }

    @PostMapping("/editar/{idTrabajador}")
    public String mostrarFormTrabajador(@PathVariable int idTrabajador, Model model){
        model.addAttribute("trabajador", objTrabajadorService.buscarTrabajadorId(idTrabajador));
        List<InstitucionPrevision> prevision = objPrevisionService.listarPrevision();
        List<InstitucionSalud> salud = objSaludService.listarSalud();
        List<Empleador> empleador = objEmpleadorService.listarEmpleadores();
        model.addAttribute("prevision", prevision);
        model.addAttribute("salud", salud);
        model.addAttribute("empleador", empleador);
        return "editarTrabajador";
    }

    @PostMapping("/actualizar/{idTrabajador}")
    public String actualizarTrabajador(@ModelAttribute Trabajador trabajador,
                                       @PathVariable int idTrabajador,
                                       @RequestParam("idPrevision") int idPrevision,
                                       @RequestParam("idSalud") int idSalud,
                                       @RequestParam("idEmpleador") int idEmpleador){
        InstitucionPrevision prevision = objPrevisionService.buscarPrevisionPorId(idPrevision);
        InstitucionSalud salud = objSaludService.buscarSaludPorId(idSalud);
        Empleador empleador = objEmpleadorService.buscarEmpleadorPorId(idEmpleador);
        trabajador.setInstPrevision(prevision);
        trabajador.setInstSalud(salud);
        List<Empleador> listaEmpleadores = new ArrayList<>();
        listaEmpleadores.add(empleador);
        trabajador.setListaEmpleadores(listaEmpleadores);
        objTrabajadorService.actualizarTrabajador(trabajador, idTrabajador);
        return "redirect:/trabajador";
    }

    @PostMapping("/eliminar/{idTrabajador}")
    public String eliminarTrabajadorPorId(@PathVariable int idTrabajador){
        objTrabajadorService.eliminarTrabajador(idTrabajador);
        return "redirect:/trabajador";
    }
}
