package cl.sprintzambrano.sprintzambrano.service;

import cl.sprintzambrano.sprintzambrano.entity.Trabajador;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ITrabajadorService {
    List<Trabajador> listarTrabajadores();
    Trabajador crearTrabajador(Trabajador trabajador);
    Trabajador buscarTrabajadorId(int idTrabajador);
    Trabajador actualizarTrabajador(Trabajador trabajador, int idTrabajador);
    public void eliminarTrabajador(int idTrabajador);

}
