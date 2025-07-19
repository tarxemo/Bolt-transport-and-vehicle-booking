package vehicle.sharing.model;

// Route.java

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "route")
    private List<Vehicle> vehicle;

    @ManyToOne
    @JoinColumn(name = "starting_point_id", nullable = false)
    private Location startingPoint;

    @ManyToOne
    @JoinColumn(name = "destination_point_id", nullable = false)
    private Location destinationPoint;

    @Column(nullable = false)
    private double distance;

    @Column(nullable = false)
    private double estimatedTime;

    @Column(nullable = false)
    private double basePrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Vehicle> getVehicles() {
        return vehicle;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicle = vehicles;
    }

    public Location getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Location startingPoint) {
        this.startingPoint = startingPoint;
    }

    public Location getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(Location destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(double estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
}
