package cl.sprintzambrano.sprintzambrano.repository;

import cl.sprintzambrano.sprintzambrano.entity.Liquidacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILiquidacionRepo extends JpaRepository<Liquidacion, Long> {
}
