package cl.sprintzambrano.sprintzambrano.restController;

import cl.sprintzambrano.sprintzambrano.entity.Trabajador;
import cl.sprintzambrano.sprintzambrano.service.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/trabajador", headers = "Accept=Application/json")
public class TrabajadorRestController {
    @Autowired
    ITrabajadorService objTrabajadorService;
    @GetMapping
    public List<Trabajador> listarTrabajadores(){
        return objTrabajadorService.listarTrabajadores();
    }

    @GetMapping("/{idTrabajador}")
    public Trabajador buscarTrabajadorPorId(@PathVariable int idTrabajador) {
        return objTrabajadorService.buscarTrabajadorId(idTrabajador);
    }
}
