package vehicle.sharing.model;

// VehicleRequester.java

import jakarta.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class VehicleRequester extends User {
    @OneToMany(mappedBy = "requester")
    private List<Booking> bookings;

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
