package vehicle.sharing.service;

// VehicleRequesterService.java

import vehicle.sharing.model.Vehicle;
import vehicle.sharing.model.VehicleRequester;
import vehicle.sharing.model.Booking;
import vehicle.sharing.model.Location;
import java.util.List;
import java.time.LocalDateTime;

public interface VehicleRequesterService {
    VehicleRequester registerRequester(VehicleRequester requester);
    List<Vehicle> searchAvailableVehicles(Long fromLocationId, Long toLocationId);
    Booking requestRide(Long vehicleId, Long requesterId, Long pickupLocationId, Long dropoffLocationId, LocalDateTime pickupTime);
    List<Booking> getMyBookings(Long requesterId);
    Booking getBookingDetails(Long bookingId);

}
