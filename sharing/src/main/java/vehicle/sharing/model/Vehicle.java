package vehicle.sharing.model;

// Vehicle.java

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false, unique = true)
    private String licensePlate;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private String vehicleType;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private VehicleOwner owner;

    @ManyToOne
    @JoinColumn(name = "current_location_id")
    private Location currentLocation;

    @Column(nullable = false)
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;


    @OneToMany(mappedBy = "vehicle")
    private List<Booking> bookings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleOwner getOwner() {
        return owner;
    }

    public void setOwner(VehicleOwner owner) {
        this.owner = owner;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}