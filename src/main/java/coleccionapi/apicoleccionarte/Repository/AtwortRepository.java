package coleccionapi.apicoleccionarte.Repository;

import coleccionapi.apicoleccionarte.Entity.AtworksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AtwortRepository extends JpaRepository<AtworksEntity, Long> {
    @Modifying
    @Query("delete from AtworksEntity where id_atwork=?1")
    public void eliminarAtwork(Long id_atwork);
}
