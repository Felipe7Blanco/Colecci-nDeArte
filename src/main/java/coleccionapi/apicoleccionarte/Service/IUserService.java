package coleccionapi.apicoleccionarte.Service;

import coleccionapi.apicoleccionarte.DTO.UserDTO;
import coleccionapi.apicoleccionarte.Entity.UserEntity;

import java.util.List;

public interface IUserService {

    //Guardar
    public UserEntity save(UserDTO userDTO);

    //Listar todos
    public List<UserEntity> findAll();

    //Listar x id
    public List<UserEntity> findById(Long id);

    //Actualizar
    public UserEntity update(Long id, UserDTO userDTO);

    //Borrar
    public void delete(Long id);


}
