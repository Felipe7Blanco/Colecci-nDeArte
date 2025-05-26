package coleccionapi.apicoleccionarte.Repository;

import coleccionapi.apicoleccionarte.DTO.UserDTO;
import coleccionapi.apicoleccionarte.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Modifying
    @Query("delete from UserEntity where id_user =?1")
    public void deleteUser(Long id) ;

    /***
     * Metodos para aceeso
     */
    @Query("SELECT us FROM UserEntity us WHERE us.nameuser=?1 AND us.password_user=?2")
    public UserDTO getByNameAndPassword(String username, String pwd);

    @Transactional
    @Modifying
    @Query(value = "UPDATE usuarios set token =:token where username = :username AND password= :password",
            nativeQuery = true)
    void updateUserToken(@Param("token") String token, @Param("username") String username, @Param("password") String password);

    //buscar por nombre
    @Query("SELECT u FROM UserEntity u WHERE u.nameuser = :username")
    UserEntity findByUsername(@Param("username") String username);




}
