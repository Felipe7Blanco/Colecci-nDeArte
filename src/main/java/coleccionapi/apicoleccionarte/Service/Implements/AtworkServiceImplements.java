package coleccionapi.apicoleccionarte.Service.Implements;

import coleccionapi.apicoleccionarte.DTO.AtworkDTO;
import coleccionapi.apicoleccionarte.Entity.AtworksEntity;
import coleccionapi.apicoleccionarte.Entity.UserEntity;
import coleccionapi.apicoleccionarte.Repository.AtwortRepository;
import coleccionapi.apicoleccionarte.Repository.UserRepository;
import coleccionapi.apicoleccionarte.Service.IAtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AtworkServiceImplements implements IAtworkService {

    @Autowired
    private AtwortRepository atworkRepo;
    @Autowired
    private UserRepository userRepo;

    @Transactional
    @Override
    public AtworksEntity save(AtworkDTO atworkDTO) {
        //creamos la entidad a guardar
        AtworksEntity atworkNew = new AtworksEntity();

        //Buscar el id del usuario
        UserEntity userID = userRepo.findById(atworkDTO.getId_user()).orElse(null);

        //validar que el id del usuario no sea nulo
        if (userID == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        //asignamos los datos de la obra de arte
        atworkNew.setName_atwork(atworkDTO.getName_art());
        atworkNew.setDescription_atwork(atworkDTO.getDescription_art());
        atworkNew.setDate_artwork(atworkDTO.getDate_art());
        atworkNew.setImage_artwork(atworkDTO.getImage_art());

        //guardar el id de usuario
        atworkNew.setUser(userID);

        return atworkRepo.save(atworkNew);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AtworksEntity> findAll() {

        return atworkRepo.findAll();
    }

    @Override
    public AtworksEntity update(Long id, AtworkDTO atworkDTO) {
        //buscamos la obra de arte que queremos actualizar
        AtworksEntity atworkUpdate = atworkRepo.findById(id).orElse(null);

        if(atworkUpdate == null) {
            throw new RuntimeException("Atwork no encontrado");
        }
        //Asignamos los nuevos atributos
        atworkUpdate.setName_atwork(atworkDTO.getName_art());
        atworkUpdate.setDescription_atwork(atworkDTO.getDescription_art());
        atworkUpdate.setDate_artwork(atworkDTO.getDate_art());
        atworkUpdate.setImage_artwork(atworkDTO.getImage_art());

        return atworkRepo.save(atworkUpdate);
    }

    @Override
    public List<AtworksEntity> findById(Long id) {
        AtworksEntity atworkID = atworkRepo.findById(id).orElse(null);
        if(atworkID == null) {
            throw new RuntimeException("Atwork no encontrado");
        }
        return List.of(atworkID);
    }

    @Override
    public void delete(Long id) {
        AtworksEntity atworkDelete = atworkRepo.findById(id).orElse(null);
        if(atworkDelete == null) {
            throw new RuntimeException("Atwork no encontrado");
        }
        atworkRepo.delete(atworkDelete);
    }
}
