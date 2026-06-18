package ucr.ac.cr.creativeSpace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.creativeSpace.model.Space;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Integer> {

//por tipo
    @Query("SELECT cp FROM Space cp WHERE cp.type = :type")
    Space findByType(@Param("type") String type);
//por ubicación
    @Query("SELECT cp FROM Space cp WHERE cp.ubication=:ubication")
    Space findByUbication(@Param("ubication") String ubication);
}
