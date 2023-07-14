package cl.sprintzambrano.sprintzambrano.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "institucion_prevision")
public class InstitucionPrevision {
    @Id
    @Column(name = "id_inst_prevision", nullable =false)
    private int idInstPrevision;

    @Column(nullable = false, length = 50)
    private String descripcion;

    @Column(name = "porc_dcto", nullable = false)
    private float porcDcto;

    @OneToMany(mappedBy = "instPrevision")
    List<Trabajador> listaTrabajadores;

    @OneToMany(mappedBy = "idInstPrevision")
    List<Liquidacion> liquidacionesPrev;
}
