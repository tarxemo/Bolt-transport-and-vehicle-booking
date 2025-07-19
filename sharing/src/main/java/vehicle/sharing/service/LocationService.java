package vehicle.sharing.service;

// LocationService.java

import vehicle.sharing.model.Location;
import java.util.List;

public interface LocationService {
    Location saveLocation(Location location);
    Location getLocationById(Long id);
    List<Location> getAllLocations();
    List<Location> searchLocations(String query);
    void deleteLocation(Long id);
}
