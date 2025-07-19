package vehicle.sharing.service;

// AdminService.java

import vehicle.sharing.model.Admin;
import vehicle.sharing.model.VehicleOwner;
import vehicle.sharing.model.Location;
import vehicle.sharing.model.SystemSettings;
import java.util.List;

public interface AdminService {
    Admin createAdmin(Admin admin);
    List<VehicleOwner> getPendingApprovalOwners();
    VehicleOwner approveOwner(Long ownerId);
    void rejectOwner(Long ownerId);
    Location addLocation(Location location);
    Location updateLocation(Location location);
    void deleteLocation(Long locationId);
    List<Location> getAllLocations();
    SystemSettings updateSystemSetting(SystemSettings setting);
    List<SystemSettings> getAllSystemSettings();

    List<VehicleOwner> getPendingVehicleOwners();
}
