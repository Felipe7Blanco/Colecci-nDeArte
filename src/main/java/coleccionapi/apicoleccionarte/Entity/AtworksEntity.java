package coleccionapi.apicoleccionarte.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.Date;

@Entity
@Table(name ="Atworks")
public class AtworksEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_atwork;

    @NotNull
    @Column(name ="name_atwork")
    public String name_atwork;

    @NotNull
    @Column(name ="description_atwork")
    public String description_atwork;

    @NotNull
    @Column(name = "date_artwork")
    public Date date_artwork;

    //dato o atributo para la imagen
    @NotNull
    @Column(name="image_artwork")
    @Lob
    public String image_artwork;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name ="fk_user", referencedColumnName = "id_user")
    private UserEntity user;

    public Long getfk_user  () {
        return user!=null ? user.getId_user() : null;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Long getId_atwork() {
        return id_atwork;
    }

    public void setId_atwork(Long id_atwork) {
        this.id_atwork = id_atwork;
    }

    public @NotNull String getName_atwork() {
        return name_atwork;
    }

    public void setName_atwork(@NotNull String name_atwork) {
        this.name_atwork = name_atwork;
    }

    public @NotNull String getDescription_atwork() {
        return description_atwork;
    }

    public void setDescription_atwork(@NotNull String description_atwork) {
        this.description_atwork = description_atwork;
    }

    public @NotNull Date getDate_artwork() {
        return date_artwork;
    }

    public void setDate_artwork(@NotNull Date date_artwork) {
        this.date_artwork = date_artwork;
    }

    public @NotNull String getImage_artwork() {
        return image_artwork;
    }

    public void setImage_artwork(@NotNull String image_artwork) {
        this.image_artwork = image_artwork;
    }
}
