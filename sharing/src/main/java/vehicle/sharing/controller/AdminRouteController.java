package vehicle.sharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vehicle.sharing.model.Route;
import vehicle.sharing.model.Vehicle;
import vehicle.sharing.model.Location;
import vehicle.sharing.service.RouteService;
import vehicle.sharing.service.VehicleService;
import vehicle.sharing.service.LocationService;

import java.util.List;

@Controller
@RequestMapping("/admin/routes")
public class AdminRouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private LocationService locationService;

    @GetMapping
    public String showAllRoutes(Model model) {
        List<Route> routes = routeService.getAllRoutes();
        model.addAttribute("routes", routes);
        return "admin/routes";  // routes.html page
    }

    @GetMapping("/add")
    public String showAddRouteForm(Model model) {
        model.addAttribute("route", new Route());
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        model.addAttribute("locations", locationService.getAllLocations());
        return "admin/route-form";
    }

    @PostMapping("/save")
    public String saveRoute(
            @ModelAttribute Route route,
            @RequestParam Long startingPointId,
            @RequestParam Long destinationPointId
    ) {
        Location startingPoint = locationService.getLocationById(startingPointId);
        System.out.println(startingPoint.getId());
        Location destinationPoint = locationService.getLocationById(destinationPointId);
        System.out.println(destinationPoint.getId());
        route.setStartingPoint(startingPoint);
        route.setDestinationPoint(destinationPoint);
        System.out.println("********************************************");
        routeService.saveRoute(route);
        return "redirect:/admin/routes";
    }


//    @PostMapping("/save")
//    public String saveRoute(@ModelAttribute Route route) {
//        routeService.saveRoute(route);
//        return "redirect:/admin/routes";
//    }

    @GetMapping("/edit/{id}")
    public String showEditRouteForm(@PathVariable Long id, Model model) {
        Route route = routeService.getRouteById(id);
        List<Route> routes = routeService.getAllRoutes();
        model.addAttribute("route", route);
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        model.addAttribute("locations", locationService.getAllLocations());
        model.addAttribute("routes", routes);
        return "admin/route-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return "redirect:/admin/routes";
    }
}

