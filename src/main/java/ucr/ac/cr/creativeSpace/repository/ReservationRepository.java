package ucr.ac.cr.creativeSpace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.creativeSpace.model.Reservation;

import java.util.List;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByUserId(Integer id);
    List<Reservation> findBySpaceId(Integer id);
}
