package entity;

import javax.persistence.*;

@Entity
@Table(name = "Powers")
public class Powers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PowerID")
    private int powerID;

    @Column(name = "Description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "powerId")
    private Hero hero;

    // Constructors, getters, and setters

    public Powers() {
    }

    public Powers(String description, Hero hero) {
        this.description = description;
        this.hero = hero;
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

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    @Override
    public String toString() {
        return "Powers{" +
                "powerID=" + powerID +
                ", description='" + description + '\'' +
                ", hero=" + hero +
                '}';
    }
}
