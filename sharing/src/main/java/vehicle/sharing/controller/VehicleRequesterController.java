package vehicle.sharing.controller;

// VehicleRequesterController.java

import vehicle.sharing.model.*;
import vehicle.sharing.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/requester")
public class VehicleRequesterController extends BaseController {

    private final VehicleRequesterService vehicleRequesterService;
    private final LocationService locationService;
    private final BookingService bookingService;
    private final VehicleService vehicleService;

    public VehicleRequesterController(VehicleRequesterService vehicleRequesterService,
                                      LocationService locationService,
                                      VehicleService vehicleService,
                                      BookingService bookingService) {
        this.vehicleRequesterService = vehicleRequesterService;
        this.locationService = locationService;
        this.bookingService = bookingService;
        this.vehicleService=vehicleService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        User currentUser = getCurrentUser();
        if (currentUser instanceof VehicleRequester) {
            List<Booking> bookings = vehicleRequesterService.getMyBookings(currentUser.getId());
            model.addAttribute("bookings", bookings);
            addCommonAttributes(model);
            return "requester/dashboard";
        }
        return "redirect:/auth/access-denied";
    }

    // Vehicle Search and Booking
    @GetMapping("/search")
    public String showSearchForm(Model model) {
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        addCommonAttributes(model);
        return "requester/search";
    }

    @PostMapping("/search/results")
    public String searchVehicles(@RequestParam Long fromLocationId,
                                 @RequestParam Long toLocationId,
                                 Model model) {
        List<Vehicle> vehicles = vehicleRequesterService.searchAvailableVehicles(fromLocationId, toLocationId);
        Location fromLocation = locationService.getLocationById(fromLocationId);
        Location toLocation = locationService.getLocationById(toLocationId);

        model.addAttribute("vehicles", vehicles);
        model.addAttribute("fromLocation", fromLocation);
        model.addAttribute("toLocation", toLocation);
        addCommonAttributes(model);
        return "requester/search-results";
    }

    @GetMapping("/book/{vehicleId}")
    public String showBookingForm(@PathVariable Long vehicleId,
                                  @RequestParam Long fromLocationId,
                                  @RequestParam Long toLocationId,
                                  Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        Location fromLocation = locationService.getLocationById(fromLocationId);
        Location toLocation = locationService.getLocationById(toLocationId);

        model.addAttribute("vehicle", vehicle);
        model.addAttribute("fromLocation", fromLocation);
        model.addAttribute("toLocation", toLocation);
        model.addAttribute("booking", new Booking());
        addCommonAttributes(model);
        return "requester/booking-form";
    }

    @PostMapping("/book/save")
    public String saveBooking(@RequestParam Long vehicleId,
                              @RequestParam Long fromLocationId,
                              @RequestParam Long toLocationId,
                              @RequestParam String pickupTime,
                              Model model) {
        User currentUser = getCurrentUser();
        if (currentUser instanceof VehicleRequester) {
            LocalDateTime pickupDateTime = LocalDateTime.parse(pickupTime);
            vehicleRequesterService.requestRide(vehicleId, currentUser.getId(),
                    fromLocationId, toLocationId, pickupDateTime);
            return "redirect:/requester/dashboard";
        }
        return "redirect:/auth/access-denied";
    }

    // Booking Management
    @GetMapping("/bookings")
    public String showMyBookings(Model model) {
        User currentUser = getCurrentUser();
        if (currentUser instanceof VehicleRequester) {
            List<Booking> bookings = vehicleRequesterService.getMyBookings(currentUser.getId());
            model.addAttribute("bookings", bookings);
            addCommonAttributes(model);
            return "requester/bookings";
        }
        return "redirect:/auth/access-denied";
    }

    @GetMapping("/bookings/{id}")
    public String showBookingDetails(@PathVariable Long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        model.addAttribute("booking", booking);
        addCommonAttributes(model);
        return "requester/booking-details";
    }
}
