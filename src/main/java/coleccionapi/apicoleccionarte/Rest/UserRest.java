package coleccionapi.apicoleccionarte.Rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import coleccionapi.apicoleccionarte.DTO.UserDTO;
import coleccionapi.apicoleccionarte.Entity.UserEntity;
import coleccionapi.apicoleccionarte.Repository.UserRepository;
import coleccionapi.apicoleccionarte.Service.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/user")
public class UserRest {

    @Autowired
    private IUserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserRest.class);
    private static final String SECRET_KEY = "fV9G8jK9tP@x9z7UqLqR4fE6wD3cN8xM6qV7yE5rS1hA9pT3rW2xY9qZ6dL1gF8s";


    // ***************** end ponit para guardar user ************
    /**@PostMapping("/save")
    public ResponseEntity<UserEntity> create(
     @Valid @RequestBody UserDTO userDTO
     ) {
     UserEntity miUser;
     UserEntity userEntity = userService.save(userDTO);
     String token = getJWTToken(userEntity.getName_user());
     try {
     miUser = userService.save(userDTO);
     ResponseEntity.status(200);
     ResponseEntity.ok(miUser);
     userDTO.setToken(token);
     } catch (Exception e) {
     System.out.println("Error al insertar el usuario" + e.getMessage());
     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
     }
     return ResponseEntity.ok(miUser);
     }
     **/

    @PostMapping("/save")
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) {
        try {
            // Guardar el usuario
            UserEntity savedUser = userService.save(userDTO);

            // Generar token
            String token = getJWTToken(savedUser.getName_user());

            // Crear DTO de respuesta
            UserDTO response = new UserDTO();
            response.setNombreUser(savedUser.getName_user());
            response.setApellidoUser(savedUser.getLastname_user());
            response.setEmail(savedUser.getEmail_user());
            response.setTelefono(savedUser.getPhone_user());
            response.setFechaNacimiento(savedUser.getDate_user());
            response.setIdrol(savedUser.getId_rol());
            response.setToken(token);

            // (Opcional) guardar el token en la base de datos si así lo deseas
            savedUser.setToken(token);
            userRepository.save(savedUser);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println("Error al registrar el usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    //********************* end point para get all user ************
    @GetMapping("/List")
    public ResponseEntity<List<UserEntity>> list() {
        return ResponseEntity.ok(userService.findAll());
    }

    //************** end point para buscar x id ***************
    @GetMapping("/list/{id}")
    public ResponseEntity<List<UserEntity>> list(@PathVariable("id") Long id) {
        if(id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(userService.findById(id));
    }

    //****************** end point para actualizar user **************
    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntity> edit(
            @Validated @RequestBody UserDTO userDTO, @PathVariable("id") Long id
    ){
        UserEntity userUpdate = userService.update(id, userDTO);
        if (userUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userUpdate);
    }

    //***************** end point para eliminar el user ************
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserEntity> delete(@PathVariable("id") Long id){
        if(id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        userService.delete(id);
        return ResponseEntity.status(204).build();
    }

    /***************************
     * Metodos de acceso
     */



    @PostMapping("/acceso")
    public ResponseEntity<?> login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
        logger.info("Intento de inicio de sesión para el usuario: {}", username);

        // Buscar al usuario en la base de datos
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            logger.warn("Usuario no encontrado: {}", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
        }

        if (!passwordEncoder.matches(pwd, user.getPassword_user())) {
            logger.warn("Contraseña incorrecta para el usuario: {}", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
        }

        // Si todo está bien, generar token
        String token = getJWTToken(user.getName_user());
        user.setToken(token); // opcional, para guardar el token en BD
        userRepository.save(user); // opcional

        // Crear y retornar un DTO con el token
        UserDTO response = new UserDTO();
        response.setNombreUser(user.getName_user());
        //response.setApellidoUser(user.getLastname_user());
        //response.setEmail(user.getEmail_user());
        //response.setTelefono(user.getPhone_user());
        //response.setFechaNacimiento(user.getDate_user());
        //response.setIdrol(user.getId_rol());
        response.setToken(token);

        return ResponseEntity.ok(response);
    }







    private String getJWTToken(String username) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        return Jwts.builder()
                .setId("ustajwt")
                .setSubject(username)
                .claim("authorities", authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 4600000))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

}




