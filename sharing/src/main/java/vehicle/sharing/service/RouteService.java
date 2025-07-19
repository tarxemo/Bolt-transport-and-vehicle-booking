package vehicle.sharing.service;

// RouteService.java

import vehicle.sharing.model.Route;
import java.util.List;

public interface RouteService {
    Route saveRoute(Route route);
    Route getRouteById(Long id);
    List<Route> getAllRoutes();
    List<Route> getRoutesByVehicle(Long vehicleId);
    List<Route> findRoutesBetweenLocations(Long fromLocationId, Long toLocationId);
    void deleteRoute(Long id);
}
