package vehicle.sharing.service;

// BookingService.java

import vehicle.sharing.model.Booking;
import java.util.List;

public interface BookingService {
    Booking saveBooking(Booking booking);
    Booking getBookingById(Long id);
    List<Booking> getAllBookings();
    List<Booking> getBookingsByStatus(String status);
    void updateBookingStatus(Long bookingId, String status);
    void deleteBooking(Long id);
    List<Booking> getRecentBookingsForOwner(Long ownerId);
}
