package cl.sprintzambrano.sprintzambrano.service;

import cl.sprintzambrano.sprintzambrano.entity.InstitucionSalud;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ISaludService {
    List<InstitucionSalud> listarSalud();
    InstitucionSalud buscarSaludPorId(int idInstSalud);
}
