package vehicle.sharing.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import vehicle.sharing.model.*;
import vehicle.sharing.repository.*;
import vehicle.sharing.service.VehicleOwnerService;
import vehicle.sharing.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class VehicleOwnerServiceImpl implements VehicleOwnerService {

        @Autowired
        private PasswordEncoder passwordEncoder;


        @Autowired
        private VehicleOwnerRepository ownerRepository;

        @Autowired
        private VehicleOwnerRepository vehicleOwnerRepository;

        @Autowired
        private VehicleRepository vehicleRepository;

        @Autowired
        private BookingRepository bookingRepository;

        @Autowired
        private LocationRepository locationRepository;

        @Autowired
        private AuthService authService;

        @Override
        public VehicleOwner registerOwner(VehicleOwner owner) {
            return (VehicleOwner) authService.register(owner, "OWNER");
        }

        @Override
        public Vehicle addVehicle(Vehicle vehicle, Long ownerId) {
            VehicleOwner owner = vehicleOwnerRepository.findById(ownerId).orElse(null);
            if (owner != null) {
                vehicle.setOwner(owner);
                return vehicleRepository.save(vehicle);
            }
            return null;
        }

        @Override
        public Vehicle updateVehicle(Vehicle vehicle) {
            return vehicleRepository.save(vehicle);
        }

        @Override
        public void deleteVehicle(Long vehicleId) {
            vehicleRepository.deleteById(vehicleId);
        }

        @Override
        public List<Vehicle> getAllVehiclesByOwner(Long ownerId) {
            return vehicleRepository.findByOwnerId(ownerId);
        }

        @Override
        public List<Booking> getBookingRequests(Long ownerId) {
            return bookingRepository.findByVehicleOwnerId(ownerId);
        }

        @Override
        public Booking approveBooking(Long bookingId) {
            Booking booking = bookingRepository.findById(bookingId).orElse(null);
            if (booking != null) {
                booking.setStatus("Accepted");
                Vehicle vehicle = booking.getVehicle();
//                vehicle.setIsAvailable(false);
                vehicleRepository.save(vehicle);
                return bookingRepository.save(booking);
            }
            return null;
        }

        @Override
        public void rejectBooking(Long bookingId) {
            Booking booking = bookingRepository.findById(bookingId).orElse(null);
            if (booking != null) {
                booking.setStatus("Rejected");
                bookingRepository.save(booking);
            }
        }

        @Override
        public Vehicle updateVehicleLocation(Long vehicleId, Long locationId, boolean isAvailable) {
            Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
            Location location = locationRepository.findById(locationId).orElse(null);
            if (vehicle != null && location != null) {
                vehicle.setCurrentLocation(location);
//                vehicle.setIsAvailable(isAvailable);
                return vehicleRepository.save(vehicle);
            }
            return null;
        }

    public void registerOwnerWithVehicle(VehicleOwner owner, String make, String model, int year, String license, String type) {
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        owner.setEnabled(true);

//        Role role = roleRepository.findByName("OWNER");

        owner.setRole(Role.OWNER);

        Vehicle vehicle = new Vehicle();
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setYear(year);
        vehicle.setLicensePlate(license);
        vehicle.setVehicleType(type);
        vehicle.setOwner(owner);

        ownerRepository.save(owner);
        vehicleRepository.save(vehicle);
    }

    public VehicleOwner findByUsername(String username) {
        return ownerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
    }

}
