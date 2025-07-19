package vehicle.sharing.repository;

import org.springframework.data.jpa.repository.Query;
import vehicle.sharing.model.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleOwnerRepository extends JpaRepository<VehicleOwner, Long> {
    List<VehicleOwner> findByIsApproved(boolean isApproved);
    @Query("SELECT vo FROM VehicleOwner vo WHERE vo.isApproved = false")
    List<VehicleOwner> findPendingVehicleOwners();
    Optional<VehicleOwner> findByUsername(String username);

}

