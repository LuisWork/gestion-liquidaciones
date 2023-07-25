package cl.sprintzambrano.sprintzambrano.service;

import cl.sprintzambrano.sprintzambrano.entity.Liquidacion;

import java.util.List;

public interface ILiquidacionService {
    List<Liquidacion> listarLiquidaciones();
    Liquidacion crearLiquidacion(Liquidacion liquidacion);
    Liquidacion buscarLiquidacionId(long idLiquidacion);
    Liquidacion actualizarLiquidacion(Liquidacion liquidacion, long idLiquidacion);
    public void eliminarLiquidacion(long idLiquidacion);
}
