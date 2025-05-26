package coleccionapi.apicoleccionarte.Rest;

import coleccionapi.apicoleccionarte.DTO.AtworkDTO;
import coleccionapi.apicoleccionarte.Entity.AtworksEntity;
import coleccionapi.apicoleccionarte.Service.IAtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atworks")
public class AtworkRest {

    @Autowired
    private IAtworkService atworkService;


    //************************* GUARDAR UNA OBRA DE ARTE ***********************************
    @PostMapping("/save")
    public ResponseEntity<AtworksEntity> create
            (@Validated @RequestBody AtworkDTO atwortDTO) {
        try{
            AtworksEntity atworksNew = atworkService.save(atwortDTO);
            return ResponseEntity.ok().body(atworksNew);
        } catch (Exception e){
            System.out.println("Fallo al crear el atwork" +e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //***************** Listar todas las obras de arte ****************
    @GetMapping("/listAll")
    public ResponseEntity<List<AtworksEntity>> listAll() {
        return ResponseEntity.ok(atworkService.findAll());
    }

    //***************** Actualizar obra de arte **************************
    @PutMapping("update/{id}")
    public ResponseEntity<AtworksEntity> update(
            @PathVariable("id") Long id, @RequestBody AtworkDTO atwortDTO) {
        AtworksEntity atworkUpdate = atworkService.update(id, atwortDTO);
        if (atworkUpdate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().body(atworkUpdate);
    }
    //****************** Buscar x id ******************
    @GetMapping("/listID/{id}")
    public ResponseEntity<List<AtworksEntity>> listID(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(atworkService.findById(id));
    }

    //******************* Eliminar obra de arte ***************************
    @DeleteMapping("Delete/{id}")
    public ResponseEntity<AtworksEntity> delete(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        atworkService.delete(id);
        return ResponseEntity.ok().body(null);
    }

}
