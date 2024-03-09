package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Powers")
public class Powers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PowerID")
    private int powerID;

    @Column(name = "Description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "power", fetch = FetchType.EAGER)
    private List<HeroPower> heroPowers;

    // Constructors, getters, and setters

    public Powers() {
    }

    public Powers(String description) {
        this.description = description;
    }

    public int getPowerID() {
        return powerID;
    }

    public void setPowerID(int powerID) {
        this.powerID = powerID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<HeroPower> getHeroPowers() {
        return heroPowers;
    }

    public void setHeroPowers(List<HeroPower> heroPowers) {
        this.heroPowers = heroPowers;
    }

    @Override
    public String toString() {
        return "Powers{" +
                "powerID=" + powerID +
                ", description='" + description + '\'' +
                '}';
    }
}
