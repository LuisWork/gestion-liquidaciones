package cl.sprintzambrano.sprintzambrano.service.serviceimpl;

import cl.sprintzambrano.sprintzambrano.entity.InstitucionPrevision;
import cl.sprintzambrano.sprintzambrano.entity.InstitucionSalud;
import cl.sprintzambrano.sprintzambrano.entity.Liquidacion;
import cl.sprintzambrano.sprintzambrano.repository.ILiquidacionRepo;
import cl.sprintzambrano.sprintzambrano.service.ILiquidacionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("liquidacionImpl")
public class LiquidacionImpl implements ILiquidacionService {
    @Autowired
    ILiquidacionRepo objLiquidacionRepo;
    @Override
    public List<Liquidacion> listarLiquidaciones() {
        return objLiquidacionRepo.findAll();
    }

    @Override
    @Transactional
    public Liquidacion crearLiquidacion(Liquidacion liquidacion) {
        int sueldoImponible = liquidacion.getSueldoImponible();
        InstitucionPrevision afpTrabajador = liquidacion.getIdInstPrevision();
        double porcentajeAFP = afpTrabajador.getPorcDcto();
        int montoAFP = (int) (sueldoImponible * (porcentajeAFP / 100.0));
        InstitucionSalud saludTrabajador = liquidacion.getIdInstSalud();
        double porcentajeSalud = saludTrabajador.getPorcDcto();
        int montoSalud = (int) (sueldoImponible * (porcentajeSalud / 100.0));
        int totalDescuento = montoAFP + montoSalud;
        int anticipo = liquidacion.getAnticipo();
        int totalHaberes = sueldoImponible;
        int sueldoLiquido = totalHaberes - totalDescuento - anticipo;
        liquidacion.setMontoInstSalud(montoSalud);
        liquidacion.setMontoInsPrevisional(montoAFP);
        liquidacion.setTotalDescuento(totalDescuento);
        liquidacion.setTotalHaberes(totalHaberes);
        liquidacion.setAnticipo(anticipo);
        liquidacion.setSueldoLiquido(sueldoLiquido);
        return objLiquidacionRepo.save(liquidacion);
    }


    @Override
    public Liquidacion buscarLiquidacionId(long idLiquidacion) {
        return objLiquidacionRepo.findById(idLiquidacion).orElseThrow(() ->new NoSuchElementException("Liquidacion no encontrada"));
    }

    @Override
    public Liquidacion actualizarLiquidacion(Liquidacion liquidacionActualizar, long idLiquidacion) {
        Liquidacion liquidacion = objLiquidacionRepo.findById(idLiquidacion).orElseThrow(() -> new NoSuchElementException("Liquidación no encontrada"));
        liquidacion.setTrabajador(liquidacionActualizar.getTrabajador());
        liquidacion.setPeriodo(liquidacionActualizar.getPeriodo());
        liquidacion.setSueldoImponible(liquidacionActualizar.getSueldoImponible());
        liquidacion.setSueldoLiquido(liquidacionActualizar.getSueldoLiquido());
        liquidacion.setIdInstSalud(liquidacionActualizar.getIdInstSalud());
        liquidacion.setMontoInstSalud(liquidacionActualizar.getMontoInstSalud());
        liquidacion.setIdInstPrevision(liquidacionActualizar.getIdInstPrevision());
        liquidacion.setMontoInsPrevisional(liquidacionActualizar.getMontoInsPrevisional());
        liquidacion.setTotalDescuento(liquidacionActualizar.getTotalDescuento());
        liquidacion.setTotalHaberes(liquidacionActualizar.getTotalHaberes());
        liquidacion.setAnticipo(liquidacionActualizar.getAnticipo());
        return objLiquidacionRepo.save(liquidacion);
    }

    @Override
    public void eliminarLiquidacion(long idLiquidacion) {
        objLiquidacionRepo.deleteById(idLiquidacion);
    }
}
