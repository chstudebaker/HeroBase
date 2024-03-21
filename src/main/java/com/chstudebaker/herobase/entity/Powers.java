/**
 * Represents a power possessed by a hero with its attributes and associations.
 */
package com.chstudebaker.herobase.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Powers")
public class Powers {

    // Unique identifier for the power
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PowerID")
    private int powerID;

    // Description of the power
    @Column(name = "Description", nullable = false)
    private String description;

    // Explanation or details of the power
    @Column(name = "Explanation")
    private String explanation;

    // Hero associated with the power
    @ManyToOne
    @JoinColumn(name = "HeroID")
    private Hero hero;

    // Default constructor
    public Powers() {
    }

    // Constructor with all fields
    public Powers(String description, String explanation, Hero hero) {
        this.description = description;
        this.explanation = explanation;
        this.hero = hero;
    }

    // Getters and setters for other fields

    /**
     * Retrieves the ID of the power.
     * @return The power ID.
     */
    public int getPowerID() {
        return powerID;
    }

    /**
     * Sets the ID of the power.
     * @param powerID The power ID to set.
     */
    public void setPowerID(int powerID) {
        this.powerID = powerID;
    }

    /**
     * Retrieves the description of the power.
     * @return The description of the power.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the power.
     * @param description The description of the power to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the explanation or details of the power.
     * @return The explanation or details of the power.
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * Sets the explanation or details of the power.
     * @param explanation The explanation or details of the power to set.
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /**
     * Retrieves the hero associated with the power.
     * @return The hero associated with the power.
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * Sets the hero associated with the power.
     * @param hero The hero associated with the power to set.
     */
    public void setHero(Hero hero) {
        this.hero = hero;
    }

    /**
     * Retrieves the ID of the hero associated with the power.
     * @return The ID of the hero associated with the power.
     */
    public int getHeroID() {
        return hero != null ? hero.getHeroId() : 0;
    }

    /**
     * Sets the ID of the hero associated with the power.
     * @param heroID The ID of the hero associated with the power to set.
     */
    public void setHeroID(int heroID) {
        if (hero == null) {
            hero = new Hero();
        }
        hero.setHeroId(heroID);
    }

    @Override
    public String toString() {
        return "Powers{" +
                "powerID=" + powerID +
                ", description='" + description + '\'' +
                '}';
    }
}
