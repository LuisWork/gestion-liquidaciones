package cl.sprintzambrano.sprintzambrano.service.serviceimpl;

import cl.sprintzambrano.sprintzambrano.entity.InstitucionSalud;
import cl.sprintzambrano.sprintzambrano.repository.ISaludRepo;
import cl.sprintzambrano.sprintzambrano.service.ISaludService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SaludImpl implements ISaludService {
    @Autowired
    ISaludRepo objSaludRepo;
    @Override
    public List<InstitucionSalud> listarSalud() {
        return objSaludRepo.findAll();
    }

    public InstitucionSalud buscarSaludPorId(int idInstSalud){
        return objSaludRepo.findById(idInstSalud).orElseThrow(() -> new NoSuchElementException("Institucion no encontrada"));
    }
}
