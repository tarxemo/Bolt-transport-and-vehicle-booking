package vehicle.sharing.repository;

// VehicleRepository.java

import vehicle.sharing.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByOwnerId(Long ownerId);
    List<Vehicle> findByIsAvailable(boolean isAvailable);
    List<Vehicle> findByCurrentLocationIdAndIsAvailable(Long locationId, boolean isAvailable);
    List<Vehicle> findByVehicleTypeAndIsAvailable(String vehicleType, boolean isAvailable);
}
