package vehicle.sharing.service.impl;

// AdminServiceImpl.java

import vehicle.sharing.model.*;
import vehicle.sharing.repository.*;
import vehicle.sharing.service.AdminService;
import vehicle.sharing.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private VehicleOwnerRepository vehicleOwnerRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SystemSettingsRepository systemSettingsRepository;

    @Autowired
    private AuthService authService;

    @Override
    public Admin createAdmin(Admin admin) {
        return (Admin) authService.register(admin, "ADMIN");
    }

    @Override
    public List<VehicleOwner> getPendingApprovalOwners() {
        return vehicleOwnerRepository.findByIsApproved(false);
    }

    @Override
    public VehicleOwner approveOwner(Long ownerId) {
        VehicleOwner owner = vehicleOwnerRepository.findById(ownerId).orElse(null);
        if (owner != null) {
            owner.setIsApproved(true);
            return vehicleOwnerRepository.save(owner);
        }
        return null;
    }

    @Override
    public void rejectOwner(Long ownerId) {
        vehicleOwnerRepository.deleteById(ownerId);
    }

    @Override
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(Long locationId) {
        locationRepository.deleteById(locationId);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public SystemSettings updateSystemSetting(SystemSettings setting) {
        return systemSettingsRepository.save(setting);
    }

    @Override
    public List<SystemSettings> getAllSystemSettings() {
        return systemSettingsRepository.findAll();
    }

    @Override
    public List<VehicleOwner> getPendingVehicleOwners() {
        return vehicleOwnerRepository.findPendingVehicleOwners();
    }
}
