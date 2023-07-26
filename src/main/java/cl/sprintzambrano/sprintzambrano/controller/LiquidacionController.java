package cl.sprintzambrano.sprintzambrano.controller;

import cl.sprintzambrano.sprintzambrano.entity.InstitucionPrevision;
import cl.sprintzambrano.sprintzambrano.entity.InstitucionSalud;
import cl.sprintzambrano.sprintzambrano.entity.Liquidacion;
import cl.sprintzambrano.sprintzambrano.entity.Trabajador;
import cl.sprintzambrano.sprintzambrano.service.ILiquidacionService;
import cl.sprintzambrano.sprintzambrano.service.IPrevisionService;
import cl.sprintzambrano.sprintzambrano.service.ISaludService;
import cl.sprintzambrano.sprintzambrano.service.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/liquidacion")
public class LiquidacionController {
    @Autowired
    ITrabajadorService objTrabajadorService;
    @Autowired
    ISaludService objSaludService;
    @Autowired
    IPrevisionService objPrevisionService;
    @Autowired
    ILiquidacionService objLiquidacionService;

    @GetMapping
    public String listarLiquidaciones(Model model){
        List<Liquidacion> listaLiquidaciones = objLiquidacionService.listarLiquidaciones();
        model.addAttribute("liquidaciones", listaLiquidaciones);
        return "listarLiquidaciones";
    }

    @GetMapping("/crearLiquidacion")
    public String mostrarFormLiquidacion(Model model){
        List<Trabajador> trabajador = objTrabajadorService.listarTrabajadores();
        List<InstitucionSalud> salud = objSaludService.listarSalud();
        List<InstitucionPrevision> prevision = objPrevisionService.listarPrevision();
        model.addAttribute("trabajador", trabajador);
        model.addAttribute("salud", salud);
        model.addAttribute("prevision", prevision);
        return "crearLiquidacion";
    }

    @PostMapping("/crearLiquidacion")
    public String crearLiquidacion(@ModelAttribute Liquidacion liquidacion,
                                   @RequestParam("idTrabajador") int idTrabajador,
                                   @RequestParam("idInstSalud") int idInstSalud,
                                   @RequestParam("idInstPrevision")int idInstPrevision,
                                   Model model) {
        liquidacion.setPeriodo(LocalDate.now());
        Trabajador trabajador = objTrabajadorService.buscarTrabajadorId(idTrabajador);
        InstitucionSalud salud = objSaludService.buscarSaludPorId(idInstSalud);
        InstitucionPrevision prevision = objPrevisionService.buscarPrevisionPorId(idInstPrevision);
        liquidacion.setTrabajador(trabajador);
        liquidacion.setIdInstSalud(salud);
        liquidacion.setIdInstPrevision(prevision);
        objLiquidacionService.crearLiquidacion(liquidacion);
        return "redirect:/liquidacion";
    }

    @GetMapping("/{idLiquidacion}")
    public String buscarTrabajadorPorId(@PathVariable int idLiquidacion, Model model){
        Liquidacion liquidacion = objLiquidacionService.buscarLiquidacionId(idLiquidacion);
        model.addAttribute("liquidacion", liquidacion);
        return "redirect:/liquidacion";
    }

    @PostMapping("/editar/{idLiquidacion}")
    public String mostrarFormLiquidacion(@PathVariable int idLiquidacion, Model model){
        model.addAttribute("liquidacion", objLiquidacionService.buscarLiquidacionId(idLiquidacion));
        List<InstitucionPrevision> prevision = objPrevisionService.listarPrevision();
        List<InstitucionSalud> salud = objSaludService.listarSalud();
        List<Trabajador> trabajador = objTrabajadorService.listarTrabajadores();
        model.addAttribute("prevision", prevision);
        model.addAttribute("salud", salud);
        model.addAttribute("trabajador", trabajador);
        return "editarLiquidacion";
    }

    @PostMapping("/actualizar/{idLiquidacion}")
    public String actualizarTrabajador(@ModelAttribute Liquidacion liquidacion, @PathVariable int idLiquidacion,
                                       @RequestParam("idInstPrevision") int idInstPrevision,
                                       @RequestParam("idInstSalud") int idInstSalud,
                                       @RequestParam("idTrabajador") int idTrabajador){
        InstitucionPrevision prevision = objPrevisionService.buscarPrevisionPorId(idInstPrevision);
        InstitucionSalud salud = objSaludService.buscarSaludPorId(idInstSalud);
        Trabajador trabajador = objTrabajadorService.buscarTrabajadorId(idTrabajador);
        liquidacion.setTrabajador(trabajador);
        liquidacion.setIdInstPrevision(prevision);
        liquidacion.setIdInstSalud(salud);
        objLiquidacionService.actualizarLiquidacion(liquidacion, idLiquidacion);
        return "redirect:/liquidacion";
    }

    @PostMapping("/eliminar/{idLiquidacion}")
    public String eliminarLiquidacionPorId(@PathVariable int idLiquidacion){
        objLiquidacionService.eliminarLiquidacion(idLiquidacion);
        return "redirect:/liquidacion";
    }


}
