package vehicle.sharing.repository;

// VehicleRequesterRepository.java

import vehicle.sharing.model.VehicleRequester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRequesterRepository extends JpaRepository<VehicleRequester, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
