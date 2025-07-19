package vehicle.sharing.service.impl;

// VehicleRequesterServiceImpl.java

import org.springframework.security.crypto.password.PasswordEncoder;
import vehicle.sharing.model.*;
import vehicle.sharing.repository.*;
import vehicle.sharing.service.VehicleRequesterService;
import vehicle.sharing.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleRequesterServiceImpl implements VehicleRequesterService {

    @Autowired
    private VehicleRequesterRepository vehicleRequesterRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private VehicleRequesterRepository requesterRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthService authService;


    @Override
    public List<Vehicle> searchAvailableVehicles(Long fromLocationId, Long toLocationId) {
        List<Route> routes = routeRepository.findByStartingPointIdAndDestinationPointId(fromLocationId, toLocationId);

        List<Vehicle> availableVehicles = new ArrayList<>();

        for (Route route : routes) {
            for (Vehicle vehicle : route.getVehicles()) {
                if (vehicle.isAvailable()
                        && vehicle.getCurrentLocation() != null
                        && vehicle.getCurrentLocation().getId().equals(route.getStartingPoint().getId())) {
                    availableVehicles.add(vehicle);
                }
            }
        }

        return availableVehicles;
    }




    @Override
    public Booking requestRide(Long vehicleId, Long requesterId, Long pickupLocationId,
                               Long dropoffLocationId, LocalDateTime pickupTime) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
        VehicleRequester requester = vehicleRequesterRepository.findById(requesterId).orElse(null);
        Location pickupLocation = locationRepository.findById(pickupLocationId).orElse(null);
        Location dropoffLocation = locationRepository.findById(dropoffLocationId).orElse(null);

        if (vehicle != null && requester != null && pickupLocation != null && dropoffLocation != null) {
            Booking booking = new Booking();
            booking.setVehicle(vehicle);
            booking.setRequester(requester);
            booking.setPickupLocation(pickupLocation);
            booking.setDropoffLocation(dropoffLocation);
            booking.setPickupTime(pickupTime);
            booking.setBookingTime(LocalDateTime.now());
            booking.setStatus("Pending");
            Route route = null;
            // Calculate fare based on route (simplified)
//            Route route = routeRepository.findByVehicleIdAndStartingPointIdAndDestinationPointId(
//                    vehicleId, pickupLocationId, dropoffLocationId).stream().findFirst().orElse(null);
            if (route != null) {
                booking.setFare(route.getBasePrice());
            } else {
                // Default fare if no specific route found
                booking.setFare(50.0); // This should be configurable
            }

            return bookingRepository.save(booking);
        }
        return null;
    }
    @Override
    public VehicleRequester registerRequester(VehicleRequester requester) {
        if (requesterRepository.existsByUsername(requester.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        if (requesterRepository.existsByEmail(requester.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        requester.setRole(Role.REQUESTER);
        requester.setPassword(passwordEncoder.encode(requester.getPassword()));
        requester.setEnabled(true);
        return requesterRepository.save(requester);
    }

    @Override
    public List<Booking> getMyBookings(Long requesterId) {
        return bookingRepository.findByRequesterId(requesterId);
    }

    @Override
    public Booking getBookingDetails(Long bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }
}
