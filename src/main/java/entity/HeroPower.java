package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "HeroPowers") // Adjust the table name as needed
public class HeroPower implements Serializable {


    @Id
    @ManyToOne
    @JoinColumn(name = "HeroID")
    private Hero hero;

    @Id
    @ManyToOne
    @JoinColumn(name = "PowerID")
    private Powers power;

    // Constructors
    public HeroPower() {
    }

    public HeroPower(Hero hero, Powers power) {
        this.hero = hero;
        this.power = power;
    }

    // Getters and Setters


    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Powers getPower() {
        return power;
    }

    public void setPower(Powers power) {
        this.power = power;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "HeroPower{" +
                "Power=" + (power != null ? power.getPowerID() : null) +
                ", Hero=" + (hero != null ? hero.getHeroId() : null) +
                '}';
    }
}
