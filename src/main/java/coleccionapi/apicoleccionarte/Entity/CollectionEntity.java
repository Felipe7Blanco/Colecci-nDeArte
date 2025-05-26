package coleccionapi.apicoleccionarte.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.util.Date;

@Entity
@Table(name = "Collection")
public class CollectionEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_collection;


    @NotNull
    @Size(max = 50)
    @Column(name = "name_collecion")
    private String name_collection;


    @NotNull
    @Column(name = "description_collection")
    private String description_collection;

    @NotNull
    @Column(name = "date_collection")
    private Date date_collection;

    //foranea para obra de arte "atwork"
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name ="fk_atwork", referencedColumnName = "id_atwork")
    private AtworksEntity atwork;

    //Get y set de la foranea
    public Long getIdAtwork(){
        return atwork!=null ? atwork.getId_atwork(): null;
    }

    public void setAtwork(AtworksEntity atwork) {
        this.atwork = atwork;
    }

    public Long getId_collection() {
        return id_collection;
    }

    public void setId_collection(Long id_collection) {
        this.id_collection = id_collection;
    }

    public @NotNull @Size(max = 50) String getName_collection() {
        return name_collection;
    }

    public void setName_collection(@NotNull @Size(max = 50) String name_collection) {
        this.name_collection = name_collection;
    }

    public @NotNull String getDescription_collection() {
        return description_collection;
    }

    public void setDescription_collection(@NotNull String description_collection) {
        this.description_collection = description_collection;
    }

    public @NotNull Date getDate_collection() {
        return date_collection;
    }

    public void setDate_collection(@NotNull Date date_collection) {
        this.date_collection = date_collection;
    }
}
