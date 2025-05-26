package coleccionapi.apicoleccionarte.Repository;

import coleccionapi.apicoleccionarte.Entity.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {

    @Modifying
    @Query("delete from CollectionEntity where id_collection=?1")
    public void deleteCollecion(Long idColle);

    //buscar por nombre
    @Query("SELECT c FROM CollectionEntity c WHERE LOWER(c.name_collection) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<CollectionEntity> findByName(@Param("nombre") String nombre);
}
