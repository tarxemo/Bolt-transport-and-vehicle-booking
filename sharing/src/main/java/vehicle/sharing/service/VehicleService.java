package vehicle.sharing.service;

// VehicleService.java
import vehicle.sharing.model.Vehicle;
import java.util.List;

public interface VehicleService {
    Vehicle saveVehicle(Vehicle vehicle);
    Vehicle getVehicleById(Long id);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAvailableVehicles();
    List<Vehicle> getVehiclesByType(String vehicleType);
    void deleteVehicle(Long id);
}
