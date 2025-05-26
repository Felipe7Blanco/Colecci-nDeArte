package coleccionapi.apicoleccionarte.Rest;

import coleccionapi.apicoleccionarte.DTO.CollectionDTO;
import coleccionapi.apicoleccionarte.Entity.CollectionEntity;
import coleccionapi.apicoleccionarte.Service.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController

@RequestMapping("/api/collection")
public class CollectionRest {

    @Autowired
    private ICollectionService collectionService;

    //********** GUARDAR UNA COLECCION *****************
    @PostMapping("save")
    public ResponseEntity<String> save(
            @Validated @RequestBody CollectionDTO collectiondto) {
            try{
                //creamos la coleccion
                CollectionEntity collection = collectionService.save(collectiondto);

                return ResponseEntity.ok(collectiondto.toString());
            }catch (Exception e){
                System.out.println("Error al guardar el objeto"+ e.getMessage());
                return ResponseEntity.badRequest().body(e.getMessage());
            }
    }

    //****************** Listar todas las colecciones ****************
    @GetMapping("/ListALL")
    public ResponseEntity<List<CollectionEntity>> ListALL() {
        return ResponseEntity.ok(collectionService.findAll());
    }
    //******************* Listar x id *********************
    @GetMapping("/list/{id}")
    public ResponseEntity<List<CollectionEntity>> List
    (@PathVariable("id") Long id) {
        if(id == null){
            return ResponseEntity.badRequest().body(collectionService.findAll());
        }
        return ResponseEntity.ok().body(Collections.singletonList(collectionService.findById(id)));
    }

    //***************** Actualizar coleccion ***************
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @RequestBody CollectionDTO colecionUP ,@PathVariable("id") Long id
    ){
        CollectionEntity collection = collectionService.update(id, colecionUP);
        if(collection == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontro el objeto");
        }
        return ResponseEntity.ok().body("Collecion actualizada");
    }

    //************* Eliminar una colección *****************************
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        //verificamos que el id no sea nulo
        if (id == null) {
            return ResponseEntity.badRequest().body("No se encontro el objeto");
        }
        collectionService.delete(id);
        return ResponseEntity.ok().body("Se elimino la collection");
    }

    //************************ BUSCAR POR NOMBRE *************************
    @GetMapping("/getname")
    public ResponseEntity<List<CollectionEntity>> buscarPorNombre(@RequestParam("nombre") String nombre) {
        List<CollectionEntity> resultado = collectionService.findByName(nombre);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(resultado);
    }




}
