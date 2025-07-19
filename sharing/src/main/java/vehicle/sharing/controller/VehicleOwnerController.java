package vehicle.sharing.controller;

// VehicleOwnerController.

import org.springframework.beans.factory.annotation.Autowired;
import vehicle.sharing.model.*;
import vehicle.sharing.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/owner")
public class VehicleOwnerController extends BaseController {

    private final VehicleOwnerService vehicleOwnerService;
    private final VehicleService vehicleService;
    private final LocationService locationService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private VehicleOwnerService ownerService;
    public VehicleOwnerController(VehicleOwnerService vehicleOwnerService,
                                  VehicleService vehicleService,
                                  LocationService locationService) {
        this.vehicleOwnerService = vehicleOwnerService;
        this.vehicleService = vehicleService;
        this.locationService = locationService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        User currentUser = getCurrentUser();
        VehicleOwner owner = ownerService.findByUsername(principal.getName());
        List<Booking> recentBookings = bookingService.getRecentBookingsForOwner(owner.getId());

        model.addAttribute("recentBookings", recentBookings);
        if (currentUser instanceof VehicleOwner) {
            Long ownerId = currentUser.getId();
            List<Vehicle> vehicles = vehicleOwnerService.getAllVehiclesByOwner(ownerId);
            List<Booking> bookings = vehicleOwnerService.getBookingRequests(ownerId);

            model.addAttribute("vehicles", vehicles);
            model.addAttribute("bookings", bookings);
            addCommonAttributes(model);
            return "owner/dashboard";
        }
        return "redirect:/auth/access-denied";
    }

    // Vehicle Management
    @GetMapping("/vehicles")
    public String showAllVehicles(Model model) {
        User currentUser = getCurrentUser();
        if (currentUser instanceof VehicleOwner) {
            List<Vehicle> vehicles = vehicleOwnerService.getAllVehiclesByOwner(currentUser.getId());
            model.addAttribute("vehicles", vehicles);
            addCommonAttributes(model);
            return "owner/vehicles";
        }
        return "redirect:/auth/access-denied";
    }

    @GetMapping("/vehicles/add")
    public String showAddVehicleForm(Model model) {
        List<Location> locations = locationService.getAllLocations();
        List<Route> routes = routeService.getAllRoutes();
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("locations", locations);
        model.addAttribute("routes", routes);
        addCommonAttributes(model);
        return "owner/vehicle-form";
    }

    @PostMapping("/vehicles/save")
    public String saveVehicle(@ModelAttribute Vehicle vehicle) {
        User currentUser = getCurrentUser();
        if (currentUser instanceof VehicleOwner) {
            vehicleOwnerService.addVehicle(vehicle, currentUser.getId());
            return "redirect:/owner/vehicles";
        }
        return "redirect:/auth/access-denied";
    }

    @GetMapping("/vehicles/edit/{id}")
    public String showEditVehicleForm(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        List<Route> routes = routeService.getAllRoutes();
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("locations", locations);
        model.addAttribute("routes", routes);
        addCommonAttributes(model);
        return "owner/vehicle-form";
    }

    @PostMapping("/vehicles/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleOwnerService.deleteVehicle(id);
        return "redirect:/owner/vehicles";
    }

    // Booking Management
    @GetMapping("/bookings")
    public String showAllBookings(Model model) {
        User currentUser = getCurrentUser();
        if (currentUser instanceof VehicleOwner) {
            List<Booking> bookings = vehicleOwnerService.getBookingRequests(currentUser.getId());
            model.addAttribute("bookings", bookings);
            addCommonAttributes(model);
            return "owner/bookings";
        }
        return "redirect:/auth/access-denied";
    }

    @PostMapping("/bookings/approve/{id}")
    public String approveBooking(@PathVariable Long id) {
        vehicleOwnerService.approveBooking(id);
        return "redirect:/owner/bookings";
    }

    @PostMapping("/bookings/reject/{id}")
    public String rejectBooking(@PathVariable Long id) {
        vehicleOwnerService.rejectBooking(id);
        return "redirect:/owner/bookings";
    }

    // Vehicle Status Management
    @PostMapping("/vehicles/update-status/{id}")
    public String updateVehicleStatus(@PathVariable Long id,
                                      @RequestParam Long locationId,
                                      @RequestParam boolean isAvailable) {
        vehicleOwnerService.updateVehicleLocation(id, locationId, isAvailable);
        return "redirect:/owner/vehicles";
    }
}
