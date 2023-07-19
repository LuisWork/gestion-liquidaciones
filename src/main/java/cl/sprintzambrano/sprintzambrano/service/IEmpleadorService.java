package cl.sprintzambrano.sprintzambrano.service;

import cl.sprintzambrano.sprintzambrano.entity.Empleador;

import java.util.List;

public interface IEmpleadorService {
    List<Empleador> listarEmpleadores();
    Empleador crearEmpleador(Empleador empleador);
    Empleador registrarEmpleador(Empleador empleador);
    Empleador buscarEmpleadorPorId(int idEmpleador);
    Empleador actualizarEmpleador(Empleador empleador, int idEmpleador);
    Empleador actualizarEmpleador2(Empleador empleador);
    public void eliminarEmpleador(Empleador empleador);
    public void eliminarEmpleador2(int idEmpleador);
}
