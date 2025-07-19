package vehicle.sharing.service.impl;

// BookingServiceImpl.java

import vehicle.sharing.model.Booking;
import vehicle.sharing.repository.BookingRepository;
import vehicle.sharing.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getBookingsByStatus(String status) {
        return bookingRepository.findByStatus(status);
    }

    @Override
    public void updateBookingStatus(Long bookingId, String status) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            booking.setStatus(status);
            bookingRepository.save(booking);
        }
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> getRecentBookingsForOwner(Long ownerId) {
        return bookingRepository.findByVehicleOwnerIdOrderByBookingTimeDesc(ownerId);
    }
}