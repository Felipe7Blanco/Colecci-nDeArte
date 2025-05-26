package coleccionapi.apicoleccionarte.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;

@Entity
@Table(name ="Roles")
public class RolesEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_rol")
    private Long id_rol;

    @NotNull
    @Size(max = 50)
    @Column(name = "name_rol")
    private String name_rol;

    public Long getId_rol() {
        return id_rol;
    }

    public void setId_rol(Long id_rol) {
        this.id_rol = id_rol;
    }

    public @NotNull @Size(max = 50) String getName_rol() {
        return name_rol;
    }

    public void setName_rol(@NotNull @Size(max = 50) String name_rol) {
        this.name_rol = name_rol;
    }
}
