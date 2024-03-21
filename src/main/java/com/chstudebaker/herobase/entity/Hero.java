/**
 * Represents a hero entity with its attributes and associations.
 */
package com.chstudebaker.herobase.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Hero")
public class Hero {

    // Unique identifier for the hero
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heroId")
    private int heroId;

    // Code name of the hero
    @Column(name = "codeName", nullable = false)
    private String codeName;

    // Real name of the hero
    @Column(name = "realName", nullable = false)
    private String realName;

    // Biography of the hero
    @Column(name = "Bio")
    private String bio;

    // Alignment of the hero (e.g., good, evil)
    @Column(name = "Alignment")
    private String alignment;

    // Image path of the hero
    @Column(name = "Images")
    private String images;

    // Descriptions of the hero
    @Column(name = "Descriptions")
    private String descriptions;

    // Personality traits of the hero
    @Column(name = "Personality")
    private String personality;

    // Height of the hero
    @Column(name = "Height")
    private String height;

    // Weight of the hero
    @Column(name = "Weight")
    private String weight;

    // Emblem representing the hero
    @Column(name = "Emblem")
    private String emblem;

    // Powers associated with the hero
    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Powers> powers;

    // Equipment associated with the hero
    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Equipment> equipment;

    // Default constructor
    public Hero() {
        this.powers = new ArrayList<>();
        this.equipment = new ArrayList<>();
    }

    // Constructor with all fields
    public Hero(String codeName, String realName, String bio, String alignment, String images, String descriptions, String personality, String height, String weight, String emblem) {
        this.codeName = codeName;
        this.realName = realName;
        this.bio = bio;
        this.alignment = alignment;
        this.images = images;
        this.descriptions = descriptions;
        this.personality = personality;
        this.height = height;
        this.weight = weight;
        this.emblem = emblem;
        this.powers = new ArrayList<>();
        this.equipment = new ArrayList<>();
    }

    // Getters and setters for other fields

    /**
     * Retrieves the ID of the hero.
     * @return The hero ID.
     */
    public int getHeroId() {
        return heroId;
    }

    /**
     * Sets the ID of the hero.
     * @param heroId The hero ID to set.
     */
    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    // Other getters and setters...

    /**
     * Retrieves the powers associated with the hero as a concatenated string.
     * @return A string containing the descriptions of the powers.
     */
    public String getPowersAsString() {
        if (powers != null && !powers.isEmpty()) {
            return powers.stream()
                    .map(Powers::getDescription)
                    .collect(Collectors.joining(", "));
        } else {
            return "No powers available.";
        }
    }

    /**
     * Retrieves the equipment associated with the hero.
     * @return The list of equipment associated with the hero.
     */
    public List<Equipment> getEquipment() {
        return equipment;
    }

    /**
     * Sets the equipment associated with the hero.
     * @param equipment The list of equipment to set.
     */
    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "heroId=" + heroId +
                ", codeName='" + codeName + '\'' +
                ", realName='" + realName + '\'' +
                ", bio='" + bio + '\'' +
                ", alignment='" + alignment + '\'' +
                ", images='" + images + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", personality='" + personality + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", emblem='" + emblem + '\'' +
                ", powers=" + powers +
                ", equipment=" + equipment +
                '}';
    }
}
