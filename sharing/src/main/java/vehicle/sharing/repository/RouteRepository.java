package vehicle.sharing.repository;

// RouteRepository.java

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vehicle.sharing.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("SELECT r FROM Route r JOIN r.vehicle v WHERE v.id = :vehicleId")
    List<Route> findByVehicleId(@Param("vehicleId") Long vehicleId);
    List<Route> findByStartingPointIdAndDestinationPointId(Long startingPointId, Long destinationPointId);

}

