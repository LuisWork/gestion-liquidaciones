package cl.sprintzambrano.sprintzambrano.service;

import cl.sprintzambrano.sprintzambrano.entity.Empleador;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IEmpleadorService {
    List<Empleador> listarEmpleadores();
    Empleador crearEmpleador(Empleador empleador);
    Empleador buscarEmpleadorPorId(int idUsuario);
    Empleador actualizarEmpleador(Empleador empleador, int idEmpleador);
    public void eliminarEmpleador(int idEmpleador);
}
