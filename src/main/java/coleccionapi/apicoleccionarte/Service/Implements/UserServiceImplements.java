package coleccionapi.apicoleccionarte.Service.Implements;

import coleccionapi.apicoleccionarte.DTO.UserDTO;
import coleccionapi.apicoleccionarte.Entity.RolesEntity;
import coleccionapi.apicoleccionarte.Entity.UserEntity;
import coleccionapi.apicoleccionarte.Repository.RolRepository;
import coleccionapi.apicoleccionarte.Repository.UserRepository;
import coleccionapi.apicoleccionarte.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserServiceImplements implements IUserService {

    @Autowired
    private UserRepository userRepo;
    //encriptar contraseña
    @Autowired
    private PasswordEncoder passwordEncoder;


    //buscar el id del rol
    @Autowired
    private RolRepository rolRepo;

    //********************* GUARDAR USER ***********************
    @Transactional
    @Override
    public UserEntity save(UserDTO userDTO) {
        //creamos un user
        UserEntity miUser = new UserEntity();

        //asignar el id del rol
        RolesEntity fkRol= rolRepo.findById(userDTO.getIdrol()).orElse(null);
        //verificamos que el rol no sea nulo
        if (fkRol==null) {
            throw new RuntimeException("El ID del rol no existe");
        }
        miUser.setRoles(fkRol);

        miUser.setName_user(userDTO.getNombreUser());
        miUser.setLastname_user(userDTO.getApellidoUser());
        miUser.setDate_user(userDTO.getFechaNacimiento());
        miUser.setPhone_user(userDTO.getTelefono());
        miUser.setEmail_user(userDTO.getEmail());
        miUser.setPassword_user(passwordEncoder.encode(userDTO.getContraseña()));
        miUser.setToken(userDTO.getToken());

        return userRepo.save(miUser);
    }

    //****************************** LISTAR USER *****************************
    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return userRepo.findAll();
    }

    //*************************** LISTAR ID ***************************
    @Override
    public List<UserEntity> findById(Long id) {
        UserEntity user = userRepo.findById(id).orElse(null);
        if (user==null) {
            throw new RuntimeException("El ID del usuario no existe");
        }
        return List.of(user);
    }


    // *************************** Actualizar USER ****************************
    @Override
    public UserEntity update(Long id, UserDTO userDTO) {
        UserEntity miUser = userRepo.findById(id).orElse(null);
        //buscar el id y en caso no existir mandar un mensaje
        if (miUser==null) {
            throw new RuntimeException("El ID del usuario no existe");
        }

        //actualizar los campos
        miUser.setName_user(userDTO.getNombreUser());
        miUser.setLastname_user(userDTO.getApellidoUser());
        miUser.setDate_user(userDTO.getFechaNacimiento());
        miUser.setPhone_user(userDTO.getTelefono());
        miUser.setEmail_user(userDTO.getEmail());
        miUser.setPassword_user(userDTO.getContraseña());
        //
        return userRepo.save(miUser);
    }

    //************************* ELIMINAR UN USUARIO **************
    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }
}
