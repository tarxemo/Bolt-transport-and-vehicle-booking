package vehicle.sharing.service;

// VehicleOwnerService.java

import vehicle.sharing.model.Vehicle;
import vehicle.sharing.model.VehicleOwner;
import vehicle.sharing.model.Booking;
import java.util.List;

public interface VehicleOwnerService {
    VehicleOwner registerOwner(VehicleOwner owner);
    Vehicle addVehicle(Vehicle vehicle, Long ownerId);
    Vehicle updateVehicle(Vehicle vehicle);
    void deleteVehicle(Long vehicleId);
    List<Vehicle> getAllVehiclesByOwner(Long ownerId);
    List<Booking> getBookingRequests(Long ownerId);
    Booking approveBooking(Long bookingId);
    void rejectBooking(Long bookingId);
    Vehicle updateVehicleLocation(Long vehicleId, Long locationId, boolean isAvailable);
    void registerOwnerWithVehicle(VehicleOwner owner, String make, String model, int year, String license, String type);
    VehicleOwner findByUsername(String username);
}
