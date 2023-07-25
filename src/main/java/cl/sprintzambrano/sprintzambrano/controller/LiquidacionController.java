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
        model.addAttribute("liquidacion", new Liquidacion());
        return "crearLiquidacion";
    }

    @PostMapping("/crearLiquidacion")
    public String crearLiquidacion(@ModelAttribute Liquidacion liquidacion,
                                   @RequestParam("idTrabajador") int idTrabajador,
                                   @RequestParam("idInstSalud") int idSalud,
                                   @RequestParam("idInstPrevision")int idPrevision,
                                   Model model) {
        Trabajador trabajador = objTrabajadorService.buscarTrabajadorId(idTrabajador);
        InstitucionPrevision prevision = objPrevisionService.buscarPrevisionPorId(idSalud);
        InstitucionSalud salud = objSaludService.buscarSaludPorId(idPrevision);
        liquidacion.setTrabajador(trabajador);
        liquidacion.setIdInstSalud(salud);
        liquidacion.setIdInstPrevision(prevision);
        model.addAttribute("trabajador", trabajador);
        model.addAttribute("salud", salud);
        model.addAttribute("prevision", prevision);
        objLiquidacionService.crearLiquidacion(liquidacion);
        return "redirect:/liquidacion";
    }

    @PostMapping("/eliminar/{idLiquidacion}")
    public String eliminarLiquidacionPorId(@PathVariable int idLiquidacion){
        objLiquidacionService.eliminarLiquidacion(idLiquidacion);
        return "redirect:/liquidacion";
    }


}
