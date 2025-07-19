package vehicle.sharing.controller;

import vehicle.sharing.model.*;
import vehicle.sharing.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    private final AdminService adminService;
    private final LocationService locationService;
    private final SystemSettingsService systemSettingsService;
    private final UserService userService;

    public AdminController(AdminService adminService, LocationService locationService,
                           SystemSettingsService systemSettingsService, UserService userService) {
        this.adminService = adminService;
        this.locationService = locationService;
        this.systemSettingsService = systemSettingsService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        addCommonAttributes(model);
        List<VehicleOwner> pendingOwners = adminService.getPendingVehicleOwners(); // returns List

        if (pendingOwners == null) {
            pendingOwners = new ArrayList<>();
        }

        model.addAttribute("pendingOwners", pendingOwners);
        addCommonAttributes(model);

        return "admin/dashboard";
    }

    // Owner Approval Management
    @GetMapping("/owners/pending")
    public String showPendingOwners(Model model) {
        List<VehicleOwner> pendingOwners = adminService.getPendingApprovalOwners();
        model.addAttribute("pendingOwners", pendingOwners);
        addCommonAttributes(model);
        return "admin/pending-owners";
    }

    @PostMapping("/owners/approve/{id}")
    public String approveOwner(@PathVariable Long id) {
        adminService.approveOwner(id);
        return "redirect:/admin/owners/pending";
    }

    @PostMapping("/owners/reject/{id}")
    public String rejectOwner(@PathVariable Long id) {
        adminService.rejectOwner(id);
        return "redirect:/admin/owners/pending";
    }

    // Location Management
    @GetMapping("/locations")
    public String showAllLocations(Model model) {
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        addCommonAttributes(model);
        return "admin/locations";
    }

    @GetMapping("/locations/add")
    public String showAddLocationForm(Model model) {
        model.addAttribute("location", new Location());
        addCommonAttributes(model);
        return "admin/location-form";
    }

    @PostMapping("/locations/save")
    public String saveLocation(@ModelAttribute Location location) {
        locationService.saveLocation(location);
        return "redirect:/admin/locations";
    }

    @GetMapping("/locations/edit/{id}")
    public String showEditLocationForm(@PathVariable Long id, Model model) {
        Location location = locationService.getLocationById(id);
        model.addAttribute("location", location);
        addCommonAttributes(model);
        return "admin/location-form";
    }

    @PostMapping("/locations/delete/{id}")
    public String deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return "redirect:/admin/locations";
    }

    // User Management
    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        addCommonAttributes(model);
        return "admin/users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    // System Settings
    @GetMapping("/settings")
    public String showSystemSettings(Model model) {
        List<SystemSettings> settings = systemSettingsService.getAllSettings();
        model.addAttribute("settings", settings);
        addCommonAttributes(model);
        return "admin/settings";
    }

    @PostMapping("/settings/save")
    public String saveSystemSetting(@ModelAttribute SystemSettings setting) {
        systemSettingsService.saveSetting(setting);
        return "redirect:/admin/settings";
    }
}
