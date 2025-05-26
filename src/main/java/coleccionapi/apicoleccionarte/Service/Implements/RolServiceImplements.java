package coleccionapi.apicoleccionarte.Service.Implements;

import coleccionapi.apicoleccionarte.DTO.RolDTO;
import coleccionapi.apicoleccionarte.Entity.RolesEntity;
import coleccionapi.apicoleccionarte.Repository.RolRepository;
import coleccionapi.apicoleccionarte.Service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolServiceImplements implements IRolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<RolesEntity> findAll() {
        return rolRepository.findAll();
    }

    @Override
    public RolesEntity findById(Integer id) {
        return null;
    }

    //guardar
    @Transactional
    @Override
    public RolesEntity save(RolDTO rolDTO) {

        RolesEntity miRol = new RolesEntity();
        miRol.setName_rol(rolDTO.getNombre());
        return rolRepository.save(miRol);
    }

    // ********************* EDITAR ***************************
    @Override
    public RolesEntity update(Long id, RolDTO role) {
        // Buscar si el rol existe
        RolesEntity miRol = rolRepository.findById(id).orElse(null);

        // Si no existe, retornar null
        if (miRol == null) {
            return null;
        }

        // Actualizar el nombre del rol
        miRol.setName_rol(role.getNombre());

        // Guardar cambios
        return rolRepository.save(miRol);
    }




    @Override
    @Transactional
    public void delete(Long id) {
        rolRepository.deleteById(id);
    }
}
