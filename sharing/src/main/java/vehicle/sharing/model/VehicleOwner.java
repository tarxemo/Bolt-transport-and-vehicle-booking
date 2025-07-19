package vehicle.sharing.model;

// VehicleOwner.java

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class VehicleOwner extends User {
    @Column(nullable = false)
    private boolean isApproved;

    @OneToMany(mappedBy = "owner")
    private List<Vehicle> vehicles;

    public boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean approved) {
        isApproved = approved;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}