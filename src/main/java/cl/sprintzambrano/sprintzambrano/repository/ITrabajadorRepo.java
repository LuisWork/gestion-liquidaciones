package cl.sprintzambrano.sprintzambrano.repository;

import cl.sprintzambrano.sprintzambrano.entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrabajadorRepo extends JpaRepository<Trabajador, Integer> {
}
