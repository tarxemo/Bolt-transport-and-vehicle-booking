package vehicle.sharing.model;

// Location.java
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String postalCode;

    @OneToMany(mappedBy = "currentLocation")
    private List<Vehicle> vehiclesAtLocation;

    @OneToMany(mappedBy = "startingPoint")
    private List<Route> startingRoutes;

    @OneToMany(mappedBy = "destinationPoint")
    private List<Route> destinationRoutes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public List<Vehicle> getVehiclesAtLocation() {
        return vehiclesAtLocation;
    }

    public void setVehiclesAtLocation(List<Vehicle> vehiclesAtLocation) {
        this.vehiclesAtLocation = vehiclesAtLocation;
    }

    public List<Route> getStartingRoutes() {
        return startingRoutes;
    }

    public void setStartingRoutes(List<Route> startingRoutes) {
        this.startingRoutes = startingRoutes;
    }

    public List<Route> getDestinationRoutes() {
        return destinationRoutes;
    }

    public void setDestinationRoutes(List<Route> destinationRoutes) {
        this.destinationRoutes = destinationRoutes;
    }
}
