package cl.sprintzambrano.sprintzambrano.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "institucion_salud")
public class InstitucionSalud {
    @Id
    @Column(name = "id_inst_salud", nullable = false)
    private int idInstSalud;

    @Column(nullable = false, length = 100)
    private String descripcion;

    @Column(name = "porc_dcto", nullable = false)
    private float porcDcto;

    @JsonIgnore
    @OneToMany(mappedBy = "instSalud")
    List<Trabajador> listaTrabajadores;

    @JsonIgnore
    @OneToMany(mappedBy = "idInstSalud")
    List<Liquidacion> liquidacionesSalud;
}
