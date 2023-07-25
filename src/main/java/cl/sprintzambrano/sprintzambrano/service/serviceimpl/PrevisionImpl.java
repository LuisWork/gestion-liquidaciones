package cl.sprintzambrano.sprintzambrano.service.serviceimpl;

import cl.sprintzambrano.sprintzambrano.entity.InstitucionPrevision;
import cl.sprintzambrano.sprintzambrano.repository.IPrevisionRepo;
import cl.sprintzambrano.sprintzambrano.service.IPrevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service("previsionImpl")
public class PrevisionImpl implements IPrevisionService {
    @Autowired
    IPrevisionRepo objPrevisionRepo;
    @Override
    public List<InstitucionPrevision> listarPrevision() {
        return objPrevisionRepo.findAll();
    }

    @Override
    public InstitucionPrevision buscarPrevisionPorId(int idInstPrevision) {
        return objPrevisionRepo.findById(idInstPrevision).orElseThrow(() -> new NoSuchElementException("Institución no encontrada"));
    }
}