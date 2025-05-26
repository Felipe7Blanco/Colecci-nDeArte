package coleccionapi.apicoleccionarte.Service;

import coleccionapi.apicoleccionarte.DTO.RolDTO;
import coleccionapi.apicoleccionarte.Entity.RolesEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRolService {

    //listar todos
    public List<RolesEntity> findAll();
    //listad id
    public RolesEntity findById(Integer id);
    //guardar
    public RolesEntity save(RolDTO role);

    //Actualizar
    public RolesEntity update(Long id, RolDTO role);

    //Borrar
    @Modifying
    @Query("DELETE FROM RolesEntity where id_rol=?1")
    public void delete(Long id);


}
