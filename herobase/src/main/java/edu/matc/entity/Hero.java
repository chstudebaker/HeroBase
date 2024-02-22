package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.util.List;

/**
 * The Hero class represents a Hero with various attributes.
 */
@Entity
@Table(name = "hero")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "hero_id")
    private String heroId;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "secret_identity")
    private String secretIdentity;

    @Column(name = "alignment")
    private String alignment;

    @Column(name = "power_tags")
    private String powerTags;

    @Column(name = "bio", length = 1000) // Adjust the column length as needed
    private String bio;

    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL)
    private List<Ability> abilities;

    /**
     * Default constructor for the hero class.
     */
    public Hero() {
    }

    /**
     * Main Constructor for the Hero class.
     *
     * @param heroId          The hero's ID.
     * @param codeName        The hero's code name.
     * @param secretIdentity  The hero's secret identity.
     * @param alignment       The hero's alignment.
     * @param powerTags       The power tags.
     * @param bio             The hero's description.
     */
    public Hero(String heroId, String codeName, String secretIdentity,
                String alignment, String powerTags,
                String bio) {
        this.heroId = heroId;
        this.codeName = codeName;
        this.secretIdentity = secretIdentity;
        this.alignment = alignment;
        this.powerTags = powerTags;
        this.bio = bio;
    }

    // Getters and setters for all fields...

    public String getHeroId() {
        return heroId;
    }

    public void setHeroId(String heroId) {
        this.heroId = heroId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getSecretIdentity() {
        return secretIdentity;
    }

    public void setSecretIdentity(String secretIdentity) {
        this.secretIdentity = secretIdentity;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getPowerTags() {
        return powerTags;
    }

    public void setPowerTags(String powerTags) {
        this.powerTags = powerTags;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    /**
     * Returns a string representation of the hero data.
     *
     * @return A string with hero data.
     */
    @Override
    public String toString() {
        return "Hero Data:" + '\n' +
                "Hero ID=" + heroId + '\n' +
                "Code Name=" + codeName + '\n' +
                "Secret Identity=" + secretIdentity + '\n' +
                "Alignment=" + alignment + '\n' +
                "Power Tags=" + powerTags + '\n' +
                "Bio=" + bio;
    }
}
