package coleccionapi.apicoleccionarte.Service;

import coleccionapi.apicoleccionarte.DTO.CollectionDTO;
import coleccionapi.apicoleccionarte.Entity.CollectionEntity;

import java.util.List;

public interface ICollectionService {

    //guardar una coleccion
    public CollectionEntity save(CollectionDTO collectionDTO);

    //listar todas las colecciones
    public List<CollectionEntity> findAll();

    //Listar id
    public CollectionEntity findById(Long id);

    //Actualizar
    public CollectionEntity update(Long id,CollectionDTO collectionDTO);

    //Eliminar
    public void delete(Long id);


    //buscar por nombre:
    List<CollectionEntity> findByName(String nombre);

}
