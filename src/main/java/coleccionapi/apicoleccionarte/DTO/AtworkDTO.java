package coleccionapi.apicoleccionarte.DTO;

import java.util.Date;

public class AtworkDTO {

    private String name_art;
    private String description_art;
    private Date date_art;
    private Long id_user;
    private String image_art;

    public String getName_art() {
        return name_art;
    }

    public void setName_art(String name_art) {
        this.name_art = name_art;
    }

    public String getDescription_art() {
        return description_art;
    }

    public void setDescription_art(String description_art) {
        this.description_art = description_art;
    }

    public Date getDate_art() {
        return date_art;
    }

    public void setDate_art(Date date_art) {
        this.date_art = date_art;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getImage_art() {
        return image_art;
    }

    public void setImage_art(String image_art) {
        this.image_art = image_art;
    }
}
