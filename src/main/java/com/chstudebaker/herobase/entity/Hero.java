/**
 * Represents a hero entity with its attributes and associations.
 */
package com.chstudebaker.herobase.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "Hero")
@JsonInclude(JsonInclude.Include.NON_NULL) // Include only non-null fields in JSON serialization
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hero {

    // Unique identifier for the hero
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HeroId")
    private int heroId;

    // Code name of the hero
    @Column(name = "CodeName", nullable = false)
    private String codeName;

    // Real name of the hero
    @Column(name = "RealName", nullable = false)
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

    // UserId representing the hero
    @Column(name = "UserId")
    private String userId;

    // Powers associated with the hero
    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("hero") // Ignore this property during JSON serialization to avoid circular dependencies
    private List<Powers> powers;

    // Equipment associated with the hero
    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("hero") // Ignore this property during JSON serialization to avoid circular dependencies
    private List<Equipment> equipment;
    // Default constructor
    public Hero() {
        this.powers = new ArrayList<>();
        this.equipment = new ArrayList<>();
    }

    // Constructor with all fields
    public Hero(String codeName, String realName, String bio, String alignment, String images, String descriptions, String personality,
                String height, String weight, String emblem, String userId) {
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
        this.userId = userId;
    }

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

    /**
     * Retrieves the code name of the hero.
     * @return The code name of the hero.
     */
    public String getCodeName() {
        return codeName;
    }

    /**
     * Sets the code name of the hero.
     * @param codeName The code name to set.
     */
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    /**
     * Retrieves the real name of the hero.
     * @return The real name of the hero.
     */
    public String getRealName() {
        return realName;
    }

    /**
     * Sets the real name of the hero.
     * @param realName The real name to set.
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * Retrieves the biography of the hero.
     * @return The biography of the hero.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Sets the biography of the hero.
     * @param bio The biography to set.
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Retrieves the alignment of the hero.
     * @return The alignment of the hero.
     */
    public String getAlignment() {
        return alignment;
    }

    /**
     * Sets the alignment of the hero.
     * @param alignment The alignment to set.
     */
    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    /**
     * Retrieves the image path of the hero.
     * @return The image path of the hero.
     */
    public String getImages() {
        return images;
    }

    /**
     * Sets the image path of the hero.
     * @param images The image path to set.
     */
    public void setImages(String images) {
        this.images = images;
    }

    /**
     * Retrieves the descriptions of the hero.
     * @return The descriptions of the hero.
     */
    public String getDescriptions() {
        return descriptions;
    }

    /**
     * Sets the descriptions of the hero.
     * @param descriptions The descriptions to set.
     */
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * Retrieves the personality traits of the hero.
     * @return The personality traits of the hero.
     */
    public String getPersonality() {
        return personality;
    }

    /**
     * Sets the personality traits of the hero.
     * @param personality The personality traits to set.
     */
    public void setPersonality(String personality) {
        this.personality = personality;
    }

    /**
     * Retrieves the height of the hero.
     * @return The height of the hero.
     */
    public String getHeight() {
        return height;
    }

    /**
     * Sets the height of the hero.
     * @param height The height to set.
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * Retrieves the weight of the hero.
     * @return The weight of the hero.
     */
    public String getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the hero.
     * @param weight The weight to set.
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * Retrieves the emblem representing the hero.
     * @return The emblem representing the hero.
     */
    public String getEmblem() {
        return emblem;
    }

    /**
     * Sets the emblem representing the hero.
     * @param emblem The emblem to set.
     */
    public void setEmblem(String emblem) {
        this.emblem = emblem;
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
    /**
     * Retrieves the powers associated with the hero.
     * @return The list of powers associated with the hero.
     */
    public List<Powers> getPowers() {
        return powers;
    }

    /**
     * Sets the powers associated with the hero.
     * @param powers The list of powers to set.
     */
    public void setPowers(List<Powers> powers) {
        this.powers = powers;
    }

    /**
     * Retrieves the userId associated with the hero.
     * @return The userId associated with the hero.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the userId associated with the hero.
     * @param userId The userId to set.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the powers associated with the hero as a comma-separated string.
     * @return The powers associated with the hero as a string.
     */
    @SuppressWarnings("unused")
    public String getPowersAsString() {
        // Check if powers list is null or empty
        if (powers == null || powers.isEmpty()) {
            return "";
        }

        // Use Java 8 stream API to map powers to their names and join them with a comma
        return powers.stream()
                .map(Powers::getDescription)
                .collect(Collectors.joining(", "));
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
                ", userId='" + userId + '\'' +
                '}';
    }
}
