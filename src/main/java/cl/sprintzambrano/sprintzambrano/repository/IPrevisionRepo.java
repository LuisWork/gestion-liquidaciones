package cl.sprintzambrano.sprintzambrano.repository;

import cl.sprintzambrano.sprintzambrano.entity.InstitucionPrevision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrevisionRepo extends JpaRepository<InstitucionPrevision, Integer> {
}
