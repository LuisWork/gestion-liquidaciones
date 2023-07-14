package cl.sprintzambrano.sprintzambrano.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "perfil")
public class Perfil {
    @Id
    @Column(name = "id_perfil", nullable = false)
    private int idPerfil;

    @Column(length = 50, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private boolean estado;

    @OneToMany(mappedBy = "perfil")
    private List<Usuario> usuarios;
}
