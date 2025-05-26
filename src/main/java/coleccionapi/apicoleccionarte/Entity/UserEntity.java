package coleccionapi.apicoleccionarte.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CollectionId;

import java.io.Serial;
import java.util.Date;


@Entity
@Table(name="Users")
public class UserEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_user")
    private Long id_user;

    @NotNull
    @Size(max = 50)
    @Column(name = "name_user")
    private String nameuser;

    @NotNull
    @Size(max = 50)
    @Column(name = "lastname_user")
    private String lastname_user;

    @NotNull
    @Size(max = 10)
    @Column(name = "phone_user")
    private String phone_user;

    @NotNull
    @Column(name = "date_user")
    private Date date_user;

    @NotNull
    @Size(max = 50)
    @Column(name = "email_user")
    private String email_user;

    @NotNull
    @Column(name = "password_user")
    private String password_user;

    @Column(name = "token")
    private String token;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fk_rol", referencedColumnName = "id_rol")
    private RolesEntity roles;

    //Get personalizado
    @JsonProperty("fk_rol")
    public Long getId_rol() {
        return roles!=null?roles.getId_rol():null;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRoles(RolesEntity roles) {
        this.roles = roles;
    }

    public @NotNull @Size(max = 50) String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(@NotNull @Size(max = 50) String email_user) {
        this.email_user = email_user;
    }

    public @NotNull Date getDate_user() {
        return date_user;
    }

    public void setDate_user(@NotNull Date date_user) {
        this.date_user = date_user;
    }

    public @NotNull @Size(max = 10) String getPhone_user() {
        return phone_user;
    }

    public void setPhone_user(@NotNull @Size(max = 10) String phone_user) {
        this.phone_user = phone_user;
    }

    public @NotNull @Size(max = 50) String getLastname_user() {
        return lastname_user;
    }

    public void setLastname_user(@NotNull @Size(max = 50) String lastname_user) {
        this.lastname_user = lastname_user;
    }

    public @NotNull @Size(max = 50) String getName_user() {
        return nameuser;
    }

    public void setName_user(@NotNull @Size(max = 50) String name_user) {
        this.nameuser = name_user;
    }

    public Long getId_user() {
        return id_user;
    }

    public @NotNull String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(@NotNull String password_user) {
        this.password_user = password_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }
}
