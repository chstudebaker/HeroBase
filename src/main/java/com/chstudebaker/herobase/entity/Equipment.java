/**
 * Represents an equipment entity with its attributes and associations.
 */
package com.chstudebaker.herobase.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "equipment")
public class Equipment {

    // Unique identifier for the equipment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipmentId")
    private int equipmentId;

    // Name of the equipment
    @Column(name = "name")
    private String name;

    // Description of the equipment
    @Column(name = "description")
    private String description;

    // Image path of the equipment
    @Column(name = "Images")
    private String images;

    // Hero associated with the equipment
    @ManyToOne
    @JoinColumn(name = "heroId")
    private Hero hero;

    // Default constructor
    public Equipment() {
    }

    // Constructor with all fields
    public Equipment(String name, String description, String images, Hero hero) {
        this.name = name;
        this.description = description;
        this.images = images;
        this.hero = hero;
    }

    // Getters and setters

    /**
     * Retrieves the ID of the associated hero.
     * @return The hero ID.
     */
    public int getHeroID() {
        return hero != null ? hero.getHeroId() : 0;
    }

    /**
     * Sets the ID of the associated hero.
     * @param heroID The hero ID to set.
     */
    public void setHeroID(int heroID) {
        if (hero == null) {
            hero = new Hero();
        }
        hero.setHeroId(heroID);
    }

    /**
     * Retrieves the equipment ID.
     * @return The equipment ID.
     */
    public int getEquipmentId() {
        return equipmentId;
    }

    /**
     * Sets the equipment ID.
     * @param equipmentId The equipment ID to set.
     */
    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    /**
     * Retrieves the name of the equipment.
     * @return The equipment name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the equipment.
     * @param name The equipment name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the description of the equipment.
     * @return The equipment description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the equipment.
     * @param description The equipment description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the image path of the equipment.
     * @return The image path of the equipment.
     */
    public String getImages() {
        return images;
    }

    /**
     * Sets the image path of the equipment.
     * @param images The image path to set.
     */
    public void setImages(String images) {
        this.images = images;
    }

    /**
     * Retrieves the hero associated with the equipment.
     * @return The hero associated with the equipment.
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * Sets the hero associated with the equipment.
     * @param hero The hero to associate with the equipment.
     */
    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
