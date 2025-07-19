package vehicle.sharing.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import vehicle.sharing.model.Admin;
import vehicle.sharing.model.User;
import vehicle.sharing.model.VehicleOwner;
import vehicle.sharing.model.VehicleRequester;

import java.io.IOException;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();

        if (user instanceof Admin) {
            response.sendRedirect("/admin/dashboard");
        } else if (user instanceof VehicleOwner) {
            response.sendRedirect("/owner/dashboard");
        } else if (user instanceof VehicleRequester) {
            response.sendRedirect("/requester/dashboard");
        } else {
            response.sendRedirect("/login?error"); // fallback case
        }
    }
}
