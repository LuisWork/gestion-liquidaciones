package cl.sprintzambrano.sprintzambrano.service;

import cl.sprintzambrano.sprintzambrano.entity.InstitucionPrevision;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IPrevisionService {
    List<InstitucionPrevision> listarPrevision();
    InstitucionPrevision buscarPrevisionPorId(int idInstPrevision);
}
