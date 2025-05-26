package coleccionapi.apicoleccionarte.DTO;

import java.util.Date;

public class CollectionDTO {

    private String nombre;
    private String descripcion;
    private Date fecha;
    private Long idArte;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIdArte() {
        return idArte;
    }

    public void setIdArte(Long idArte) {
        this.idArte = idArte;
    }
}
