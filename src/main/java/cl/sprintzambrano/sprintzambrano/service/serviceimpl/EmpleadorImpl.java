package cl.sprintzambrano.sprintzambrano.service.serviceimpl;

import cl.sprintzambrano.sprintzambrano.entity.Empleador;
import cl.sprintzambrano.sprintzambrano.repository.IEmpleadorRepo;
import cl.sprintzambrano.sprintzambrano.service.IEmpleadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("empleadorImpl")
public class EmpleadorImpl implements IEmpleadorService {
    @Autowired
    IEmpleadorRepo objEmpleadorRepo;

    @Override
    public Empleador crearEmpleador(Empleador empleador) { return objEmpleadorRepo.save(empleador); }

    @Override
    public Empleador registrarEmpleador(Empleador empleador) { return objEmpleadorRepo.save(empleador); }
    @Override
    public List<Empleador> listarEmpleadores(){ return objEmpleadorRepo.findAll(); }

    @Override
    public Empleador buscarEmpleadorPorId(int idEmpleador){
        return objEmpleadorRepo.findById(idEmpleador).orElseThrow(() -> new NoSuchElementException("Empleador no encontrado"));
    }

    @Override
    public Empleador actualizarEmpleador(Empleador empleadorActualizar, int idEmpleador) {
        Empleador empleador = objEmpleadorRepo.findById(idEmpleador).orElseThrow(() -> new NoSuchElementException("Empleador no encontrado"));
        empleador.setRun(empleadorActualizar.getRun());
        empleador.setNombre(empleadorActualizar.getNombre());
        empleador.setApellido1(empleadorActualizar.getApellido1());
        empleador.setApellido2(empleadorActualizar.getApellido2());
        empleador.setDireccion(empleadorActualizar.getDireccion());
        empleador.setEmail(empleadorActualizar.getEmail());
        return objEmpleadorRepo.save(empleador);
    }

    @Override
    public Empleador actualizarEmpleador2(Empleador empleadorActualizar){
        Empleador empleador = objEmpleadorRepo.findById(empleadorActualizar.getIdEmpleador()).orElseThrow(() -> new NoSuchElementException("Empleador no encontrado"));
        empleador.setRun(empleadorActualizar.getRun());
        empleador.setNombre(empleadorActualizar.getNombre());
        empleador.setApellido1(empleadorActualizar.getApellido1());
        empleador.setApellido2(empleadorActualizar.getApellido2());
        empleador.setDireccion(empleadorActualizar.getDireccion());
        empleador.setEmail(empleadorActualizar.getEmail());
        return objEmpleadorRepo.save(empleador);
    }

    @Override
    public void eliminarEmpleador(Empleador empleador) { objEmpleadorRepo.delete(empleador); }

    @Override
    public void eliminarEmpleador2(int idEmpleador) { objEmpleadorRepo.deleteById(idEmpleador); }
}
