package coleccionapi.apicoleccionarte.Service.Implements;

import coleccionapi.apicoleccionarte.DTO.CollectionDTO;
import coleccionapi.apicoleccionarte.Entity.AtworksEntity;
import coleccionapi.apicoleccionarte.Entity.CollectionEntity;
import coleccionapi.apicoleccionarte.Repository.AtwortRepository;
import coleccionapi.apicoleccionarte.Repository.CollectionRepository;
import coleccionapi.apicoleccionarte.Service.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CollectionServiceImplements implements ICollectionService {

    @Autowired
    private CollectionRepository colleRepo;

    //Variable para buscar el id de la obra de arte
    @Autowired
    private AtwortRepository atwortRepo;

    //******************* GUARDAR UNA COLECCION ********************************
    @Transactional
    @Override
    public CollectionEntity save(CollectionDTO collectionDTO) {

        //creamos la nueva coleccion
        CollectionEntity collectionSave = new CollectionEntity();

        //creamos la variable que traera el id de la obra de arte
        AtworksEntity atworkID= atwortRepo.findById(collectionDTO.getIdArte()).orElse(null);


        //validar que el id no sea nulo
        if (atworkID == null) {
            throw new RuntimeException("Obra de arte no encontrada");
        }

        //asignamos los valores a nuestra coleccion
        collectionSave.setName_collection(collectionDTO.getNombre());
        collectionSave.setDescription_collection(collectionDTO.getDescripcion());
        collectionSave.setDate_collection(collectionDTO.getFecha());
        collectionSave.setAtwork(atworkID);

        return colleRepo.save(collectionSave);
    }

    //************************** BUSCAR TODAS LAS COLECCIONES ***************************
    @Override
    @Transactional(readOnly = true)
    public List<CollectionEntity> findAll() {
        return colleRepo.findAll();
    }

    //************************* BUSCAR POR ID ***********************************
    @Override
    @Transactional(readOnly = true)
    public CollectionEntity findById(Long id) {
        return colleRepo.findById(id).orElse(null);
    }

    //**************************** ACTUALIZAR ********************************
    @Override
    public CollectionEntity update(Long id, CollectionDTO collectionDTO) {
        //Buscamos el id que queremos actualizar
        CollectionEntity collectionUpdate = colleRepo.findById(id).orElse(null);
        //validamos que el id no sea nulo
        if(collectionUpdate == null){
            throw new RuntimeException("colección no encontrada");
        }
        //Asignamos los valores
        collectionUpdate.setName_collection(collectionDTO.getNombre());
        collectionUpdate.setDescription_collection(collectionDTO.getDescripcion());
        collectionUpdate.setDate_collection(collectionDTO.getFecha());

        return colleRepo.save(collectionUpdate);
    }

    @Override
    public void delete(Long id) {
        //primero verificamos que el id exista
        if (id ==null){
            throw new RuntimeException("Obra de arte no encontrada");
        }
        colleRepo.deleteById(id);
    }
    //buscar por nombre
    @Override
    public List<CollectionEntity> findByName(String nombre) {
        return colleRepo.findByName(nombre);
    }


}
