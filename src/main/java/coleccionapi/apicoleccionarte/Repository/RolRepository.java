package coleccionapi.apicoleccionarte.Repository;

import coleccionapi.apicoleccionarte.Entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RolRepository extends JpaRepository<RolesEntity, Long> {

    @Modifying
    @Query("delete from RolesEntity where id_rol=?1")
    public void eliminarRol(Long idRol);


}
