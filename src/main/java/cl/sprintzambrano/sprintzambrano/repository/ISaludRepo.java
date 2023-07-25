package cl.sprintzambrano.sprintzambrano.repository;

import cl.sprintzambrano.sprintzambrano.entity.InstitucionSalud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaludRepo extends JpaRepository<InstitucionSalud, Integer> {
}
