package coleccionapi.apicoleccionarte.Service;

import coleccionapi.apicoleccionarte.DTO.AtworkDTO;
import coleccionapi.apicoleccionarte.Entity.AtworksEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IAtworkService {

    //**** Guardar obra de arte ***********************
    public AtworksEntity save (AtworkDTO atworkDTO);

    //******* Listar obra de arte *******************
    public List<AtworksEntity> findAll();

    //*********** Actualizar obra de arte ************
    public AtworksEntity update (Long id,AtworkDTO atworkDTO);

    //**************** Listar por id *****************
    public List<AtworksEntity> findById(Long id);

    //******** Eliminar obra de arte ****************
    public void delete (Long id);

}
