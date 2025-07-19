package vehicle.sharing.service.impl;

// RouteServiceImpl.java

import vehicle.sharing.model.Route;
import vehicle.sharing.repository.RouteRepository;
import vehicle.sharing.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Route getRouteById(Long id) {
        return routeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public List<Route> getRoutesByVehicle(Long vehicleId) {
        return routeRepository.findByVehicleId(vehicleId);
    }

    @Override
    public List<Route> findRoutesBetweenLocations(Long fromLocationId, Long toLocationId) {
        return routeRepository.findByStartingPointIdAndDestinationPointId(fromLocationId, toLocationId);
    }

    @Override
    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }
}

