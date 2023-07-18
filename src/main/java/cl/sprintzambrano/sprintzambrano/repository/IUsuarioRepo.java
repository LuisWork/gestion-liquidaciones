package cl.sprintzambrano.sprintzambrano.repository;

import cl.sprintzambrano.sprintzambrano.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {
}
