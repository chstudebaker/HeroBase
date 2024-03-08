package entity;

import javax.persistence.*;

@Entity
@Table(name = "abilities")
public class Abilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "abilityName")
    private String abilityName;

    @Column(name = "description")
    private String description;

    // Constructors
    public Abilities() {
        // Default constructor
    }

    public Abilities(String abilityName, String description) {
        this.abilityName = abilityName;
        this.description = description;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString method
    @Override
    public String toString() {
        return "Abilities{" +
                "id=" + id +
                ", abilityName='" + abilityName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
