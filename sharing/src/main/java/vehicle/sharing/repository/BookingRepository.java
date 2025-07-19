package vehicle.sharing.repository;

// BookingRepository.java

import vehicle.sharing.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByVehicleId(Long vehicleId);
    List<Booking> findByRequesterId(Long requesterId);
    List<Booking> findByStatus(String status);
    List<Booking> findByVehicleOwnerId(Long ownerId);
    List<Booking> findByVehicleOwnerIdOrderByBookingTimeDesc(Long ownerId);
}
