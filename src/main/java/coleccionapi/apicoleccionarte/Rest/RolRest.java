package coleccionapi.apicoleccionarte.Rest;

import coleccionapi.apicoleccionarte.DTO.RolDTO;
import coleccionapi.apicoleccionarte.Entity.RolesEntity;
import coleccionapi.apicoleccionarte.Service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rol")
public class RolRest {

    @Autowired
    private IRolService rolService;

    //********************** GUARDAR O CREAR ROL **************************

    @PostMapping("/save")
    public ResponseEntity<RolesEntity> create
        (@Validated @RequestBody RolDTO rolGuardar){
        try{
            RolesEntity rol = rolService.save(rolGuardar);
            rol.setName_rol(rolGuardar.getNombre());
            ResponseEntity.status(200);
            return ResponseEntity.ok(rol);
        } catch (Exception e) {
            System.out.println("Errol al guardar rol" + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    // ********************* LISTAR TODOS **********************************
    @GetMapping("/listAll")
    public ResponseEntity<List<RolesEntity>> listAll(){
        return ResponseEntity.ok(rolService.findAll());
    }

    //*************+** BORRAR ID *************************
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> delete
    (@PathVariable("id") Long id){
        rolService.delete(id);
        return ResponseEntity.ok("Rol eliminado");
    }

    //********************* EDITAR EL ROL******************
    @PutMapping("/edit/{id}")
    public ResponseEntity<RolesEntity>edit(
            @Validated @RequestBody RolDTO rolGuardar, @PathVariable("id") Long id
            ) {
            RolesEntity updateROl = rolService.update(id,rolGuardar);
            if(updateROl == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updateROl);
    }

}
