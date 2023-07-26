package cl.sprintzambrano.sprintzambrano.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "instPrevision")
    List<Trabajador> listaTrabajadores;

    @JsonIgnore
    @OneToMany(mappedBy = "idInstPrevision")
    List<Liquidacion> liquidacionesPrev;
}
