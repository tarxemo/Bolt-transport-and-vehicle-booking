package vehicle.sharing.controller;

// RegistrationController.java

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vehicle.sharing.model.VehicleOwner;
import vehicle.sharing.model.VehicleRequester;
import vehicle.sharing.service.VehicleOwnerService;
import vehicle.sharing.service.VehicleRequesterService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private VehicleOwnerService ownerService;

    @Autowired
    private VehicleRequesterService requesterService;

    @GetMapping("/owner")
    public String showOwnerRegistrationForm() {
        return "registration/owner-register";
    }

    @GetMapping("/requester")
    public String showRequesterRegistrationForm() {
        return "registration/requester-register";
    }


    @PostMapping("/requester")
    public String registerRequester(@ModelAttribute("requester") VehicleRequester requester, Model model) {
        try {
            requesterService.registerRequester(requester);
            return "redirect:/login?success";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register-requester";
        }
    }


    @PostMapping("/owner")
    public String registerOwner(HttpServletRequest request, Model model) {
        try {
            // Extract form fields manually
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            String vehicleMake = request.getParameter("vehicleMake");
            String vehicleModel = request.getParameter("vehicleModel");
            int vehicleYear = Integer.parseInt(request.getParameter("vehicleYear"));
            String vehicleLicense = request.getParameter("vehicleLicense");
            String vehicleType = request.getParameter("vehicleType");

            VehicleOwner owner = new VehicleOwner();
            owner.setFirstName(firstName);
            owner.setLastName(lastName);
            owner.setEmail(email);
            owner.setPhoneNumber(phoneNumber);
            owner.setUsername(username);
            owner.setPassword(password);  // Service should encode password
            owner.setIsApproved(false);   // New owners are unapproved by default

            ownerService.registerOwnerWithVehicle(owner, vehicleMake, vehicleModel, vehicleYear, vehicleLicense, vehicleType);

            return "redirect:/login?success";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "registration/owner-register";
        }
    }

}