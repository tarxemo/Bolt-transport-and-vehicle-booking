package vehicle.sharing.service.impl;

// LocationServiceImpl.java

import vehicle.sharing.model.Location;
import vehicle.sharing.repository.LocationRepository;
import vehicle.sharing.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location getLocationById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public List<Location> searchLocations(String query) {
        return locationRepository.findByNameContainingIgnoreCase(query);
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}
